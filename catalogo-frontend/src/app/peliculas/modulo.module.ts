import { NgModule } from '@angular/core';
import { FILMS_COMPONENTES, FilmsAddComponent, FilmsEditComponent, FilmsListComponent, FilmsViewComponent } from './componente.component';
import { RouterModule, Routes } from '@angular/router';
import { InRoleCanActivate } from '../security';

export const routes: Routes = [
  { path: '', component: FilmsListComponent },
  { path: 'add', component: FilmsAddComponent, canActivate: [ InRoleCanActivate('Films')] },
  { path: ':id/edit', component: FilmsEditComponent, canActivate: [ InRoleCanActivate('Films')] },
  { path: ':id', component: FilmsViewComponent, canActivate: [ InRoleCanActivate('Films')] },
  { path: ':id/:kk', component: FilmsViewComponent, canActivate: [ InRoleCanActivate('Films')] },
]
@NgModule({
  declarations: [],
  imports: [ FILMS_COMPONENTES, RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class FilmsModule { }
