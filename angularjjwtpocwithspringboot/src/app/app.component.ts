import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from './_services';
import { User } from './_models';


// https://jasonwatmore.com/post/2018/11/16/angular-7-jwt-authentication-example-tutorial


@Component({ selector: 'app-root', templateUrl: './app.component.html' })
export class AppComponent {
    currentUser: User;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) {
        console.log('OutSide...');
        this.authenticationService.currentUser.subscribe(user => {
            console.log("inside App");
            this.currentUser = user;
        });
    }

    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/login']);

    }

    newUser() {
        this.authenticationService.logout();
        this.router.navigate(['/newUser']);
    }

    admin() {
        // this.authenticationService.logout();
        // this.router.navigate(['/admin']);
    }

    careers() {
        // this.authenticationService.logout();
        // this.router.navigate(['/careers']);
    }
    deleteToken() {
        localStorage.removeItem('currentUser');
        this.authenticationService.currentUserSubject.next(null);
    }
}