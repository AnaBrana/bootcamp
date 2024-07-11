import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SecurityModule } from './security';
import { LoggerService, MyCoreModule } from '@my/core'
import { DemosComponent } from './demos/demos.component';
import { NotificationModalComponent } from './main/notification-modal/notification-modal.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SecurityModule,MyCoreModule,NotificationModalComponent, DemosComponent, ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title: string = 'World';

  constructor(log: LoggerService){
    log.error('Es un error')
    log.warn('Es un warn')
    log.info('Es una info')
    log.log('Es un log')
  }
}
