import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from 'src/app/models/review';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-MovieReviewsListComponent',
  templateUrl: './MovieReviewsListComponent.component.html',
  styleUrls: ['./MovieReviewsListComponent.component.css']
})
export class MovieReviewsListComponent implements OnInit{
  name!:string;
  movie : Review[] = [];

  constructor(private activatedRoute: ActivatedRoute, 
    private router: Router, private searchSvc: SearchService ){}


  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(()=> {
      this.listMovie();
    });
  }


  listMovie() {
    this.name = this.activatedRoute.snapshot.paramMap.get('name')!;
    console.log(this.name);
    this.searchSvc.getMovie(this.name).subscribe(
      data => {
        console.log('movie review =' + JSON.stringify(data));
        this.movie = data
      }
    );
    // this.searchSvc.updateTotalMovie(this.movie.length);
  }

  goCommentPage(value:string){
    this.router.navigate(['PostCommentComponent', value , this.name]);
  }



}
