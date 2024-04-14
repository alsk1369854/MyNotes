class RandomUtils {
  /**
   * 產生隨機色碼 #000000 ~ #ffffff
   * @returns 隨機色碼
   */
  public randomColor(): string {
    return "#" + Math.random().toString(16).substring(2, 8).padEnd(6, "0");
  }

  /**
   * 變換數值
   * @param baseNumber 基準數值
   * @param changeRange 變動幅度
   * @param maximum 數值上限
   * @param minimum 數值下限
   * @returns 新數值
   */
  public changeFloat(
    base: number,
    changeRange: number,
    maximum: number,
    minimum: number
  ): number {
    let result: number = base;
    const changeAmount: number = (Math.random() * changeRange) / 2;
    if (Math.random() > 0.5) {
      result += changeAmount;
    } else {
      result -= changeAmount;
    }
    if (result > maximum) return maximum;
    if (result < minimum) return minimum;
    return result;
  }

  /**
   * 變換數值
   * @param baseNumber 基準數值
   * @param changeRange 變動幅度
   * @param maximum 數值上限
   * @param minimum 數值下限
   * @returns 新數值
   */
  public changeInt(
    base: number,
    changeRange: number,
    maximum: number,
    minimum: number
  ): number {
    return Math.floor(this.changeFloat(base, changeRange, maximum, minimum));
  }

  /**
   * 產生隨機長度字串
   * @param len 字串長度
   * @returns 隨機字串
   */
  public randomString(len: number = 6): string {
    if (len <= 11) {
      return Math.random()
        .toString(36)
        .substring(2, len + 2)
        .padEnd(len, "0");
    } else {
      return this.randomString(11) + this.randomString(len - 11);
    }
  }
}

export const randomUtils = new RandomUtils();
