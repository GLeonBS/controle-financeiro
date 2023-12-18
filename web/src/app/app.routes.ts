import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    title: 'Home',
    path: 'home',
  },
  {
    path: '',
    redirectTo: 'home',
  },
];
