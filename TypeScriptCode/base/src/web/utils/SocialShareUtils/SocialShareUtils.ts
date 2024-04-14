class SocialShareUtils {
  public readonly TEST_SHARE_URL: string = "https://m.facebook.com/WMG2025/";

  /**
   * 分享到LINE
   * @param shareUrl 要分享的網頁地址
   */
  public shareToLine(shareUrl: string): void {
    const basicShareUrl: string =
      "https://social-plugins.line.me/lineit/share?url=";
    const fullShareUrl: string = basicShareUrl + shareUrl;
    window.open(fullShareUrl);
  }

  /**
   * 分享到Fackbook
   * @param shareUrl 要分享的網頁地址
   */
  public shareToFackbook(shareUrl: string) {
    const basicShareUrl: string = "https://www.facebook.com/sharer.php?u=";
    const fullShareUrl: string = basicShareUrl + shareUrl;
    window.open(fullShareUrl);
  }

  /**
   * 分享到Twitter
   * @param shareUrl 要分享的網頁地址
   */
  public shareToTwitter(shareUrl: string) {
    const basicShareUrl: string = "https://twitter.com/share?text=&url=";
    const fullShareUrl: string = basicShareUrl + shareUrl;
    window.open(fullShareUrl);
  }
}

export const socialShareUtils = new SocialShareUtils();
