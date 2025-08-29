import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from "./main/main.component";
import {VoteFollowingComponent} from "./vote-following/vote-following.component";
import {VoteResultComponent} from "./vote-result/vote-result.component";

export const routes: Routes = [
  {path: 'main', redirectTo: '/', pathMatch: 'full'},
  {path: '', component: MainComponent},
  {path: '**', component: MainComponent},
  {path: 'following', component: VoteFollowingComponent},
  {path: 'result', component: VoteResultComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
