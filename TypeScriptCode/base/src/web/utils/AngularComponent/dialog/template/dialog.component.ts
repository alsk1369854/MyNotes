import {
  AfterViewInit,
  Component,
  Inject,
  OnInit,
  TemplateRef,
} from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

// 自定義的頁底模板
// 會傳入 {onCloseClick, onConfirmClick}
export interface CustomDialogFooterTemplateRef<TemplateRefType = any>
  extends TemplateRef<any> {}

export interface DialogDate<CustomEventType = any> {
  templateRef: TemplateRef<any>; // 主內容區的 templateOutlet
  templateContext?: object; // 主內容區的 templateOutlet content 參數
  title?: string; // 標題文字
  confirmLabelName?: string; // 自定義確認標籤名 預設 "確認"
  closeLabelName?: string; // 自定義關閉標籤名 預設 "取消"
  footerTemplateRef?: CustomDialogFooterTemplateRef<CustomEventType>; // 自定義腳頁
  onInit?: (dialogState: DialogState<undefined>) => void; // 初始化事件
  afterViewInit?: (dialogState: DialogState<undefined>) => void; // 畫面渲染結束後初始化事件
  onConfirmClickCallback?: (dialogState: DialogState<CustomEventType>) => void; // 確認點擊事件回調(預設腳頁，要有配置此項才會顯示 "確認按鈕")
  onCloseClickCallback?: (dialogState: DialogState<CustomEventType>) => void; // 取消點擊事件回調
}

export interface DialogState<T = any> {
  dialogRef: MatDialogRef<DialogComponent>;
  isLoading: boolean; // 當前是否處於載入狀態
  setIsLoading: (newState: boolean) => void; // 設定載入狀態
  customEvent?: T; // 自定義事件，搭配 footerTemplateRef 使用
}
// 轉型為自定義的函數處理器，搭配 footerTemplateRef 使用
export type CustomDialogFooterClickHandler<CustomEventType = any> = (
  customEvent?: CustomEventType
) => void;

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrl: './dialog.component.scss',
})
export class DialogComponent implements OnInit, AfterViewInit {
  public isLoading: boolean = false;

  public getState<T>(customEvent?: T): DialogState<T> {
    return {
      dialogRef: this.dialogRef,
      isLoading: this.isLoading,
      setIsLoading: (newState) => (this.isLoading = newState),
      customEvent,
    };
  }

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: DialogDate,
    private dialogRef: MatDialogRef<DialogComponent>
  ) {}

  ngOnInit(): void {
    const { onInit } = this.data;
    if (onInit) onInit(this.getState());
  }

  ngAfterViewInit(): void {
    const { afterViewInit } = this.data;
    if (afterViewInit) setTimeout(() => afterViewInit(this.getState()), 0);
  }

  /**
   * "確認" 點擊事件處理函式
   * @param customEvent 自定義事件
   */
  public onConfirmClick = (customEvent: any = undefined): void => {
    const { onConfirmClickCallback } = this.data;
    if (onConfirmClickCallback)
      onConfirmClickCallback(this.getState(customEvent));
  };

  /**
   * "關閉" 點擊事件處理函式
   * @param customEvent 自定義事件
   */
  public onCloseClick = (customEvent: any = undefined): void => {
    const { onCloseClickCallback } = this.data;
    const callback = onCloseClickCallback
      ? onCloseClickCallback // 自定義關閉處理
      : () => this.dialogRef.close(); // 預設直接關閉
    callback(this.getState(customEvent));
  };
}
