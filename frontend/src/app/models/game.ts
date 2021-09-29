export default class Game {

  id:number;
  fullName:string;
  pos:number;
  time:number;
  date:Date;
  picture: any;
  pictureId: number;


  constructor(id: number, fullName: string, pos: number, time: number, date: Date, picture: any, pictureId: number) {
    this.id = id;
    this.fullName = fullName;
    this.pos = pos;
    this.time = time;
    this.date = date;
    this.picture = picture;
    this.pictureId = pictureId;
  }
}
