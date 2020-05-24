import { Component, OnInit } from '@angular/core';
import {QuizService} from "../../shared/quiz.service";
import {Observable} from "rxjs";
import {Quiz} from "../quiz";
import {Router} from "@angular/router";

@Component({
  selector: 'quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export class QuizComponent implements OnInit{

  quiz: Quiz;
  constructor(private router: Router, public quizService: QuizService) {

  }

  ngOnInit() {
    if (parseInt(localStorage.getItem('seconds')) > 0){
      this.quizService.seconds = parseInt(localStorage.getItem('seconds'));
      this.quizService.quizProgress = parseInt(localStorage.getItem('quizProgress'));
      this.quiz = JSON.parse(localStorage.getItem('quiz'));
      if (this.quizService.quizProgress == 2)
        this.router.navigate(['/result']);
      else
        this.startTimer();
    }
    else {
      this.quizService.seconds = 0;
      this.quizService.quizProgress = 0;
      this.getQuiz();
    }
  }

  getQuiz() {
    this.quizService.getQuiz().subscribe(res => {
      this.quiz = res;
      this.quizService.questionList = this.quiz.questionList;
      localStorage.setItem('questionList', JSON.stringify(this.quizService.questionList));
      this.startTimer();
    })
  }

  startTimer() {
    this.quizService.timer = setInterval(() => {
      this.quizService.seconds++;
      localStorage.setItem('seconds', this.quizService.seconds.toString());
    }, 1000);
  }

  Answer(questionId, selectedAnswer){
    this.quiz.questionList[this.quizService.quizProgress].userAnswer = selectedAnswer;
    localStorage.setItem('quiz', JSON.stringify(this.quiz));
    localStorage.setItem('questionList', JSON.stringify(this.quizService.questionList));
    this.quizService.quizProgress++;
    localStorage.setItem('quizProgress', this.quizService.quizProgress.toString());
    if (this.quizService.quizProgress == 2){
      clearTimeout(this.quizService.timer);
      this.router.navigate(['/result']);
    }

  }

}
