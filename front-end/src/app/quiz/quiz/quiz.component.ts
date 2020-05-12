import { Component, OnInit } from '@angular/core';
import {QuizService} from "../quiz.service";
import {Observable} from "rxjs";
import {Quiz} from "../quiz";

@Component({
  selector: 'quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export class QuizComponent implements OnInit{

  quiz: Quiz;
  constructor(private quizService: QuizService) {

  }

  ngOnInit() {
    this.getQuiz();
  }

  getQuiz() {
    this.quizService.getQuiz().subscribe(res => {
      this.quiz = res;
    })
  }

}
