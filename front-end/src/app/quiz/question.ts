import {Answer} from "./answer";

export class Question {
  description : string;
  correctAnswer: string;
  wrongAnswers: Answer[];
}
