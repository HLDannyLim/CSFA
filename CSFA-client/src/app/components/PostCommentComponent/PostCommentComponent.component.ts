import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from 'src/app/models/review';
import { SearchService } from 'src/app/services/search.service';
import { CustomValidators } from '../validators/custom-validators';
import { Comment } from 'src/app/models/comment';

@Component({
  selector: 'app-PostCommentComponent',
  templateUrl: './PostCommentComponent.component.html',
  styleUrls: ['./PostCommentComponent.component.css']
})
export class PostCommentComponent implements OnInit{
  name!:string;
  searchname!:string;
  form!: FormGroup;


  constructor(private activatedRoute: ActivatedRoute, 
    private router: Router, private searchSvc: SearchService, 
    private fb:FormBuilder ){}


  ngOnInit(): void {
    this.form = this.fb.group(
      { name: new FormControl('',[Validators.required, Validators.minLength(3), CustomValidators.notOnlyWhitespace]),
        rating: new FormControl('',[Validators.required]),
        comment: new FormControl('',[Validators.required, Validators.minLength(1), CustomValidators.notOnlyWhitespace])
      }
    )
    this.name = this.activatedRoute.snapshot.paramMap.get('name')!;
    this.searchname = this.activatedRoute.snapshot.paramMap.get('searchname')!;
    console.log(this.name);
    console.log(this.searchname);
  }

  // constructor(public movieName:string, public posterName: string, public rating: number,
  //   public commentText: string){}

  async post() {
    const c = {} as Comment;
    c.name = this.name;
    c.posterName = this.form?.value['name'];
    c.rating = this.form?.value['rating'];
    c.commentText = this.form?.value['comment'];

    const l = await this.searchSvc.saveToMongo(c);
    
    const a = l;
    console.log(a.chamovieNamerId);
    console.log(a.posterName);
    console.log(a.rating);
    console.log(a.commentText);

    this.router.navigate(['MovieReviewsListComponent/'+ this.searchname]);
  }

  goback(){
    console.log(this.searchname);
    this.router.navigate(['MovieReviewsListComponent/'+ this.searchname]);
  }

}
