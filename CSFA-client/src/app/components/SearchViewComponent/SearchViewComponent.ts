import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomValidators } from '../validators/custom-validators';

@Component({
  selector: 'app-SearchViewComponent',
  templateUrl: './SearchViewComponent.component.html',
  styleUrls: ['./SearchViewComponent.component.css']
})
export class SearchViewComponent implements OnInit{
  constructor(private router: Router, 
    private fb:FormBuilder){}

    form!: FormGroup;
    name!:string;

    ngOnInit(): void {
      this.form = this.fb.group(
        { name: new FormControl('',[Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace]),
        }
      )
    }

    search(){
      const formVal = this.form.value;
      console.log(formVal);
        this.name = formVal['name'];
        this.router.navigate(['MovieReviewsListComponent/'+ this.name]);
    }



}
