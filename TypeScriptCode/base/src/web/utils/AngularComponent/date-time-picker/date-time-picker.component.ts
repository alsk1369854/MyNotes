import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  OnChanges,
  OnDestroy,
  SimpleChanges,
  ViewChild,
  forwardRef,
} from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import flatpickr from 'flatpickr';
import confirmDatePlugin from 'flatpickr/dist/plugins/confirmDate/confirmDate';
import { MandarinTraditional } from 'flatpickr/dist/l10n/zh-tw.js';

type SingleInstance<T> = T extends (infer U)[] ? U : T;
type FlatpickrInstance = SingleInstance<ReturnType<typeof flatpickr>>;

@Component({
  selector: 'app-date-time-picker',
  templateUrl: './date-time-picker.component.html',
  styleUrl: './date-time-picker.component.scss',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: forwardRef(() => DateTimePickerComponent),
    },
  ],
})
export class DateTimePickerComponent
  implements AfterViewInit, ControlValueAccessor, OnDestroy, OnChanges
{
  @ViewChild('dateTimePicker') dateTimePicker!: ElementRef<HTMLInputElement>;

  @Input({ required: true }) inputID!: string;
  @Input() disabled: boolean = false;
  @Input() enableTime: boolean = true;
  @Input() className: string = '';
  @Input() min?: Date | string | null;
  @Input() max?: Date | string | null;

  private value?: string;
  private flatpickrInstance?: FlatpickrInstance;
  private touched = false;
  private onChange!: (val: string | null) => void;
  private onTouched!: () => void;

  constructor() {}

  ngAfterViewInit(): void {
    this.flatpickrInstance = this.getFlatpickrInstance();
  }

  ngOnChanges(changes: SimpleChanges): void {
    const isMaxChange = changes['max'] !== undefined;
    const isMinChange = changes['min'] !== undefined;
    if (this.flatpickrInstance && (isMaxChange || isMinChange)) {
      this.flatpickrInstance.set({
        disable: [(date) => this.disableDate(date)],
      });
    }
  }

  ngOnDestroy(): void {
    if (this.flatpickrInstance) this.flatpickrInstance.destroy();
  }

  writeValue(value: string): void {
    this.value = value;
    if (this.flatpickrInstance) this.flatpickrInstance.setDate(value);
  }

  registerOnChange(fn: (val: string | null) => void): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  /**
   * 日曆 icon 點擊事件處理函式
   * @param event 點擊事件
   */
  public onCalenderIconClick(event: MouseEvent): void {
    event.preventDefault();
    if (!this.flatpickrInstance) return;
    this.flatpickrInstance.toggle();
  }

  /**
   * 刪除點擊事件處理函式
   * @param event 點擊事件
   */
  public onClearIconClick(event: MouseEvent): void {
    event.preventDefault();
    if (!this.flatpickrInstance) return;
    this.flatpickrInstance.clear();
  }

  /**
   * 數據變化
   * @param value 變化數據
   */
  private onValueChange(value: string) {
    this.value = value;
    // 通知 form control onChange
    this.onChange(value === '' ? null : value);

    // 數據第一次修改
    if (this.touched) return;
    this.touched = true;
    // 通知 form control onTouched
    this.onTouched();
  }

  /**
   * 判斷兩個日期 年月日 是否相同
   * @param date1 日期1
   * @param date2 日期2
   * @returns 兩個日期 年月日 是否相同
   */
  private isDateEqual(date1: Date, date2: Date): boolean {
    return (
      date1.getFullYear() === date2.getFullYear() &&
      date1.getMonth() === date2.getMonth() &&
      date1.getDate() === date2.getDate()
    );
  }

  /**
   * 是否要禁止這個日期的選取
   * @param date 日期
   * @returns 是否禁止
   */
  private disableDate(date: Date): boolean {
    // 為當前選擇日期
    const isCurrentSelectedDate =
      this.value && this.isDateEqual(new Date(this.value), date);
    if (isCurrentSelectedDate) return false;

    // 小於最小時間限制
    const isLessThenMinDate = this.min && date < new Date(this.min);
    // 大於最大時間限制
    const isGreaterThenMaxDate = this.max && date > new Date(this.max);
    if (isLessThenMinDate || isGreaterThenMaxDate) return true;

    return false;
  }

  /**
   * 獲取 Flatpickr 日期選擇工具實例
   * @returns Flatpickr 日期選擇工具實例
   */
  private getFlatpickrInstance(): FlatpickrInstance {
    return flatpickr(this.dateTimePicker.nativeElement, {
      locale: MandarinTraditional,
      defaultDate: this.value,
      enableTime: this.enableTime,
      dateFormat: this.enableTime ? 'Y/m/d H:i' : 'Y/m/d',
      time_24hr: true,
      onChange: (...args) => {
        const currentDateString = args[1];
        this.onValueChange(currentDateString);
      },
      plugins: [
        confirmDatePlugin({
          confirmIcon: '',
          confirmText: '確定',
          showAlways: true,
          theme: 'light', // or "dark"
        }),
      ],
      disable: [(date) => this.disableDate(date)],
    });
  }
}
