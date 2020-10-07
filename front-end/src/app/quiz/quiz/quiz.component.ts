import { Component, OnInit } from '@angular/core';
import {QuizService} from "../../shared/quiz.service";
import {interval, Observable} from "rxjs";
import {Quiz} from "../quiz";
import {Router} from "@angular/router";
import {take, timeInterval} from "rxjs/operators";

@Component({
  selector: 'quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export class QuizComponent implements OnInit{

  quiz: Quiz;
  // songUrl: string = "https://p.scdn.co/mp3-preview/272ade18568d8dbc23244870b74427cb92e02a4b?cid=774b29d4f13844c495f206cafdad9c86";
  // songId: number = 2;

  private _player: HTMLAudioElement;
  private _countdown = 5;
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
      this.quizService.startTime = this.quiz.startTime;
      localStorage.setItem('questionList', JSON.stringify(this.quizService.questionList));
      localStorage.setItem('startTime', JSON.stringify(this.quizService.startTime));
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
      this.quizService.totalTime = (new Date().getTime() - new Date(this.quizService.startTime).getTime()) / 1000;
      localStorage.setItem('totalTime', this.quizService.totalTime.toString());
      this.router.navigate(['/result']);
    }
  }


  get countdown(): number {
    return this._countdown;
  }

  public playSong(player: HTMLAudioElement, button: HTMLElement): void {


    let countdown = this._countdown - 1;

    interval(1000)
      .pipe(
        timeInterval(),
        take(this._countdown)
      )
      .subscribe((next) => {
        this._countdown = countdown - next.value;
      }, (error) => {

      }, () => {
        this._countdown = 0;
        player.pause();
      });

    player.play();
    button.setAttribute('disabled', '');
    button.classList.add('disabled');
  }

}
