import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    title: 'Home',
    path: 'home',
    loadChildren: () =>
      import('./home/home.module').then((m) => m.HomeModule),
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
];
