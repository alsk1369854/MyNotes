class StringUtils {
  /**
   * 變更索引字串
   * @param str 原字串
   * @param index 索引
   * @param replacement 要替換索引位置的字串
   * @returns 替換後的新字串
   */
  public replaceCharacter(str: string, index: number, replacement: string) {
    return (
      str.slice(0, index) + replacement + str.slice(index + replacement.length)
    );
  }
}

export const stringUtils = new StringUtils();
