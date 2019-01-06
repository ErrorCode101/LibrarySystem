import {Component, Input, OnInit} from '@angular/core';
import {HttpService} from "../service/http.service";

@Component({
  selector: 'app-borrow',
  templateUrl: './borrow.component.html',
  styleUrls: ['./borrow.component.css']
})
export class BorrowComponent implements OnInit {

  //@Input() processedOrderNumber: string;

  @Input() isbn:string;
  @Input() title:string;
  @Input() type:string;
  public libraryId:string = "";
  public name:string = "";
  public number:number;

  public selectedId:string;
  public returnDate:Date = new Date();

  public readers:any[] = [];
  private results:any[] = [];

  constructor(private httpClient:HttpService){ }

  ngOnInit() {
    this.getReaders();
  }

  private getReaders(){
    this.httpClient.getData('getpersons').subscribe(result => {
      this.results = result;
      this.readers = this.results.slice();
      console.log(this.readers)
    });
  }

  public filterChange(filter: any): void {
    this.readers = this.results.filter((s) => s._id.toLowerCase().indexOf(filter.toLowerCase()) !== -1);
  }

  public valueChange(value:any){
    console.log(value);
    let val = this.results.filter(x => x._id== value)[0];

    if(val != null && val != undefined) {
      this.selectedId = val._id;
      this.name = val.name;
      this.number = val.phoneNo;
    }else{
      this.selectedId = null;
      this.name = null;
      this.number = null;
    }
  }

  public borrow(){
    let url:string = "";
    let data = {Id:this.isbn, PersonId:this.libraryId, Date: {Day:this.returnDate.getDate(), Month: this.returnDate.getMonth()+1, Year: this.returnDate.getFullYear()}};;
    if(this.type == 'Book'){
      url = 'borrowbook'

    }else{
      url = 'borrowdvd';
    }
    this.httpClient.postData(url, data).subscribe(result => {
      console.log(result);
    })
  }


}
