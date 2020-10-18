import {Component, OnInit} from '@angular/core';
import {QuizService} from '../shared/quiz.service';
import {Router} from '@angular/router';
import {Result} from './result';
import {Quiz} from '../quiz/quiz';


@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss']
})
export class ResultComponent implements OnInit {

  correctAnswerCount = 0;
  listOfQuestionId;
  isDisabled = false;
  finishedQuiz: Quiz;
  userResult: Result;
  userAnswers: string[];

  constructor(public quizService: QuizService, private router: Router) { }

  ngOnInit() {
    if ((localStorage.getItem('quizProgress')) === '2') {
      this.quizService.seconds = Number((localStorage.getItem('seconds')));
      this.quizService.quizProgress = Number((localStorage.getItem('quizProgress')));
      this.quizService.questionList = JSON.parse(localStorage.getItem('questionList'));
      this.quizService.startTime = JSON.parse(localStorage.getItem('startTime'));
      this.quizService.totalTime = JSON.parse(localStorage.getItem('totalTime'));

      this.finishedQuiz = JSON.parse(localStorage.getItem('quiz'));

      this.quizService.questionList.forEach((e,i) => {
        e.userAnswer = this.userAnswers[i];
      });
      this.finishedQuiz.userAnswers = this.userAnswers;

      this.test();

      // this.listOfQuestionId = this.quizService.questionList.map(question => question.id);
      // this.quizService.getCorrectAnswers(this.listOfQuestionId).subscribe((data: any) => {
      //   this.quizService.questionList.forEach((e, i) => {
      //     if (e.userAnswer === data[i]) {
      //       this.correctAnswerCount++;
      //     }
      //   });
      // }
      // );
    }
  }

  test(){
    this.quizService.getUserResult(this.finishedQuiz).subscribe(res => {
      this.userResult = res;
      this.correctAnswerCount = this.userResult.numberOfCorrectAnswers;
    });
  }

  disableButton() {
    this.isDisabled = true;
  }

  actionMethod($event: MouseEvent) {
    ($event.target as HTMLButtonElement).disabled = true;
  }


  onSubmit(nickname: string) {
    const newResult: Result = {
      id: null,
      timeSpent: this.quizService.totalTime,
      numberOfCorrectAnswers: this.correctAnswerCount,
      nickname,
      totalScore: 0
    };
    this.disableButton();
    this.quizService.submitScore(newResult).subscribe(() => {
      this.restart();
    });
  }

  restart() {
    localStorage.setItem('quizProgress', '0');
    localStorage.setItem('questions', '');
    localStorage.setItem('seconds', '0');
  }

  quizRepeat() {
    this.restart();
    this.router.navigate(['/quiz']);
  }


}
