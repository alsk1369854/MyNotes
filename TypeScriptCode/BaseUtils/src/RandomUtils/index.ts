export class RandomUtils {
  /**
   * 產生隨機色碼 #000000 ~ #ffffff
   * @returns 隨機色碼
   */
  public randomColor(): string {
    return "#" + Math.random().toString(16).substring(2, 8).padEnd(6, "0");
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
