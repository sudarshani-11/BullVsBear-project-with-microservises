import { FullscreenOverlayContainer } from '@angular/cdk/overlay';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent} from './register/register.component'
import {StocksComponent } from './stocks/stocks.component'
import {PortfolioComponent} from './portfolio/portfolio.component'
const routes: Routes = [
  {
    path:"login",
    component:LoginComponent
    
  },
  {
    path:"register",
    component:RegisterComponent
    
  },
  {
    path:"stock",
    component:StocksComponent
    
  },
  {
    path:"portfolio",
    component:PortfolioComponent ,
    canActivate:[AuthGuard]   
  },
  { path: '**', component: HomeComponent },
  {
    path:"home ",
    component: HomeComponent,
    pathMatch:"full"
  }
  
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
