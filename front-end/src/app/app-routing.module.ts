import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {QuizComponent} from "./quiz/quiz/quiz.component";
import {ResultComponent} from "./result/result.component";


const routes: Routes = [
  {path: 'quiz', component: QuizComponent },
  {path: 'result', component: ResultComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }