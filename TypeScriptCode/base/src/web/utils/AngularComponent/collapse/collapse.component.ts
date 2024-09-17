import {
  trigger,
  state,
  style,
  transition,
  animate,
} from '@angular/animations';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-collapse',
  templateUrl: './collapse.component.html',
  styleUrl: './collapse.component.scss',
  animations: [
    trigger('openClose', [
      // 定義狀態為 'open' 時的樣式
      state(
        'open',
        style({
          visibility: 'visible',
          height: '*',
        })
      ),

      // 定義狀態為 'close' 時的樣式
      state(
        'close',
        style({
          visibility: 'hidden',
          height: '0px',
        })
      ),

      // 用 transition 定義狀態轉換時，元件該怎麼動作
      transition('* => close', [
        animate(
          '0.6s ease-out',
          style({ visibility: 'hidden', height: '0px' })
        ),
      ]),
      transition('* => open', [
        style({ visibility: 'visible' }),
        animate('0.6s ease-out', style({ height: '*' })),
      ]),
    ]),
  ],
})
export class CollapseComponent {
  @Input() title: string = '';
  @Input() isOpen: boolean = true;
  @Input() isSwitchable: boolean = true;
  @Input() titlePosition: 'start' | 'center' | 'end' = 'start';
  @Input() disabled: boolean = false;

  /**
   * 標頭點擊事件處理函示
   */
  public onHeaderClick(): void {
    if (this.isSwitchable) {
      this.isOpen = !this.isOpen;
    }
  }
}
