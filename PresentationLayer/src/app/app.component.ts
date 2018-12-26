import { Component } from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {slideInAnimation} from "../animations";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [
    slideInAnimation
  ]
})
export class AppComponent {
  title = 'Westminster Library System';

  menu = [
    { title: 'Home', link: '#/home' },
    { title: 'Lending Section', link: '#/lending' },
    { title: 'Management Section', link: '#/management' },
    { title: 'Dashboard', link: '#/dashboard ' },
  ]

  prepareRoute(outlet: RouterOutlet) {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
  }
}
