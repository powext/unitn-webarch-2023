/*
{
    "ID": 1,
    "Abbreviation": "Ind",
    "ActualName": "Independent",
    "PreferredName": "Independent",
    "Notes": "",
    "ValidFromDate": "2002-05-02T00:00:00",
    "ValidUntilDate": null,
    "MemberParties": null,
    "PartyRoles": null
  },
  */


export interface Party {
  ID: number,
  Abbreviation: string,
  ActualName: string,
  PreferredName: string,
  Notes: string,
  ValidFromDate: string
  ValidUntilDate: string,
}
