import { Component } from '@angular/core';
import { WeComponent } from "./we/we.component";
import { CategoriesComponent } from "./categories/categories.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [WeComponent, CategoriesComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
