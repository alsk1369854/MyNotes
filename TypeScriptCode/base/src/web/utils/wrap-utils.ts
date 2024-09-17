class WrapUtils {
  /**
   * 包裝防抖函數
   * @param func 目標函數
   * @param ms 防抖毫秒
   * @returns 防抖函數
   */
  public debounce<T extends (...args: any[]) => any>(
    func: T,
    ms: number = 200
  ): (...args: Parameters<T>) => void {
    let timeout: NodeJS.Timeout;
    return function (...args: Parameters<T>): void {
      clearTimeout(timeout);
      timeout = setTimeout(() => {
        func(...args);
      }, ms);
    };
  }

  /**
   * 當調用參與與上次不同才執行函數
   * @param func 被包裝函數
   * @param isChanged 自定義是否變化回調
   * @returns 包裝後的函數
   */
  public distinctUntilChanged<T extends (...args: any[]) => any>(
    func: T,
    isChanged?: (
      previousArgs: Parameters<T> | undefined,
      currentArgs: Parameters<T>
    ) => boolean
  ): (...args: Parameters<T>) => ReturnType<T> | undefined {
    let previousArgs: Parameters<T> | undefined;
    isChanged = isChanged
      ? isChanged
      : (previousArgs, currentArgs) => {
          if (previousArgs === undefined) return true;
          if (previousArgs.length !== currentArgs.length) return true;
          return previousArgs.some((arg, index) => arg !== currentArgs[index]);
        };
    return function (...currentArgs: Parameters<T>): ReturnType<T> | undefined {
      const hasChanged = isChanged(previousArgs, currentArgs);
      if (hasChanged) {
        previousArgs = currentArgs;
        return func(...currentArgs);
      }
      // 如果參數沒變化，返回 undefined 表示沒有調用原函數
      return undefined;
    };
  }
}

export const wrapUtils = new WrapUtils();
