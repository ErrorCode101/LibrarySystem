import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {catchError} from "rxjs/internal/operators";
import {throwError} from "rxjs/index";

@Injectable()
export class HttpService {

  private coreService:string = "http://localhost:9000/"

  constructor(private http: HttpClient) {
  }

  getData(url: string):Observable<any> {
    return this.http.get(this.coreService + url).pipe(catchError(this.handleError));
  }

  postData(url:string, data:any):Observable<any> {
    return this.http.post(this.coreService + url,data).pipe(catchError(this.handleError));
  }

  handleError(error: HttpErrorResponse){
    return throwError(error);
  }
}
