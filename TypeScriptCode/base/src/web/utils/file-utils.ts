class FileUtils {
  /**
   * 下載檔案
   * @param file 檔案數據
   * @param fileName 下載檔案名稱
   */
  public downloadFile(file: Blob, fileName: string): void {
    const aEle = document.createElement<'a'>('a');
    aEle.href = URL.createObjectURL(file);
    aEle.download = fileName;
    aEle.click();
  }
}

export const fileUtils = new FileUtils();
