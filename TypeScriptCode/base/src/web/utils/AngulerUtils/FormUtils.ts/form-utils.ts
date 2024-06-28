import { FormControl, FormGroup } from "@angular/forms";

export type IMappingFormGroup<T> = FormGroup<{
  [_key in keyof T]: T[_key] extends (infer U)[]
    ? FormControl<U[] | null>
    : T[_key] extends object
    ? IMappingFormGroup<T[_key]>
    : FormControl<T[_key] | null>;
}>;

class FormUtils {
  /**
   * 獲取表單數據映射
   * @returns 表單數據物件
   */
  public castFormValue<T>(formGroup: IMappingFormGroup<T>): T {
    return formGroup.value as T;
  }
}

export const formUtils: FormUtils = new FormUtils();
