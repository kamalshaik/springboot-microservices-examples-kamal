import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '../_models';
import { UserService, AuthenticationService } from '../_services';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent implements OnInit {
    users: User[] = [];

    constructor(private userService: UserService) { }

    ngOnInit() {

        this.userService.getAll().subscribe(users => {

            console.log("HomeComponent ---> getAll()...");
            console.log(users);
            this.users = users;
        });
    }
}