import scrapy


class GoogleSpider(scrapy.Spider):
    # 爬蟲名稱
    name = "google"
    # 允許訪問的網址
    allowed_domains = ["www.google.com"]
    # 第一次訪問的網址
    start_urls = ["https://www.google.com"]

    def parse(self, response):
        print("### ming")

        # 頁面 string 數據
        # print(response.text)

        # 頁面 binary 數據
        # print(response.body)
