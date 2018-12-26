import { Component, OnInit } from '@angular/core';
import {FileInfo, FileRestrictions} from "@progress/kendo-angular-upload";
import {HttpService} from "../service/http.service";
import {State, process} from "@progress/kendo-data-query";
import {GridDataResult} from "@progress/kendo-angular-grid";

@Component({
  selector: 'app-book-manage',
  templateUrl: './book.manage.component.html',
  styleUrls: ['./book.manage.component.css']
})
export class BookManageComponent implements OnInit {

  public isbnBook:string = "";
  public isbnDVD:string = "";
  public dvdName:string = "";
  public bookName:string = "";
  public sectorBook:string ="";
  public sectorDVD:string = "";
  public noOfPages:number;
  public publisher:string = "";
  public producer:string = "";
  public publishedDateBook:any;
  public publishedDateDVD:any;
  public authors:string = "";
  public actors:string = "";
  public languages:string = "";
  public subtitles:string = "";
  public reader:string = "";
  public mobileNo:number;
  public email:string = "";
  public libraryId:string = "";

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

  public listSectors = ["Children", "Novel", "Education", "Magazine", "Documentary"]

  constructor(private httpClient: HttpService) { }

  ngOnInit() {
    this.getBooks();
    this.getDVDs();
  }

  uploadSaveUrl = 'http://localhost:9000/upload'; // should represent an actual API endpoint
  uploadRemoveUrl = '/assets/uploads/'; // should represent an actual API endpoint

  public myRestrictions: FileRestrictions = {
    allowedExtensions: ['jpg', 'jpeg', 'png']
  };

  public userImages: Array<FileInfo>;

  save(value: any, valid: boolean) {
    if (valid) {
      console.log('Everything is OK!');
    }
  }

  private getBooks(){
    this.httpClient.getData("getbooks").subscribe(result =>{
      for(let i of result){
        if(i.availableDate == null || i.availableDate.date == "0/0/0"){
          i.availableDate = "Available";
          i["type"]= "Book"
        }
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
          i.availableDate = "Available";
          i["type"]= "DVD"
        }
        this.gridData.data.push(i);
        this.gridResult.push(i)
      }
      this.gridData.total += result.length;
      console.log(result);
    })
  }

  public onSubmitAddBook(){
      let obj = { isbn: this.isbnBook,
          title: this.bookName,
          sector: this.sectorBook,
          publicationDate: null,
        borrowedDate: null,
        availableDate: null,
        reader: null,
        authors: [this.authors],
        publisher: this.publisher,
        noOfPages: this.noOfPages
      };

      this.httpClient.postData('addbook',obj).subscribe(result => {
        console.log(result);
      })
  }

  public onSubmitAddDVD(){
    let obj = { isbn: this.isbnDVD,
        title:this.dvdName,
        sector: this.sectorDVD,
        publicationDate: null,
      borrowedDate: null,
      availableDate: null,
      reader: null,
      producer: this.producer,
      actors: [this.actors],
      languages: [this.languages],
      subtitles: [this.subtitles]
    };

    this.httpClient.postData('adddvd',obj).subscribe(result => {
      console.log(result);
    });
  }

  public onSubmitPerson(){
    let data = {
      name: this.reader,
      phoneNo: this.mobileNo,
      email: this.email
    };
    this.httpClient.postData('addreader', data).subscribe( result => {
      console.log(result);
      this.libraryId = result._id;
    })
  }

  public onDelete(item:any){
    let url = "";
    if(item.type == 'Book'){
      url = 'deletebook?id='
    }else{
      url = 'deletedvd?id='
    }

    this.httpClient.getData(url + item.isbn).subscribe( result=> {
      console.log(result);
      this.gridData.data = [];
      this.gridResult = [];
      this.gridData.total = 0;
      this.getBooks();
      this.getDVDs();

    })
  }
}
