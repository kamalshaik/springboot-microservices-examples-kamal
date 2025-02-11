import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { User } from '../_models';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    public currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient, private router: Router) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
        console.log(this.currentUser);
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }
    // loginWithSpringBootApplication
    login(username: string, password: string) {

        // const data = { 'username': 'javainuse', 'password': 'password' };
        const data = { 'username': username, 'password': password };
        const config = { headers: new HttpHeaders().set('Content-Type', 'application/json') };

        return this.http.post<any>('http://localhost:3000/authenticate', data, config)
            .pipe(map(user => {
                // login successful if there's a jwt token in the response
                console.log('AuthenticationService -- > login() ---> ');
                console.log(user.token);

                if (user && user.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    console.log('AuthenticationService -- > login() --->Token :  ');
                    console.log(JSON.stringify(user));

                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.currentUserSubject.next(user);
                }
                return user;
            }),
                catchError(this.handleError)
            );
    }

    logout() {
        console.log('AuthenticationService -- > logout()');
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
    handleError(error: HttpErrorResponse) {
        console.log("lalalalalalalala");
        return throwError(error);
    }
}
