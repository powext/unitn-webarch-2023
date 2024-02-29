/*
* ID": 62,
    "PersonID": 1892,
    "PartyID": 3,
    "ValidFromDate": "2003-05-01T00:00:00",
    "ValidUntilDate": "2007-04-02T23:59:59",
    "MemberPartyRoles": null
* */


export interface Membership {
  PersonID: number,
  PartyID: number,
  ValidFromDate: string,
  ValidUntilDate: string
}
