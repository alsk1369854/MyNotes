import urllib.request

url = 'https://www.canva.com/settings/your-account'

headers = {
    # ':authority': 'www.canva.com',
    # ':method': 'GET',
    # ':path': '/settings/your-account',
    # ':scheme': 'https',
    'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
    # 'accept-encoding': 'gzip, deflate, br',
    'accept-language': 'zh-TW,zh;q=0.9,en-AU;q=0.8,en;q=0.7,en-US;q=0.6',
    'cache-control': 'max-age=0',
#    ### cookie中攜帶著你登入訊息  如果有登入之後的cookie 那麼我們就可以攜帶著cookie進入到任何頁面
    'cookie': 'CDI=3e4be388-70fb-4f94-a921-5207d807c70e; CAI=9028b132-9a63-4531-9627-c9b4268bf582; CPA=mj070szbZANAS02VEUZBDkmu67jk-gNtXuqhY_FjOJt_bT1NIUYOnKqkcZ0kjnrnMYkFdecibxy-HuzWhc3hEhzOCRMkevPPgv2hkK2LknLuvxYsAniHd5oz6xIeBn0nfK1NZDbryyNGrU1Q7Tmsl0ACRnAK_MYpzEqsl2SjM7RHDds3PBJKL6hGKXzjmmOtA-xyeMBAspCErPbmFiRShQKqtpqGMkb-IbDNDfzaaw34D14a1Gx9bTlXsSHJDC-XlaZsPO0NrKhvywY9B4xyRWm76j9ib6AFdW5kSg03lSQu0KnVgEJhupvJppgn8bICWkW9zS8Qsu2tYlZ647yEBoPutqSMcMv0hBgad28t226GGt50A5OdHBxFzx46M3XYGwD-8g; CCK=BuMWph2ZGu47Rq3LeUDltw; __cf_bm=SxkxDoVHxdGiBXNDFbWT9KU1O6IwAvT4.DSITmKdRrg-1643454314-0-AS3jqctxJFzCBAVDKhdM41FfhY0pTyjN6wKnRXqrFEZLs39qAuP5W6ZmxOlqk2Y1+P599gISB40wcMNUGMMT9erBwdvqWHybKjhVxPKS4U9+fJKp6yuv8e5wAzolyISfjioh94a3Znu6GEZc4VnpM1q6lJ6my0PErrkW1Xl0GI0T; __cfruid=5961e795e274b3439e3853f31d9559d852023bf9-1643454315; ab.storage.deviceId.320f7332-8571-45d7-b342-c54192dae547=%7B%22g%22%3A%22f7e627dd-ceea-f6aa-e543-59b82d8d180c%22%2C%22c%22%3A1643454315963%2C%22l%22%3A1643454315963%7D; gtm_custom_user_engagement_lock_4=yes; _gid=GA1.2.302773464.1643454318; FPID=FPID2.2.PmGvKsF4M%2BAZlXvsEERko11Zj62PNZ0sXjB6Xj1B8tU%3D.1643454318; FPAU=1.2.2062747423.1643454317; FPLC=AUOO3xqV1apiqkMdBz3zuaPACxO2Cw1DN6HRv74hHePsujo7LmA4GSzHADuwd5ZIgl%2FCfVQudqNmSOA9pdglBrJ1XibShoj58kJbCp65vpZNDaoO8t%2Bo2PYY3eBonA%3D%3D; CB=BAE201Wrfu0; CID=mP2GFLNYmhbvmTupAyFQoEcYokYsQVTRiTXaIzSHl-zbYLSQAK68Bn7Rs6jZfkZSKZcOoyE1OptdAwTJzigzpVafb7rhK8-4sJuRmGp5vFsVUpL_; CS=1; CL=zh-TW; ab.storage.userId.320f7332-8571-45d7-b342-c54192dae547=%7B%22g%22%3A%22UAE201d4Io4%22%2C%22c%22%3A1643454327407%2C%22l%22%3A1643454327407%7D; __cid=ZmImqQSkVIYsKSbzPRfgrZ9Vf3XRBJ95WmgCZE1scwx0PV56CU6MMlppC7x0cTxoZ2NOhFkmCxkGoK4zXBuaZlE2BWQmDXspNhNgCDUIJlF3WSlMDgBnADYeekQXPSlVaUc5X3k-YApvXTJEIV89TXkoeRQ1DF4BOyJgEHZcOlN3Wj9EcSJBMBQlJUQ1AGIBeS5sBzIGIEQaAXsLNAwmXW5HOUptXzBWd144RAoIbwUrACZRal4nV28FAQVtXmsAal85E1pugmTNYWwAaQxoUj8KlTMYJ04oHEkhLTcdbAh1SUAKLQxlTAtAKTERLSkjKwh5DDAKekRvWjlEHQB7ATodOiBoWCkSKjY8O2lJeRcGXFZUdUlNVx1YOElrXidWaUc4VGlHMFBvXyDAT1gwU2lGOEtoSe3c04yE7GFTOVRjWTnLUWm8RZzfk69Zugi51pfuZLJq_ik2bS0ZpmtBrFnqCWRZ3gm8Wd5yZFlpCWRZaQlkWdNo3jkpSSQXaQlkWSlJJBkpSQQeCU4FDilJJBk7T7ABKZBkW2kJZFlhCZs; __pdst=e5e7f03926c34b0fade0a3006ed8ab8c; CAZ=bLsDsvQa5Ez0QtNOgxDDzQJsJszNuq48KZfBxd-wT9eTKgsUBs8GJhMBi-IrsBCbQCRsruj8knc4EzzTimt7gbO79Qx41rN8ORSqVOhtAILdEji-iWGTLmyMUfq6ITn5UYV8q2KuPhJMoBvlNwY5wAl8KX85alo098Gkgyz8JiHGUhgMWUPtHhuTQxsfj31OnOfH0A2dvaiX49R6ct1mhlU-CFYD21q_RBhHZPekMRs0QABUr7njt_rmNKmCWpoe8jt2aO3XJSu96HcQYl6VIx5EhWssToEFpWnEj9rnECkA4TvD-urJtGp-8YcBu5zrQe0Ik8QfSU7nG8q5ERbPuXWvlCwxY5KAD0uuIPrVYCI2PBdJQwJaw6BZCGlz8gQaJ-7fc5UAOBU-x2eQQS1L1c6FAyj1eCvCwvl_x9n4DeXiWFVb7KvoAC7-tv577PmEbqaqeipVSK8IAT_u_8Kr93N4VkIGE5sutuwPtFKLLrg76z0teUE9FaN3wjUn6aORbjQs6STiiwNxY2Kg1oJWzSXVZ3zdHNtsfHMSu5ZeYH5lGEF-a4VWnpAW6XWSTELose4_F5Ifcp3nAGI7azsY52Rp_q8olePemYGWjT-SgABWWtgIMcPHjjq6PpIFxJl1v0a7I6yKafm2Beervk738N3OOKXjbxpw0K_ihFcPL7lkdOR576u2654ivjuylLz2KhizVn-cDHRUZHTLCxqGjBWTCYbZXcdBVCC52FizUfZtlWdjMp3tSChq8eryHH6-2hO3YyMT04-SyzelL33NSySX4EwKeXvbFpRjRWuOrR3jq-07Z7YafjHfsX4tvKzTo70jJjoakHuisJcMGoKRXQ8hQw0U1ivd78at4dO_HcdUsiXDLx-4JB30gLM4SagrLE6ZsP_k9DiepDnuNewC0eLo4A2UMlbP4BOwvPRip9vJMoQdE6kCht4OhtBzqsQRqDvNICr4EJtIlQBwpyzkEQXIWN2iqAxRLIKlxwkRRIotZCG3Wx63wfx0f5VHAMrLbL5Ec-hehb-IoWXshpVYYeF4zzb7oQXXggIx6qTh2LALLzYtoTKCjOFu04AYfvRtZS1HRboS5tnk3OuwDU6ppJeCRGG8Dwh-6boeNNe6b-uY47CJQa2MHMZoxTCxa2bzil7cfs0nDi8H5SRvFz3ZQTOH0fM9qQycrJaSp7ukKow64_6G0lwyBGDJFnLy00X7dKpOjQLTUP_HM1HrPdsz1rxvI52gBDqj2UavUNtTE_DFF7548baOcIyohdV3IbcnhTOIaS-UWGdgkBE2wM6mhgr3GdDKl1iQkTIiNCFGrQ8k_xGXLZnnusIB-HfgDYFzrP6bfclSmfLjCHIt1sY9Zti-r1M0KVLwJ0xPmu0cUdXTJQbn81XrOCDksDPpKx5C334e8wITuMOVIseNQgi9GDVlO-W6RHjf9r17o_HCetmJOK-H; CUI=2WcZk1CMXIAbRuUc-JZl2dd9Ji8-YPJGC7W1gODBWEEWpmVLKWMq4Pqt-S206HW7-6Sjkw; __stripe_mid=554b2369-9772-4a34-ba74-aa950b9a59ae8e7f51; __stripe_sid=6942f7cf-7ef9-42c1-b75b-61d458851266957464; _dc_gtm_UA-37190734-9=1; ab.storage.sessionId.320f7332-8571-45d7-b342-c54192dae547=%7B%22g%22%3A%222b833a16-73fc-26fe-cf36-b12ec27b0afd%22%2C%22e%22%3A1643456204166%2C%22c%22%3A1643454327408%2C%22l%22%3A1643454404166%7D; gtm_custom_user_engagement={"lock":"yes","page":6,"landingPageURL":"https://www.canva.com/zh_tw/","newSession":"no"}; _ga_EPWEMH6717=GS1.1.1643454316.1.1.1643454404.0; _ga=GA1.2.1409443127.1643454318; _uetsid=57a28e7080f311ecb6529fc64497a796; _uetvid=57a2acb080f311ecbd714fdfc2451bc9',
#    ### referer(有的頁面會有) 判斷當前路徑是不是由上一個路徑近來的  一般情況下 是做圖片防盜鏈
    'sec-ch-ua': '" Not;A Brand";v="99", "Google Chrome";v="97", "Chromium";v="97"',
    'sec-ch-ua-mobile': '?0',
    'sec-ch-ua-platform': '"Windows"',
    'sec-fetch-dest': 'document',
    'sec-fetch-mode': 'navigate',
    'sec-fetch-site': 'none',
    'sec-fetch-user': '?1',
    'upgrade-insecure-requests': '1',
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36',
}

request = urllib.request.Request(url=url, headers=headers)

response = urllib.request.urlopen(request)

content = response.read().decode('utf-8')

print(content)