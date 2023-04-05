import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchViewComponent } from './components/SearchViewComponent/SearchViewComponent';
import { MovieReviewsListComponent } from './components/MovieReviewsListComponent/MovieReviewsListComponent.component';
import { PostCommentComponent } from './components/PostCommentComponent/PostCommentComponent.component';

const routes: Routes = [
  {path:'', component: SearchViewComponent },
  {path: 'MovieReviewsListComponent/:name', component: MovieReviewsListComponent},
  {path: 'PostCommentComponent/:name/:searchname', component: PostCommentComponent},
  { path: '**', redirectTo: '', pathMatch: 'full'} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
