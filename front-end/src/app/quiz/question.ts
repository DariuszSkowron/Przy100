import {Answer} from "./answer";

export class Question {
  Id: string;
  description : string;
  correctAnswer: string;
  wrongAnswers: Answer[];
}
