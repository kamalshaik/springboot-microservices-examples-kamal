import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { AuthGuard } from './_guards';
import { CreateNewUserComponent } from './newuser/create.newuser.component';
import { AdminComponent } from './admin/admin.component';
import { CareersComponent } from './careers/careers.component';
import { ServicesComponent } from './services/services.component';

const appRoutes: Routes = [
    {
        path: '',
        component: HomeComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'newUser',
        component: CreateNewUserComponent
    },
    {
        path: 'admin',
        component: AdminComponent
    },
    {
        path: 'services',
        component: ServicesComponent
    },
    {
        path: 'careers',
        component: CareersComponent
    },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes, { useHash: true });