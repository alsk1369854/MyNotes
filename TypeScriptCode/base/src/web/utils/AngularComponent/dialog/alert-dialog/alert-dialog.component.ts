import { Component, Inject, Input } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';

export interface AlertDialogData {
  title: string;
  content: string;
  confirm?: string;
  close?: string;
}

@Component({
  selector: 'app-alert-dialog',
  templateUrl: './alert-dialog.component.html',
  styleUrl: './alert-dialog.component.scss',
})
export class AlertDialogComponent {
  @Input() title!: string;
  @Input() content!: string;
  @Input() confirm!: string;
  @Input() close!: string;

  constructor(
    public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: AlertDialogData
  ) {}

  ngOnInit(): void {
    this.setDataToStr();
  }

  setDataToStr() {
    if (this.data.title) {
      this.title = this.data.title;
    }
    if (this.data.content) {
      this.content = this.data.content;
    }
    if (this.data.confirm) {
      this.confirm = this.data.confirm;
    }
    if (this.data.close) {
      this.close = this.data.close;
    }
  }
}
