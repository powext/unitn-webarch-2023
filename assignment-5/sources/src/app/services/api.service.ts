import { Injectable } from '@angular/core';
import {Member} from "../model/member";
import {imageExists} from "../utils";
import {Website} from "../model/website";
import {Party} from "../model/party";
import {Membership} from "../model/membership";
import * as moment from "moment";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  members?: Member[];
  websites?: Website[];
  parties?: Party[];
  memberParties?: Membership[];

  constructor() {
  }

  async fetchMembers(): Promise<Member[]> {
    if(this.members) return this.members;
    return new Promise(async (resolve, reject) => {
      const response = await fetch("https://data.parliament.scot/api/members");
      if (!response.ok) reject()

      const json = await response.json();
      json.map((member: any) => {
        const image = new Image();
        image.onload = () => {}
        image.onerror = () => {
          const err = new Image();
          member.PhotoURL = "assets/" + (member.GenderTypeID === 2 ? "woman.jpg" : "man.png")
        }
        image.src = member.PhotoURL;
      })
      resolve(json as Member[]);
    })
  }

  async fetchWebsites(): Promise<Website[]> {
    if(this.websites) return this.websites;
    const response = await fetch("https://data.parliament.scot/api/websites");
    return response.json();
  }

  async fetchPartiesMembership(personID: number): Promise<[Party[], Membership[]]> {
    if(!this.parties) {
      const response = await fetch("https://data.parliament.scot/api/parties");
      this.parties = await response.json() as Party[];
    }

    if(!this.memberParties) {
      const response = await fetch("https://data.parliament.scot/api/memberparties");
      this.memberParties = await response.json() as Membership[];
    }

    return [
      this.parties,
      this.memberParties
        .filter(membership => membership.PersonID === personID)
        .sort((a, b) => {
          if (moment(a.ValidFromDate).isBefore(b.ValidFromDate)) return -1;
          if (moment(a.ValidFromDate).isAfter(b.ValidFromDate)) return 1;
          return 0;
        })
    ]
  }
}
