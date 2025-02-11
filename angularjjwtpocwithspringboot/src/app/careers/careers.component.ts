import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services';

@Component({
  selector: 'app-careers',
  templateUrl: './careers.component.html',
  styleUrls: ['./careers.component.css']
})
export class CareersComponent implements OnInit {
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
