import {enableProdMode, importProvidersFrom} from '@angular/core';
import {bootstrapApplication} from '@angular/platform-browser';

import {AppComponent} from './app/app.component';
import {environment} from './environments/environment';

import {provideRouter, withComponentInputBinding} from '@angular/router';
import {provideHttpClient} from '@angular/common/http';
import {MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import {MatRadioModule} from '@angular/material/radio';
import {FormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {FlexModule} from '@angular/flex-layout';
import {provideAnimations} from '@angular/platform-browser/animations';
import {CookieService} from 'ngx-cookie-service';
import {routes} from "./app/app-routing.module";

if (environment.production) {
  enableProdMode();
}

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes, withComponentInputBinding()),
    provideHttpClient(),
    provideAnimations(),
    importProvidersFrom(
      MatCardModule,
      MatListModule,
      MatRadioModule,
      FormsModule,
      MatButtonModule,
      FlexModule
    ),
    CookieService
  ]
}).catch(err => console.error(err));
