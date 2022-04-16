import urllib.request
import ssl

ssl._create_default_https_context = ssl._create_unverified_context



# 下載網頁
# url_page = 'http://www.baidu.com'
# url_web_page = 'https://sites.google.com/view/chiamingliang/about-ming'
# url代表是下載路徑  filename是下載後的文件命名
# 在python中 可以寫變量名稱  也可以直接寫值
# urllib.request.urlretrieve(url_web_page, 'about_ming.html')


# 下載圖片
# url_img = 'https://files.ckcdn.com/attachments/forum/202007/07/195037rer3xq6xxorogorz.jpg'
# url_demo_img = 'https://lh4.googleusercontent.com/VlEG9nDlEsojAvqYvgU9C2ajETFSX76cMY1Gt4ynw9glmzFJ90EP1uv6oFUyuLPbfOJnTkaPHMCxIQOJgcXMFokKZyf43WQWNrxVabQewvR9kYwdwNhbjGXsGVRxng7OfA=w1280'
# urllib.request.urlretrieve(url=url_demo_img, filename='demo_img.png')


# https://rr4---sn-un57sn7y.c.youtube.com/videoplayback?expire=1649084832&ei=QLVKYrTvFoqLs8IPhbCXiAU&ip=36.238.162.55&cp=U0tTTlBTUl9ITENRM19ITVZJOkpLdWhMcjctTExnbzlmaGJfVk9EWVpPcTB1VzNWYXF2ZjQxWVd6UG02QnE&id=o-AFrFi6_HOTXhaFSYLFK7dljymFmhOqe0ICHG3ZdNvj3n&itag=251&source=youtube&requiressl=yes&mh=2T&mm=32&mn=sn-un57sn7y&ms=su&mv=u&mvi=4&pl=21&sc=yes&spc=4ocVC6orASaNTHTrqygA004WoUaf062eH7qGl-FDww&vprv=1&mime=audio%2Fwebm&ns=E1GS6YCWvaBG8aZVwDSbQfIG&gir=yes&clen=62535&otfp=1&dur=5.061&lmt=1649057989422223&mt=1649062910&keepalive=yes&fexp=24001373%2C24007246&c=WEB&txp=6211224&n=FrRETZe_GNh8yg&sparams=expire%2Cei%2Cip%2Ccp%2Cid%2Citag%2Csource%2Crequiressl%2Cspc%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cotfp%2Cdur%2Clmt&sig=AOq0QJ8wRQIgTEg9rObuyjjrv5DrvlVMBeYmq31daLwUaqPHqmVjFkgCIQDNu-80TbtaRmzx4bIdaiyODsL6yxiOkb0kxIBGOcKYUQ%3D%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Csc&lsig=AG3C_xAwRAIgGUwNcY5Cki0dzUkyvZl9jtofgx2bDmLuONOPHt96NTECIDzEjoP4iTpaYp9G52IE2Jp3UioYCYND1MyV3JX9p2W-&alr=yes&cpn=MxmryzGDmrgtist7&cver=2.20220331.06.00&range=0-62534&rn=2&rbuf=0


# 下載影片
# url_video = 'https://player.vimeo.com/external/646899881.sd.mp4?s=d72849430791626eac9a76c753110f702b6a15b8&profile_id=165&oauth_token_id=57447761'
url_demo_video = 'https://vd4.bdstatic.com/mda-nc9641cs73ae8isf/sc/cae_h264_delogo/1646886893879862123/mda-nc9641cs73ae8isf.mp4?v_from_s=hkapp-haokan-nanjing&auth_key=1649067774-0-0-c583d9124b7e9ca8f420ff5376fede13&bcevod_channel=searchbox_feed&cd=0&pd=1&pt=3&logid=3174775469&vid=13845466462917644178&abtest=100815_2-101454_3-17451_1&klogid=3174775469'

urllib.request.urlretrieve(url_demo_video, 'demo_video.mp4')


