import { FormGroup } from "@angular/forms";
import { MappingFormControls } from "./interfaces";

class FormUtils {
  /**
   * 獲取表單數據映射
   * @returns 表單數據物件
   */
  public getFormGroupValue<T>(formGroup: FormGroup<MappingFormControls<T>>): T {
    return formGroup.value as T;
  }
}

export const formUtils = new FormUtils();
