import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {QuizService} from "../../shared/quiz.service";
import {Result} from "../result";

@Component({
  selector: 'app-highscores',
  templateUrl: './highscores.component.html',
  styleUrls: ['./highscores.component.scss']
})
export class HighscoresComponent implements OnInit {

  highscores: Result[];

  constructor(private router: Router, public quizService: QuizService) { }

  ngOnInit(){
    this.getHighscores();
  }

  getHighscores(){
    this.quizService.getHighScores().subscribe(
      res => {
        this.highscores = res;
        this.highscores.sort((a,b) => a.totalScore < b.totalScore ? -1:1);
      }
    )
  }


}
