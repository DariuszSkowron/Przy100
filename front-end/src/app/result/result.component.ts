import {Component, OnInit, ViewChild} from '@angular/core';
import {QuizService} from "../shared/quiz.service";
import {Router} from "@angular/router";
import {QuizComponent} from "../quiz/quiz/quiz.component";
import {Result} from "./result";

@Component({
  selector: 'result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss']
})
export class ResultComponent implements OnInit {

  correctAnswerCount = 0;
  listOfQuestionId;
  constructor(public quizService: QuizService, private router: Router) { }

  ngOnInit(){
    if (parseInt(localStorage.getItem('quizProgress')) == 2){
      this.quizService.seconds = parseInt(localStorage.getItem('seconds'));
      this.quizService.quizProgress = parseInt(localStorage.getItem('quizProgress'));
      this.quizService.questionList = JSON.parse(localStorage.getItem('questionList'));
      this.quizService.startTime = JSON.parse(localStorage.getItem('startTime'));



      this.listOfQuestionId = this.quizService.questionList.map(question => question.id);
      this.quizService.getCorrectAnswers(this.listOfQuestionId).subscribe((data: any) => {
        this.quizService.questionList.forEach((e,i) => {
          if (e.userAnswer == data[i])
            this.correctAnswerCount++;
        });
      }
      );
    }
  }

  onSubmit(nickname: string){
    const newResult: Result = {
      id: null,
      timeSpent: this.quizService.startTime,
      numberOfCorrectAnswers: this.correctAnswerCount,
      nickname: nickname
    };
    this.quizService.submitScore(newResult).subscribe(() => {
      this.restart();
    });
  }

  restart() {
    localStorage.setItem('quizProgress', "0");
    localStorage.setItem('questions', "");
    localStorage.setItem('seconds', "0");
    this.router.navigate(['/quiz']);
  }

}
