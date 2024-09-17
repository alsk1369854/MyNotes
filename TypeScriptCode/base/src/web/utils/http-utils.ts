import { HttpErrorResponse } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';

export type ParseToUrlParamsOptions = {
  ignoreNull: boolean;
};

export type FormDataSource<T extends object> = {
  [key in keyof T]: string | Blob | string[] | Blob[] | null | undefined;
};

export class HttpUtils {
  /**
   * 將物件解析為 http 請求參數(請確保物件內只有原型參數)
   * { a:"a", b:1, c:null, d:undefined, e:true } => ?a=a&b=1&e=true
   * @param obj 解析物件
   * @param options 配置參數
   * @returns http 請求參數
   */
  public parseToUrlParams<T extends object>(
    obj: T,
    options: Partial<ParseToUrlParamsOptions> = {}
  ): string {
    const _options: ParseToUrlParamsOptions = {
      ignoreNull: true,
      ...options,
    };
    let params = [];
    for (const key in obj) {
      const value = obj[key as keyof typeof obj];
      const shouldIgnore =
        value === undefined || (_options.ignoreNull && value === null);
      if (shouldIgnore) continue;
      params.push(`${key}=${value}`);
    }
    return params.length > 0 ? `?${params.join('&')}` : '';
  }

  /**
   * 將物件解析為FormData
   * @param src 數據源
   * @returns FormData
   */
  public parseToFormData<T extends object>(src: FormDataSource<T>): FormData {
    let formData = new FormData();
    Object.entries(src).forEach(([key, value]) => {
      if (!value) return;
      if (!Array.isArray(value)) value = [value];
      (value as Array<string | Blob>).forEach((item) => {
        formData.append(key, item);
      });
    });
    return formData;
  }
}
export const httpUtils = new HttpUtils();
