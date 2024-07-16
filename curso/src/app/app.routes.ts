import { Routes } from '@angular/router';
import { HomeComponent } from './main';
import { CalculadoraComponent, DemosComponent } from './ejemplos';
import { ContactosAddComponent, ContactosEditComponent, ContactosListComponent, ContactosViewComponent } from './contactos';

export const routes: Routes = [
    {path: '',pathMatch:'full',component:HomeComponent},
    {path: 'inicio',component:HomeComponent},
    {path: 'demos',component:DemosComponent},
    {path: 'chisme/de/hacer/numeros',component:CalculadoraComponent},
    {path: 'contactos',component:ContactosListComponent},
    {path: 'contactos/:id',component:ContactosViewComponent},
    {path: 'contactos/:id/:kk',component:ContactosViewComponent},
    {path: 'contactos/:id/edit',component:ContactosEditComponent},
    {path: 'contactos/add',component:ContactosAddComponent},

];
