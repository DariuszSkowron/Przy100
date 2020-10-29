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

  correctAnswerCount: number;
  isDisabled = false;
  finishedQuiz: Quiz;
  userResult: Result;
  lastScoreSubmitted: number;
  userAnswers: string[] = [];
  

  constructor(public quizService: QuizService, private router: Router) { }

  ngOnInit() {
    if ((localStorage.getItem('quizProgress')) === '2') {
      this.quizService.seconds = Number((localStorage.getItem('seconds')));
      this.quizService.quizProgress = Number((localStorage.getItem('quizProgress')));
      this.quizService.questionList = JSON.parse(localStorage.getItem('questionList'));
      this.quizService.startTime = JSON.parse(localStorage.getItem('startTime'));
      this.quizService.totalTime = JSON.parse(localStorage.getItem('totalTime'));
      this.getLastSubmittedResult();

      this.finishedQuiz = JSON.parse(localStorage.getItem('quiz'));
      // this.quizService.userTest.questionList = JSON.parse(localStorage.getItem('questionList'));

      // this.userAnswers = this.quizService.questionList[1].userAnswer;

      this.quizService.questionList.forEach((question, i) => {
        this.userAnswers[i] = question.userAnswer;
      });


      this.quizService.questionList.forEach((e,i) => {
        this.userAnswers[i] = e.userAnswer;
      });

      this.finishedQuiz.userAnswers = this.userAnswers;

      // this.finishedQuiz.userAnswers = this.userAnswers;

      // this.test();
      this.quizService.getUserResult(this.finishedQuiz).subscribe(res => {
        this.userResult = res;
        if (this.lastScoreSubmitted > this.userResult.totalScore) {
          this.disableButton();
        }
      });

      this.correctAnswerCount = this.userResult.numberOfCorrectAnswers;

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

  // test(){
  //   this.quizService.getUserResult(this.finishedQuiz).subscribe(res => {
  //     this.userResult = res;
  //     this.correctAnswerCount = this.userResult.numberOfCorrectAnswers;
  //   });
  // }
  getLastSubmittedResult() {
    this.quizService.checkIfScoreIsHigh().subscribe(result => {
      this.lastScoreSubmitted = result;
    });
  }

  disableButton() {
    this.isDisabled = true;
  }

  actionMethod($event: MouseEvent) {
    ($event.target as HTMLButtonElement).disabled = true;
  }


  onSubmit(nickname: string) {
    this.userResult.nickname = nickname;
    this.disableButton();
    this.quizService.submitScore(this.userResult).subscribe(() => {
      this.restart();
    });
  }

  restart() {
    localStorage.setItem('quizProgress', '0');
    localStorage.setItem('questionList', '');
    localStorage.setItem('seconds', '0');
    // localStorage.setItem('quiz', '');
  }

  quizRepeat() {
    this.restart();
    this.router.navigate(['/quiz']);
  }


}
