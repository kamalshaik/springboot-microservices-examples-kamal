import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AuthenticationService, UserService } from '../_services';

@Component({ templateUrl: 'create.newuser.component.html' })
export class CreateNewUserComponent implements OnInit {
    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    error = '';

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private userService: UserService,
        private authenticationService: AuthenticationService
    ) { }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required],
            confirmPassword: ['', Validators.required]
        });

        // reset login status
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f() {
        return this.loginForm.controls;
    }

    createUser() {
        this.submitted = true;
        //this.authenticationService.logout();
        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        if (this.loginForm.controls.password.value !== this.loginForm.controls.confirmPassword.value) {
            this.error = 'Password and Confirm Password not matched';
            return;
        }


        console.log(this.loginForm.controls.username.value);
        console.log(this.loginForm.controls.password.value);
        console.log(this.loginForm.controls.confirmPassword.value);
        this.userService.createNewUser(this.loginForm.controls.username.value,
            this.loginForm.controls.password.value).subscribe(data => {
                if (confirm('User created !! , do you want to login ?')) {
                    this.router.navigate(['/']);
                } else {
                    this.loginForm.reset();
                }
            });
    }
}
