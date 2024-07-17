import { Routes } from '@angular/router';
import { HomeComponent, PageNotFoundComponent } from './main';
import { ActoresListComponent, ActoresViewComponent } from './actores';
import { IdiomasListComponent, IdiomasViewComponent } from './idiomas';

export const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'inicio', component: HomeComponent },
  { path: 'actores', component: ActoresListComponent},
  { path: 'actores/:id', component: ActoresViewComponent},
  { path: 'idiomas', component:IdiomasListComponent},
  { path: 'idiomas/:id', component:IdiomasViewComponent},



  { path: '**', component: PageNotFoundComponent },
];
