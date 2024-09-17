import { StringDateTime } from 'src/app/models/common.model';
import { switchUtils } from './switch-utils';

const dateUnitTypes = [
  'year',
  'month',
  'day',
  'hour',
  'minute',
  'second',
] as const;
type DateUnitType = (typeof dateUnitTypes)[number];

class DateUtils {
  /**
   * 獲取時間範圍
   * @param start 期始時間
   * @param end 終止時間
   * @param sep 間隔時間誇度 (default = year)
   * @returns 時間範圍
   */
  public range(start: Date, end: Date, sep: DateUnitType = 'year'): Date[] {
    if (start > end) {
      return [];
    }
    let curr = new Date(start.valueOf());
    let result = [new Date(curr.valueOf())];
    while (curr < end) {
      switch (sep) {
        case 'year':
          curr.setFullYear(curr.getFullYear() + 1);
          break;
        case 'month':
          curr.setMonth(curr.getMonth() + 1);
          break;
        case 'day':
          curr.setDate(curr.getDate() + 1);
          break;
        case 'hour':
          curr.setHours(curr.getHours() + 1);
          break;
        case 'minute':
          curr.setMinutes(curr.getMinutes() + 1);
          break;
        case 'second':
          curr.setSeconds(curr.getSeconds() + 1);
          break;
        default:
          return switchUtils.assertToNever(sep);
      }
      if (curr < end) {
        result.push(new Date(curr.valueOf()));
      }
    }
    return result;
  }

  /**
   * 將日期物件轉換成指定字串格式
   * @param date 日期物件
   * @param format 格式化模板
   * @returns 字串日期
   */
  public formatDate(date: Date, format: string): string {
    const padZero = (value: number): string => {
      return value < 10 ? `0${value}` : value.toString();
    };

    const year = date.getFullYear();
    const month = padZero(date.getMonth() + 1); // 月份從 0 開始
    const day = padZero(date.getDate());
    const hour = padZero(date.getHours());
    const minute = padZero(date.getMinutes());

    return format
      .replace('yyyy', year.toString())
      .replace('MM', month)
      .replace('dd', day)
      .replace('HH', hour)
      .replace('mm', minute);
  }

  /**
   * 將字串解析為日期物件
   * @param stringDateTime 字串
   * @returns 日期物件
   */
  public parseToDate(stringDateTime: StringDateTime): Date {
    return new Date(stringDateTime);
  }
}

export const dateUtils = new DateUtils();
