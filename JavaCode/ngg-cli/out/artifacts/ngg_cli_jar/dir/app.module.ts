import { MyComComponent } from './my-com/my-com.component';
import { MyComComponent } from './my-com/my-com.component';
import {MyComComponent} from './my-com/my-com.component';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ChartsComponent } from './site/charts/charts.component';

@NgModule({
  declarations: [
	MyComComponent,
MyComComponent,

    AppComponent,
    UseDataAnalysisChartsComponent,
    TestComponent
  ],
  providers: [],
  bootstrap: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxEchartsModule.forRoot({
      echarts: () => import('echarts')
    }),
    DataAnalysisChartsModule
  ]
})
export class AppModule { }
