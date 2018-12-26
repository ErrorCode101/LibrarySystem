import { Component, OnInit } from '@angular/core';
import {process, State} from "@progress/kendo-data-query";
import {DataStateChangeEvent, GridDataResult} from "@progress/kendo-angular-grid";

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

  public gridData: GridDataResult =  process(this.gridResult, this.state);
  constructor() { }

  ngOnInit() {
  }

  public dataStateChange(state: DataStateChangeEvent): void {
    this.state = state;
    this.gridData = process(this.gridResult, this.state);
  }
}
