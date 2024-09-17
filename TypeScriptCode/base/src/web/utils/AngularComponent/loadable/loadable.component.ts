import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-loadable',
  templateUrl: './loadable.component.html',
  styleUrl: './loadable.component.scss',
})
export class LoadableComponent {
  @Input({ required: true }) isLoading: boolean = false;
}
