import { Component, ElementRef, HostListener, Renderer2, ViewChild } from '@angular/core';
import { SharedService } from '../../services/shared.service';
import { app } from '../../../../server';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  cart = [];
  totalAmmount: number = 0;
  productsInCart: number = 0;
  animationApplied: boolean = false;
  breakpointScroll: number = 65;
  buttonTopValue: string = '24px';
  animationTime: number = 600;

  @ViewChild('headerMenu', { static: true }) headerMenu?: ElementRef;
  @ViewChild('userButton', { static: true }) userButton?: ElementRef;
  @ViewChild('logo', { static: true }) logo?: ElementRef;

  constructor(public sharedService: SharedService, private renderer: Renderer2) {
  }

  ngAfterViewInit() {
    if(this.headerMenu && this.userButton) {
      setInterval(() => {
        this.applyAnimatiion(window.pageYOffset, this.headerMenu?.nativeElement, this.userButton?.nativeElement);
      } , 1000);
    }
  }
  addToCart() {

  }

  scrollTo(sectionId: string): void {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth', block: 'start' })
    }
  }
  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event): void {
    const scrollPosition = window.pageYOffset;

    if (this.headerMenu && this.userButton && this.logo) {
      const headerElement = this.headerMenu.nativeElement;
      const userButton = this.userButton.nativeElement;
      this.applyAnimatiion(scrollPosition, headerElement, userButton);
    }
  }

  applyAnimatiion(scrollPosition: number, headerElement: any, userButton: any) {
    if (scrollPosition > this.breakpointScroll && !this.animationApplied) {
      userButton.classList.remove('animation-down');

      headerElement.classList.remove('animation-down');
      headerElement.classList.add('header-fixed', 'animation-top');

      this.animationApplied = true;

      userButton.classList.add('animation-top');
      this.buttonTopValue = '39px';

      setTimeout(() => {
        headerElement.classList.remove('animation-top');
      }, this.animationTime);
    }
    else if (scrollPosition <= this.breakpointScroll && this.animationApplied) {
      headerElement.classList.remove('animation-top', 'header-fixed');
      headerElement.classList.add('animation-down');
      userButton.classList.remove('animation-top');

      userButton.classList.add('animation-down');
      this.buttonTopValue = '24px';
      this.animationApplied = false;
      setTimeout(() => {
        headerElement.classList.remove('animation-down');
      }, this.animationTime);
    }
  }
}
