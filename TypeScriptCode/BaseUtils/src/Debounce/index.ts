/**
 * 創建防抖函數
 * @param func 目標函數
 * @param duration 時間間隔(毫秒)
 * @returns 防抖函數
 */
export function debounce<A extends any[]>(
  func: (...args: A) => any,
  duration: number = 5000
): (...args: A) => void {
  let timerId: NodeJS.Timeout | null = null;
  return function (...args: any[]) {
    if (timerId) {
      clearTimeout(timerId);
    }
    timerId = setTimeout(() => {
      //@ts-ignore
      func.apply(this, args);
    }, duration);
  };
}
