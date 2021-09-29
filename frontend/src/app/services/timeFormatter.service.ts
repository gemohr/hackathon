import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export default class TimeFormatterService {
  formatTime(time: number) {
    const mili = time % 10;
    const sec = Math.floor(time/10) % 60
    const min = Math.floor(time/600)

    let secText = sec.toString();
    if(secText.length < 2) secText = '0' + sec

    let minText = min.toString();
    if(minText.length < 2) minText = '0' + min

    return `${minText}:${secText}.${mili}`;
  }
}
