import { Component } from '@angular/core';
import {ApiService} from "../../services/api.service";
import {Member} from "../../model/member";

@Component({
  selector: 'app-members-list',
  templateUrl: './members-list.component.html',
  styleUrls: ['./members-list.component.scss'],
})
export class MembersListComponent {
  membersService;
  members?: Member[] = undefined;
  title: string = "ciao"

  constructor() {
    this.membersService = new ApiService();
    this.fetchMembers();
  }

  fetchMembers() {
    this.membersService.fetchMembers().then(members => {
      this.members = members;
      console.log(members);
    });
  }
}
