import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import { User } from '../_models';
import { Observable, throwError } from 'rxjs';
import { map, catchError, retry, tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll(): Observable<User[]> {

        console.log('UserService ---> constructor ---> ');
        console.log(JSON.parse(localStorage.getItem('currentUser')).token);
        const httpOptions = {
            headers: new HttpHeaders({
                // 'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('currentUser')).token
            })
        };
        return this.http.get<User[]>('http://localhost:3000/users/', httpOptions);
    }

    createNewUser(username, password) {

        const data = { 'username': username, 'password': password };
        const config = { headers: new HttpHeaders().set('Content-Type', 'application/json') };

        return this.http.post<any>('http://localhost:3000/saveNewUser', data, config)
            .pipe(map(user => {
                // login successful if there's a jwt token in the response

                return user;
            }),
                catchError(this.handleError)
            );


    }

    getServices(): Observable<User> {
        // return this.getServicesWithRetry();
        return this.http.get<User>('http://localhost:3000/fetchServiceData/');
    }

    getServicesWithRetry(): Observable<User> {
        const URL = 'http://localhost:3000/fetchServiceData1/';
        return this.http.get<User>(URL).pipe(
            tap(() => console.log(URL)),
            retry(3),  // retry the failed request up to 3 times
            catchError(this.handleError)
        );
    }

    handleError(error: HttpErrorResponse) {
        console.log("lalalalalalalala");
        return throwError(error);
    }
}
