import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './main/main.component';
import { VoteResultComponent } from './vote-result/vote-result.component';
import { VoteFollowingComponent } from './vote-following/vote-following.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    VoteResultComponent,
    VoteFollowingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
