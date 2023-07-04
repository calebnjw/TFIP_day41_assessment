import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { IndexComponent } from './components/index/index.component';
import { ListComponent } from './components/list/list.component';
import { DetailsComponent } from './components/details/details.component';
import { ReviewComponent } from './components/review/review.component';

const appRoutes: Routes = [
  { path: '', component: IndexComponent, title: 'Book Reviews' },
  { path: 'index', component: IndexComponent },
  { path: 'search/:character', component: ListComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' },
];

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ListComponent,
    DetailsComponent,
    ReviewComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes, { useHash: true }),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
