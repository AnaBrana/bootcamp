import { Routes } from '@angular/router';
import { HomeComponent, PageNotFoundComponent } from './main';
import { ActoresAddComponent, ActoresEditComponent, ActoresListComponent, ActoresViewComponent } from './actores';
import { IdiomasAddComponent, IdiomasEditComponent, IdiomasListComponent, IdiomasViewComponent } from './idiomas';
import { CategoriasAddComponent, CategoriasEditComponent, CategoriasListComponent } from './categorias';
import { CategoriasViewModelService } from './categorias/servicios.service';
import { FilmsAddComponent, FilmsEditComponent, FilmsListComponent, FilmsViewComponent } from './peliculas';

export const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'inicio', component: HomeComponent },

  { path: 'actores', component: ActoresListComponent},
  { path: 'actores/add', component: ActoresAddComponent},
  { path: 'actores/:id', component: ActoresViewComponent},
  { path: 'actores/:id/edit', component: ActoresEditComponent},
  { path: 'actores/:id/delete', component: ActoresListComponent},

  { path: 'categories', component: CategoriasListComponent},
  { path: 'categories/add', component:CategoriasAddComponent},
  { path: 'categories/:id', component: CategoriasViewModelService},
  { path: 'categories/:id/edit', component: CategoriasEditComponent},
  { path: 'categories/:id/delete', component: CategoriasListComponent},

  { path: 'languages', component:IdiomasListComponent},
  { path: 'languages/add', component:IdiomasAddComponent},
  { path: 'languages/:id', component:IdiomasViewComponent},
  { path: 'languages/:id/edit', component: IdiomasEditComponent},
  { path: 'languages/:id/delete', component: IdiomasListComponent},

  { path: 'films', component:FilmsListComponent},
  { path: 'films/add', component:FilmsAddComponent},
  { path: 'films/:id', component:FilmsViewComponent},
  { path: 'films/:id/edit', component: FilmsEditComponent},
  { path: 'films/:id/delete', component: FilmsEditComponent},





  { path: '**', component: PageNotFoundComponent },
];
