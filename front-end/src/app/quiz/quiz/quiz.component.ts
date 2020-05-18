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
  questions: any[];
  timer;
  quizProgress: number;
  seconds: number;
  constructor(private router: Router, private quizService: QuizService) {

  }

  ngOnInit() {
    this.seconds = 0;
    this.quizProgress = 0;
    this.getQuiz();
  }

  getQuiz() {
    this.quizService.getQuiz().subscribe(res => {
      this.quiz = res;
      this.startTimer();
    })
  }

  startTimer() {
    this.timer = setInterval(() => {
      this.seconds++;
    }, 1000);
  }

  displayTimeElapsed() {
    return Math.floor(this.seconds / 3600) + ':' + Math.floor(this.seconds / 60) + ':' + Math.floor(this.seconds % 60);
  }

  Answer(questionId, selectedAnswer){
    // this.questions(this.quizProgress).answer = selectedAnswer;
  }

}
