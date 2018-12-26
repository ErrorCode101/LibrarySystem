import { Component, OnInit } from '@angular/core';
import {HttpService} from "../service/http.service";

@Component({
  selector: 'app-borrow',
  templateUrl: './borrow.component.html',
  styleUrls: ['./borrow.component.css']
})
export class BorrowComponent implements OnInit {

  public isbn:string = "";
  public libraryId:string = "";
  public name:string = "";
  public number:number;
  public title:string = "";
  public selectedId:string;
  public returnDate:any;

  public readers:any[] = [];

  constructor(private httpClient:HttpService){ }

  ngOnInit() {
    this.getReaders();
  }

  private getReaders(){
    this.httpClient.getData('getpersons').subscribe(result => {
      this.readers = result;
      console.log(this.readers)
    });
  }

}
