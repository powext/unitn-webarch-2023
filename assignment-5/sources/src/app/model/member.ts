/*PersonID": 1771,
"PhotoURL": "",
  "Notes": "",
  "BirthDate": null,
  "BirthDateIsProtected": false,
  "ParliamentaryName": "Scott, Eleanor",
  "PreferredName": "Eleanor",
  "GenderTypeID": 1,
  "IsCurrent": false*/


export interface Member {
  PersonID: number,
  PhotoURL: string,
  BirthDate: string,
  BirthDateIsProtected: boolean,
  ParliamentaryName: string,
  PreferredName: string
  GenderTypeID: number,
  IsCurrent: boolean
}
