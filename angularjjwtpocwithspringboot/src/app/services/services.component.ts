import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services';

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.css']
})
export class ServicesComponent implements OnInit {

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
