import {Component, Input, OnInit} from '@angular/core';
import {HttpService} from "../service/http.service";

@Component({
  selector: 'app-return',
  templateUrl: './return.component.html',
  styleUrls: ['./return.component.css']
})
export class ReturnComponent implements OnInit {

  @Input() data:any;
  public isbn:string;
  public libraryId:string;
  public bookName:string;
  public returnDate:string;
  public fine:number;
  public firstName:string;
  public lastName:string;
  public type:string;

  constructor(private httpClient: HttpService) { }

  ngOnInit() {
    this.bookName = this.data.title;
    this.returnDate = this.data.availableDate.day + "/" + this.data.availableDate.month + "/" +  this.data.availableDate.year;
    this.type = this.data.type;
    this.firstName = this.data.reader.name;
    this.isbn = this.data.isbn;
    this.libraryId = this.data.reader._id;
  }

  public return(){
    let url:string = "";
    let data = {Id:this.isbn, PersonId:this.libraryId};;
    if(this.type == 'Book'){
      url = 'returnbook'

    }else{
      url = 'returndvd';
    }
    this.httpClient.postData(url, data).subscribe(result => {
      console.log(result);
    })
  }


}
