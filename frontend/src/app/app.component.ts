import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <div class="container-fluid">
      <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
        <div class="container">
          <a class="navbar-brand" href="#">Sistema de Parqueadero</a>
        </div>
      </nav>
      <app-vehicle-list></app-vehicle-list>
    </div>
  `,
  styles: []
})
export class AppComponent {
  title = 'parking-system-frontend';
} 