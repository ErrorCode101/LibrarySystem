import { Component, OnInit } from '@angular/core';
import {process, State} from "@progress/kendo-data-query";
import {DataStateChangeEvent, GridDataResult} from "@progress/kendo-angular-grid";
import {HttpService} from "../service/http.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

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
  private date = new Date(Date.now());
  public gridData: GridDataResult =  process(this.gridResult, this.state);
  constructor(private httpClient: HttpService) { }

  ngOnInit() {
    this.getBooks();
    //this.getDVDs();
  }

  private getBooks(){
    this.httpClient.getData("getbooks").subscribe(result =>{
      for(let i of result){
        if(i.availableDate == null || i.availableDate.date == "0/0/0"){
          i.availableDate = null;
        }else{
          i.availableDate = new Date(i.availableDate.day + "/" + i.availableDate.month + "/" + i.availableDate.year);
          i["type"]= "Book"
          this.gridData.data.push(i);
          this.gridResult.push(i)
        }

      }
      this.gridData.total += result.length;
      console.log(result);
      this.getDVDs();
    })
  }

  private getDVDs(){
    this.httpClient.getData("getdvds").subscribe(result =>{
      for(let i of result){
        if(i.availableDate == null || i.availableDate.date == "0/0/0"){
          i.availableDate = null;
        }else{
          i.availableDate = new Date(i.availableDate.day + "/" + i.availableDate.month + "/" + i.availableDate.year);
          i["publisher"] = i.producer;
          i["type"]= "DVD"
          this.gridData.data.push(i);
          this.gridResult.push(i)
        }

      }
      this.gridData.total += result.length;
      console.log(result);
      this.sortByDueDate();
    })
  }

  private getTime(date?: Date) {
    return date != null ? date.getTime() : 0;
  }

  public sortByDueDate(): void {
    this.gridData.data.sort(a  => {
      return this.getTime(a.availableDate) - this.getTime(this.date);
    });
  }
}
