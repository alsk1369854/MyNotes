import { FormArray, FormControl, FormGroup } from '@angular/forms';

export type MappingFormArray<T extends any[]> = T extends (infer U)[]
  ? U extends object
    ? FormArray<MappingFormGroup<U>>
    : FormControl<T | null>
  : never;

export type MappingFormGroup<T> = FormGroup<{
  [_key in keyof T]: T[_key] extends any[]
    ? MappingFormArray<T[_key]>
    : T[_key] extends object
    ? MappingFormGroup<T[_key]>
    : FormControl<T[_key] | null>;
}>;

class FormUtils {
  /**
   * 轉型為 "表單數據" 類型
   * @param group 表單群組物件
   * @returns 表單數據
   */
  public castFormValue<T>(group: MappingFormGroup<T>): T {
    return group.value as T;
  }

  /**
   * 是否應該顯示 "警告文字"
   * @param control 表單控制物件
   * @returns 是否顯示 "警告文字"
   */
  public shouldDisplayAlert(control: FormControl): boolean {
    return control.invalid && (control.dirty || control.touched);
  }

  /**
   * 觸發所有必須顯示的 "警告文字"
   * @param group 表單群組物件
   */
  public showValidationErrors(group: FormGroup): void {
    Object.keys(group.controls).forEach((key) => {
      const control = group.get(key)!;
      if (control instanceof FormGroup) {
        this.showValidationErrors(control);
      } else {
        control.markAsTouched();
      }
    });
  }
}

export const formUtils: FormUtils = new FormUtils();
