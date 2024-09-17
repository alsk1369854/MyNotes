import { Observable } from 'rxjs';

class RxjsUtils {
  // 將 Observable 轉換成 Promise
  public observableToPromise<T>(observable: Observable<T>): Promise<T> {
    return new Promise((resolve, reject) => {
      observable.subscribe({
        next: resolve,
        error: reject,
      });
    });
  }
}

export const rxjsUtils = new RxjsUtils();
