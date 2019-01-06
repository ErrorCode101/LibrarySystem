import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { LendingComponent } from './lending/lending.component';
import { GridModule } from '@progress/kendo-angular-grid';
import { ButtonsModule } from '@progress/kendo-angular-buttons';
import { DialogsModule } from '@progress/kendo-angular-dialog';
import {FormsModule} from "@angular/forms";
import { DateInputsModule } from '@progress/kendo-angular-dateinputs';
import { BookManageComponent } from './book.manage/book.manage.component';
import { LayoutModule } from '@progress/kendo-angular-layout';
import {HttpService} from "./service/http.service";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule, Routes} from "@angular/router";
import { UploadModule } from '@progress/kendo-angular-upload';
import { BorrowComponent } from './borrow/borrow.component';
import { ReturnComponent } from './return/return.component';
import { ReserveComponent } from './reserve/reserve.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DropDownsModule } from '@progress/kendo-angular-dropdowns';
import { DeleteComponent } from './delete/delete.component';
import { PDFExportModule } from '@progress/kendo-angular-pdf-export';
import { ExcelExportModule } from '@progress/kendo-angular-excel-export';





const appRoutes: Routes = [
  { path: '', component: HomeComponent , data: {animation: 'HomePage'}},
  { path: 'lending', component : LendingComponent, data: {animation: 'LendingPage'}},
  { path: 'management', component : BookManageComponent, data: {animation: 'ManagementPage'}},
  { path: 'dashboard', component : DashboardComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LendingComponent,
    BookManageComponent,
    BorrowComponent,
    ReturnComponent,
    ReserveComponent,
    DashboardComponent,
    DeleteComponent
  ],
  imports:[
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(appRoutes, { useHash: true }),
    HttpClientModule,
    FormsModule,
    GridModule,
    ButtonsModule,
    DialogsModule,
    DateInputsModule,
    LayoutModule,
    UploadModule,
    DropDownsModule,
    PDFExportModule,
    ExcelExportModule,
  ],
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
