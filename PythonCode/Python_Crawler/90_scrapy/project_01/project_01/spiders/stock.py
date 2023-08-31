import scrapy


class StockSpider(scrapy.Spider):
    name = "stock"
    allowed_domains = ["www.taifex.com.tw"]
    start_urls = ["https://www.taifex.com.tw/cht/5/stockMargining"]

    def parse(self, response):
        pass
