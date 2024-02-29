import { Component } from '@angular/core';
import {Member} from "../../model/member";
import {ApiService} from "../../services/api.service";
import {ActivatedRoute} from "@angular/router";
import * as moment from "moment";
import {Website} from "../../model/website";
import {Party} from "../../model/party";
import {Membership} from "../../model/membership";

@Component({
  selector: 'app-member-details',
  templateUrl: './member-details.component.html',
  styleUrls: ['./member-details.component.scss']
})
export class MemberDetailsComponent {
  members?: Member[];
  member?: Member;
  websites?: Website[];
  memberships: (Membership & {
    party: Party,
  })[] = []


  constructor(private route: ActivatedRoute) {
    new Promise(async (resolve, reject) => {
      const apiService = new ApiService();
      this.members = await apiService.fetchMembers();

      const routeParams = this.route.snapshot.paramMap;
      const memberId = Number(routeParams.get('memberId'));
      this.member = this.members.find(member => member.PersonID === memberId);

      const [parties, memberships] = await apiService.fetchPartiesMembership(memberId);
      memberships.forEach(membership => {
        let party = parties.find(party => party.ID === membership.PartyID)
        if (!party) return;

        this.memberships.push({
          ...membership,
          party
        })
      })

      const websites = await apiService.fetchWebsites().then();
      this.websites = websites.filter(website => website.PersonID === this.member?.PersonID)
    }).then()
  }

  getBirthDate() {
    if(!this.member?.BirthDate) return "";
    const date = moment(this.member?.BirthDate);
    return date.format("MMMM DD, YYYY")
  }

  getFormattedDate(rawDate: string) {
    if(!rawDate) return "";
    const date = moment(rawDate);
    return date.format("MMMM DD, YYYY")
  }
}
