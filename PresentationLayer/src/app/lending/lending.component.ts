import { Component, OnInit } from '@angular/core';
import {HttpService} from "../service/http.service";
import {process,State} from "@progress/kendo-data-query";
import {DataStateChangeEvent, GridDataResult} from "@progress/kendo-angular-grid";

@Component({
  selector: 'app-lending',
  templateUrl: './lending.component.html',
  styleUrls: ['./lending.component.css']
})
export class LendingComponent implements OnInit {


  private gridResult:any = [];
  public returnDate:any = null;
  public state: State = {
    skip: 0,
    take: 5,
    filter: {
      logic: 'and',
      filters: [{ field: 'ProductName', operator: 'contains', value: '' }]
    }
  };

  public gridData: GridDataResult =  process(this.gridResult, this.state);
  public selectedIsbns:string[] =[];
  public title ="";
  public isbn ="";
  public sector = "";
  public type = "";
  public dialogTitle:string;
  public isDialogOpen:boolean = false;
  public isBorrow:boolean = false;
  public selectedData:any;

  private readers:any[] = [];

  constructor(private httpClient: HttpService) { }

  ngOnInit() {
    this.getBooks();
    this.getDVDs();
  }


  private getBooks(){
    this.httpClient.getData("getbooks").subscribe(result =>{
      for(let i of result){
        if(i.availableDate == null || i.availableDate.date == "0/0/0"){
          i.availableDate = { date: "Available"};

        }
        i["type"]= "Book"
        this.gridData.data.push(i);
        this.gridResult.push(i)
      }
      this.gridData.total += result.length;
      console.log(result);
    })
  }

  private getDVDs(){
    this.httpClient.getData("getdvds").subscribe(result =>{
      for(let i of result){
        if(i.availableDate == null || i.availableDate.date == "0/0/0"){
          i.availableDate = { date: "Available"};

        }
        i["publisher"] = i.producer;
        i["type"]= "DVD"
        this.gridData.data.push(i);
        this.gridResult.push(i)
      }
      this.gridData.total += result.length;
      console.log(result);
    })
  }

  public dataStateChange(state: DataStateChangeEvent): void {
    this.state = state;
    this.gridData = process(this.gridResult, this.state);
  }

  public onSelectedKeysChange(e){
    console.log(e)
    let item = this.gridResult.filter(x => x.isbn == e)[0];
    this.isbn = item.isbn;
    this.title = item.title;
    this.sector = item.sector;
    this.type = item.type;
  }

  public borrow(data:any){
    this.dialogTitle = "Item Borrow";
    this.isDialogOpen = true
    if(data.availableDate.date == 'Available'){
      this.isBorrow = true;
    }else {
      this.isBorrow = false;
    }
  }

  public return(data:any){
    this.dialogTitle = "Item Return";
    this.selectedData= null;
    if(data.availableDate.date != 'Available'){
      this.isBorrow = false;
    }else {
      this.isBorrow = true;
    }
    if(data.reader != undefined && data.reader != null) {
      this.selectedData = data;
    }
    this.isDialogOpen = true;
  }

  public close(){
    this.dialogTitle = "";
    this.isDialogOpen = false;
    this.gridData.data = [];
    this.gridData.total = 0;
    this.gridResult = [];
    this.getBooks();
    this.getDVDs();
  }

  private getReaders(){
    this.httpClient.getData('getpersons').subscribe(result => {
      this.readers = result;
      console.log(this.readers);
    });
  }
}
