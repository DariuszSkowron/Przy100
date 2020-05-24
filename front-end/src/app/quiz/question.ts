import {Answer} from "./answer";

export class Question {
  id: string;
  description : string;
  correctAnswerId: string;
  userAnswer: number;
  listOfAnswers: Answer[];
}
