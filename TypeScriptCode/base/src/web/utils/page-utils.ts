import {
  PageRequest,
  PageResponse,
  PageState,
} from 'src/app/models/common.model';
import { environment } from 'src/environments/environment';
import { ArrayItemType } from './type-utils';

interface GetInitPageStateOptions {
  defaultPageState: Partial<PageState>;
  afterChangeCallback: () => void;
}

class PageUtils {
  /**
   * 獲取分頁數據的第一項數編號
   * @param currPageAt 當前頁面(必須 > 0)
   * @param pageSize 單頁數據量(必須 >= 0)
   * @returns 當前分頁的第一項數據編號
   */
  public getDataFirstCountNumber(currPageAt: number, pageSize: number): number {
    if (currPageAt <= 0) {
      throw new Error(`currPageAt 必須大於 0, 當前值為: ${currPageAt}`);
    }
    if (pageSize < 0) {
      throw new Error(`pageSize 必須大於等於 0, 當前值為: ${pageSize}`);
    }
    return (currPageAt - 1) * pageSize + 1;
  }

  /**
   * 獲取分頁訊息
   * @param dataset 全部資料集
   * @param pageRequest 分頁請求
   * @returns 分頁數據
   */
  public getPageData<T extends any[]>(
    dataset: T,
    pageRequest: PageRequest = {}
  ): PageResponse<ArrayItemType<T>> {
    const pageAt = pageRequest.currentPage ?? 1;
    const onePageLimit = pageRequest.take ?? 10;
    const startIndex = (pageAt - 1) * onePageLimit;
    const endIndex = startIndex + onePageLimit;
    return {
      totalPages: Math.ceil(dataset.length / onePageLimit),
      totalCount: dataset.length,
      datas: dataset.slice(startIndex, endIndex),
    };
  }

  /**
   * 獲取初始化分頁器狀態
   * @returns 初始化分頁器狀態
   */
  public getInitPageState(
    options: Partial<GetInitPageStateOptions> = {}
  ): PageState {
    const securityOnePageSize = sessionStorage.getItem(
      environment.SESSION_STORAGE_ONE_PAGE_SIZE_KEY
    );
    let state: PageState = {
      currentPageAt: 1,
      size: securityOnePageSize ? +securityOnePageSize : 10,
      totalCount: 0,
      ...(options.defaultPageState ?? {}),
    };
    return new Proxy(state, {
      set: (target: PageState, prop: keyof PageState, newValue: any) => {
        target[prop] = newValue;
        if (prop === 'size' || prop === 'currentPageAt') {
          if (options.afterChangeCallback) {
            options.afterChangeCallback();
          }
        }
        return true;
      },
    });
  }
}

export const pageUtils = new PageUtils();
