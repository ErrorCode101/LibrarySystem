import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [`h3.sub-head{
                background-color: #eee;
                padding: 6px;
                margin: 8px 0px 0px 0px;
            }`]
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
