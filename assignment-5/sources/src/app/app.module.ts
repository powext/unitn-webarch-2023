import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './components/app.component';
import { MembersListComponent } from './components/members-list/members-list.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import {MatCardModule} from "@angular/material/card";
import { NgOptimizedImage } from '@angular/common';
import { MemberDetailsComponent } from './components/member-details/member-details.component';
import {RouterModule} from "@angular/router";
import {MatRippleModule} from "@angular/material/core";
import {MatIconModule} from "@angular/material/icon";

@NgModule({
  declarations: [
    AppComponent,
    MembersListComponent,
    ToolbarComponent,
    MemberDetailsComponent
  ],
  imports: [
    BrowserModule,
    MatToolbarModule,
    MatCardModule,
    RouterModule.forRoot([
      {path: '', component: MembersListComponent},
      {path: 'member/:memberId', component: MemberDetailsComponent},
    ]),
    MatRippleModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
