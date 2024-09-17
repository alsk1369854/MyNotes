import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface CheckDialogData {
  title?: string;
  content?: string;
  confirmLabel?: string;
  closeLabel?: string;
  onConfirmCallback?: () => void;
  onCloseCallback?: () => void;
}

@Component({
  selector: 'app-check-dialog',
  templateUrl: './check-dialog.component.html',
  styleUrl: './check-dialog.component.scss',
})
export class CheckDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: CheckDialogData) {}
}
