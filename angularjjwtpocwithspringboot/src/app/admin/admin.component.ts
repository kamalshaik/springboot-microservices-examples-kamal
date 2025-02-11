import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  message: string;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getServices().subscribe(data => {
      this.message = data.firstName;
      console.log(data);
    },
      error => {
        alert('Error : ' + error);
      });
  }

}
