import { ArrayItemType } from './type-utils';

class ArrayUtils {
  /**
   * 插入一個值至指定 index
   * @param arr 插入陣列
   * @param index 插入 index
   * @param item 插入元素
   * @returns 插入陣列
   */
  public insert<T extends any[]>(
    arr: T,
    index: number,
    item: ArrayItemType<T>
  ): T {
    arr.splice(index, 0, item);
    return arr;
  }

  /**
   * 判斷是否為空
   * @param arr 判斷陣列
   * @returns 是否為空
   */
  public isEmpty(arr: any[]): boolean {
    return arr.length === 0;
  }

  /**
   * 獲取陣列第一項
   * @param arr 目標陣列
   * @returns 陣列第一項
   */
  public getFirst<T extends any[]>(arr: T): ArrayItemType<T> | undefined {
    if (this.isEmpty(arr)) return undefined;
    return arr[0];
  }
}
export const arrayUtils = new ArrayUtils();
