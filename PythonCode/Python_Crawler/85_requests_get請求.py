# urllib
# 1. 一個類型, 六個方法
# 2. get請求
# 3. post請求
# 4. ajax get請求
# 5. ajax post請求
# 6. cookie 登入
# 7. IP 代理

# requests
# 1. 一個類型六個屬性
# 2. get請求
# 3. post請求
# 4. 代理
# 5. cookie 驗證碼

# https://opensea.io/assets?search[query]=School%20Littl%20Dinosaur
# https://www.google.com/search?q=台灣
# https://sites.google.com/search/chiamingliang?query=aa
import requests


# 分析get請求
# https://sites.google.com/search/chiamingliang?query=about%20ming

# ? 可選擇性省略
# url = 'https://sites.google.com/search/chiamingliang?'
url = 'https://sites.google.com/search/chiamingliang'

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.84 Safari/537.36',
}

data = {
    'query' : 'about ming'
}



# url = 請求網址
# params = 搜尋參數
# kwargs = headers 字典
request = requests.get(url=url, params=data)

# 總結：
# 參數使用 params 傳遞
# 數據無需在 urlencode 編碼
# 不需要請求對象的定製
# 請求路徑中的 (?) 可加也可以不加

print(request.text)

# output
'''
<!DOCTYPE html><html lang="en-US" itemscope itemtype="http://schema.org/WebPage"><head><meta charset="utf-8"><script nonce="/c3Bnj4HxrE59MR+U1gLAw">var DOCS_timing={}; DOCS_timing['sl']=new Date().getTime();</script><script nonce="/c3Bnj4HxrE59MR+U1gLAw">function _DumpException(e) {throw e;}</script><script nonce="/c3Bnj4HxrE59MR+U1gLAw">_docs_flag_initialData={"atari-emtpr":false,"atari-efpe":false,"atari-etsm":true,"atari-etss":false,"atari-etsswfm":false,"atari-ebidm":false,"atari-ebids":false,"docs-sup":"","docs-eldi":false,"docs-eiq":false,"docs-ecci":false,"docs-eeii":false,"docs-ipmmp":true,"docs-esi":false,"docs-liap":"/logImpressions","ilcm":{"eui":"AHKXmL0AN2f0jOrO28aRRStSOt_fNT3F0cnWbAfxyAPOAbtLInmXmxue8GLeONplaLClClALAa5b","je":1,"sstu":1649055224926000,"si":"CIWilrfp-fYCFWeAIwAdo3sJuw","gsc":null,"ei":[5703839,5704621,5706832,5706836,5707711,5708870,5711808,5713207,5714550,5714628,5720060,5720925,5721344,5729664,5732942,5734571,5734896,5734954,5735806,5737337,5737441,5738509,5738529,5739802,5740814,5741775,5742462,5743124,5745622,5747263,5747741,5748029,5749679,5752694,5753006,5753329,5754229,5754788,5756315,5758193,5761523,5763092,5764268,14101306,14101502,14101510,14101530,14101534],"crc":0,"cvi":[]},"docs-ccdil":false,"docs-eil":true,"docs-eoi":false,"info_params":{},"atari-jefp":"/_/view/jserror","docs-jern":"view","atari-rhpp":"/_/view"}; _docs_flag_cek= null ; if (window['DOCS_timing']) {DOCS_timing['ifdld']=new Date().getTime();}</script><meta name="viewport" content="width=device-width, initial-scale=1"><meta http-equiv="X-UA-Compatible" content="IE=edge"><meta name="referrer" content="origin"><link rel="icon" href="https://ssl.gstatic.com/atari/images/public/favicon.ico"><meta property="og:title" content="Chia-Ming Liang　梁家銘"><meta property="og:type" content="website"><meta property="og:url" content="https://sites.google.com/search/chiamingliang"><meta property="og:description" content="
梁家銘 Chia-Ming Liang"><meta itemprop="name" content="Chia-Ming Liang　梁家銘"><meta itemprop="description" content="
梁家銘 Chia-Ming Liang"><meta itemprop="url" content="https://sites.google.com/search/chiamingliang"><meta itemprop="thumbnailUrl" content="https://lh3.googleusercontent.com/8emRqTBtN8SHu-6B0WuuBze64Oz7ANwCmk16Nd2XGxX4tDGmlFFjUHDokbjRFBznffocx2MCP70-7ISFhxTOlDk=w1280"><meta itemprop="image" content="https://lh3.googleusercontent.com/8emRqTBtN8SHu-6B0WuuBze64Oz7ANwCmk16Nd2XGxX4tDGmlFFjUHDokbjRFBznffocx2MCP70-7ISFhxTOlDk=w1280"><meta itemprop="imageUrl" content="https://lh3.googleusercontent.com/8emRqTBtN8SHu-6B0WuuBze64Oz7ANwCmk16Nd2XGxX4tDGmlFFjUHDokbjRFBznffocx2MCP70-7ISFhxTOlDk=w1280"><meta property="og:image" content="https://lh3.googleusercontent.com/8emRqTBtN8SHu-6B0WuuBze64Oz7ANwCmk16Nd2XGxX4tDGmlFFjUHDokbjRFBznffocx2MCP70-7ISFhxTOlDk=w1280"><link href="https://fonts.googleapis.com/css?family=Roboto%3A100%2C300%2C400%2C700&display=swap" rel="stylesheet" nonce="DcOH3aoJLeZipZxkfnzjXQ"><link href="https://fonts.googleapis.com/css?family=Google+Sans:400,500|Roboto:300,400,500,700|Source+Code+Pro:400,700&display=swap" rel="stylesheet" nonce="DcOH3aoJLeZipZxkfnzjXQ"><link href="https://fonts.googleapis.com/css?family=Roboto%3Ai%2Cbi%2C700%2C400%2C500&display=swap" rel="stylesheet" nonce="DcOH3aoJLeZipZxkfnzjXQ"><style nonce="DcOH3aoJLeZipZxkfnzjXQ">@media only screen and (max-width: 479px){.qTLLAf{font-size: 37.0pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.qTLLAf{font-size: 47.0pt;}}@media only screen and (min-width: 768px) and (max-width: 1279px){.qTLLAf{font-size: 55.0pt;}}@media only screen and (min-width: 1280px){.qTLLAf{font-size: 55.0pt;}}@media only screen and (max-width: 479px){.Rn3Z1b{font-size: 23.0pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.Rn3Z1b{font-size: 27.0pt;}}@media only screen and (min-width: 768px) and (max-width: 1279px){.Rn3Z1b{font-size: 30.0pt;}}@media only screen and (min-width: 1280px){.Rn3Z1b{font-size: 30.0pt;}}@media only screen and (max-width: 479px){.jgG6ef{font-size: 17.0pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.jgG6ef{font-size: 17.0pt;}}@media only screen and (min-width: 768px) and (max-width: 1279px){.jgG6ef{font-size: 18.0pt;}}@media only screen and (min-width: 1280px){.jgG6ef{font-size: 18.0pt;}}</style><link rel="stylesheet" href="https://www.gstatic.com/_/atari/_/ss/k=atari.vw.fzz42ZGADpM.L.W.O/d=1/rs=AGEqA5mstQSU-evvTM2DcrisD6s2WY-vHA" nonce="DcOH3aoJLeZipZxkfnzjXQ"><title>Chia-Ming Liang　梁家銘</title><style jsname="ptDGoc" nonce="DcOH3aoJLeZipZxkfnzjXQ">.ImnMyf{background-color: rgba(255,255,255,1); color: rgba(33,33,33,1);}.Vs12Bd{color: rgba(33,33,33,1);}.S5d9Rd{background-color: rgba(183,159,124,1); color: rgba(33,33,33,1);}.O13XJf{height: 340px;}.O13XJf .IFuOkc{background-image: url(https://ssl.gstatic.com/atari/images/level-header.png); background-color: rgba(183,159,124,1); background-position-y: center;}.O13XJf .IFuOkc:before{background-color: rgba(33,33,33,1); opacity: 0.5; display: block;}.O13XJf .zfr3Q{color: rgba(255,255,255,1);}.O13XJf .qnVSj{color: rgba(255,255,255,1);}.O13XJf .Glwbz{color: rgba(255,255,255,1);}.O13XJf .qLrapd{color: rgba(255,255,255,1);}.O13XJf .aHM7ed{color: rgba(255,255,255,1);}.O13XJf .NHD4Gf{color: rgba(255,255,255,1);}.O13XJf .QmpIrf{background-color: rgba(255,255,255,1); border-color: rgba(255,255,255,1); color: rgba(0,0,0,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}@media only screen and (max-width: 479px){.O13XJf .QmpIrf{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.O13XJf .QmpIrf{font-size: 13pt;}}@media only screen and (max-width: 479px){.O13XJf{height: 250px;}}.SBrW1{height: 430px; padding-bottom: 120px; padding-top: 120px;}.Wew9ke{fill: rgba(255,255,255,1);}.fOU46b .YSH9J{color: rgba(255,255,255,1);}.fOU46b .KJll8d{background-color: rgba(255,255,255,1);}.fOU46b .Mz8gvb{color: rgba(255,255,255,1);}.fOU46b .G8QRnc .Mz8gvb{color: rgba(33,33,33,1);}.fOU46b .G8QRnc .YSH9J{color: rgba(33,33,33,1);}.fOU46b .G8QRnc .KJll8d{background-color: rgba(33,33,33,1);}.fOU46b .usN8rf .YSH9J{color: rgba(33,33,33,1);}.fOU46b .usN8rf .KJll8d{background-color: rgba(33,33,33,1);}.fOU46b .usN8rf .Mz8gvb{color: rgba(33,33,33,1);}.fOU46b .aCIEDd .YSH9J{color: rgba(33,33,33,1);}.fOU46b .aCIEDd .KJll8d{background-color: rgba(33,33,33,1);}.fOU46b .aCIEDd .Mz8gvb{color: rgba(33,33,33,1);}.fOU46b .a3ETed .YSH9J{color: rgba(33,33,33,1);}.fOU46b .a3ETed .KJll8d{background-color: rgba(33,33,33,1);}.fOU46b .a3ETed .M9vuGd{border-bottom-color: rgba(211,211,211,1);}.fOU46b .a3ETed .Mz8gvb{color: rgba(33,33,33,1);}.fOU46b .zDUgLc{opacity: 0;}.fOU46b .LBrwzc .zDUgLc{opacity: 1; border-bottom-style: none;}.fOU46b .GBy4H .zDUgLc{opacity: 1;}@media only screen and (min-width: 1280px){.XeSM4.b2Iqye.fOU46b .LBrwzc .iWs3gf{color: rgba(255,255,255,1);}}@media only screen and (min-width: 1280px){.KuNac.b2Iqye.fOU46b .GBy4H .iWs3gf{color: rgba(33,33,33,1);}}@media only screen and (min-width: 1280px){.KuNac.G9Qloe.fOU46b .GBy4H .iWs3gf{color: rgba(255,255,255,1);}}@media only screen and (min-width: 1280px){.XeSM4.G9Qloe.fOU46b .LBrwzc .iWs3gf{color: rgba(33,33,33,1);}}.LBrwzc .YSH9J{color: rgba(33,33,33,1);}.LBrwzc .oNsfjf{color: rgba(33,33,33,1);}.LBrwzc .KJll8d{background-color: rgba(33,33,33,1);}.LBrwzc .YTv4We.chg4Jd:focus:before{border-color: rgba(33,33,33,1); display: block;}.LBrwzc .Mz8gvb{color: rgba(33,33,33,1);}.LBrwzc .wgxiMe{background-color: rgba(255,255,255,1);}.LBrwzc .zDUgLc{border-bottom-color: rgba(204,204,204,1); border-bottom-width: 1px; border-bottom-style: solid;}.GBy4H .wgxiMe{background-color: rgba(0,0,0,1);}.JzO0Vc{background-color: rgba(79,71,78,1);}.M63kCb{background-color: rgba(237,236,234,1);}.zfr3Q{font-family: Roboto; color: rgba(33,33,33,1); font-size: 13pt; line-height: 1.55; margin-top: 14px;}.qnVSj{color: rgba(33,33,33,1);}.Glwbz{color: rgba(33,33,33,1);}.qLrapd{color: rgba(33,33,33,1);}.aHM7ed{color: rgba(33,33,33,1);}.NHD4Gf{color: rgba(33,33,33,1);}.zfr3Q .OUGEr{color: rgba(33,33,33,1);}.dhtgD{text-decoration: none; border-bottom-style: solid; border-bottom-width: 1px; font-weight: 700;}.dhtgD:hover{border-bottom-color: rgba(183,159,124,1); border-bottom-style: solid; border-bottom-width: 1px;}.lQAHbd .dhtgD:visited{border-bottom-color: rgba(237,236,234,1);}.lQAHbd .dhtgD:hover{border-bottom-color: rgba(33,33,33,1);}.dhtgD:active{border-bottom-color: rgba(183,159,124,1); border-bottom-style: solid; border-bottom-width: 1px;}.dhtgD:visited{border-bottom-color: rgba(183,159,124,1); border-bottom-style: solid; border-bottom-width: 1px;}.duRjpb{font-family: Roboto; font-size: 37pt; line-height: 1.25; font-weight: 100; margin-top: 20px;}.Ap4VC{margin-bottom: -20px;}.JYVBee{font-family: Roboto; font-size: 28pt; line-height: 1.21; font-weight: 100; margin-top: 20px;}.CobnVe{margin-bottom: -20px;}.OmQG5e{font-family: Roboto; font-size: 16pt; line-height: 1.27; margin-top: 18px; font-weight: 300;}.GV3q8e{margin-bottom: -18px;}.TMjjoe{font-family: Roboto; font-size: 9pt; line-height: 1.2; margin-top: 0px;}.Zjiec{font-family: Roboto; font-weight: 300; font-size: 15pt; line-height: 1.4; margin-top: 48px; margin-left: 48px; margin-bottom: 62px; margin-right: 32px;}.XMyrgf{margin-top: 48px; margin-left: 48px; margin-bottom: 0px; margin-right: 32px;}.PsKE7e{font-family: Roboto; font-weight: 700; font-size: 12pt; padding-left: 10px; padding-right: 10px;}.lhZOrc{font-weight: 700;}.lhZOrc .hDrhEe{border-bottom-width: 7px; border-bottom-color: rgba(183,159,124,1); border-bottom-style: solid;}.TlfmSc{font-family: Roboto; font-weight: 300; font-size: 15pt; line-height: 1.4;}.zDUgLc{background-color: rgba(79,71,78,1); opacity: 1;}.baZpAe{background-color: rgba(255,255,255,1);}.N0neUc{background-color: rgba(255,255,255,0);}.ySLm4c{background-color: rgba(255,255,255,0); font-family: Roboto;}.OWlOyc{background-color: rgba(255,255,255,0);}.iwQgFb{background-color: rgba(194,194,192,1); height: 3px; margin-top: 8px;}.CbiMKe{background-color: rgba(183,159,124,1);}.QmpIrf{background-color: rgba(183,159,124,1); border-color: rgba(183,159,124,1); color: rgba(33,33,33,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.LRAOtb{background-color: rgba(255,255,255,1);}.qeLZfd .zfr3Q{color: rgba(33,33,33,1);}.qeLZfd .zfr3Q .OUGEr{color: rgba(33,33,33,1);}.qeLZfd .qnVSj{color: rgba(33,33,33,1);}.qeLZfd .Glwbz{color: rgba(33,33,33,1);}.qeLZfd .qLrapd{color: rgba(33,33,33,1);}.qeLZfd .aHM7ed{color: rgba(33,33,33,1);}.qeLZfd .NHD4Gf{color: rgba(33,33,33,1);}.qeLZfd .LRAOtb{background-color: transparent;}.qeLZfd .baZpAe{background-color: transparent;}.qeLZfd .iwQgFb{background-color: rgba(194,194,192,1);}.lQAHbd:before{background-color: rgba(183,159,124,1); display: block;}.lQAHbd .zfr3Q{color: rgba(33,33,33,1);}.lQAHbd .zfr3Q .OUGEr{color: rgba(33,33,33,1);}.lQAHbd .qnVSj{color: rgba(33,33,33,1);}.lQAHbd .Glwbz{color: rgba(33,33,33,1);}.lQAHbd .qLrapd{color: rgba(33,33,33,1);}.lQAHbd .aHM7ed{color: rgba(33,33,33,1);}.lQAHbd .NHD4Gf{color: rgba(33,33,33,1);}.lQAHbd .LRAOtb{background-color: transparent;}.lQAHbd .baZpAe{background-color: transparent;}.lQAHbd .iwQgFb{background-color: rgba(211,197,176,1);}.lQAHbd .QmpIrf{background-color: rgba(255,255,255,1); border-color: rgba(255,255,255,1); color: rgba(0,0,0,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.lQAHbd .CbiMKe{background-color: rgba(255,255,255,1);}@media only screen and (max-width: 479px){.lQAHbd .QmpIrf{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.lQAHbd .QmpIrf{font-size: 13pt;}}.LB7kq .IFuOkc{border-bottom-color: rgba(183,159,124,1); border-bottom-width: 10px; border-bottom-style: solid;}.LB7kq .duRjpb{font-size: 63pt; line-height: 1.25; margin-bottom: 19px;}@media only screen and (max-width: 479px){.LB7kq .duRjpb{font-size: 41pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.LB7kq .duRjpb{font-size: 53pt;}}.gk8rDe .duRjpb{font-size: 48pt;}.LB7kq.gk8rDe .zfr3Q{color: rgba(0,0,0,1);}@media only screen and (max-width: 479px){.gk8rDe .duRjpb{font-size: 32pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.gk8rDe .duRjpb{font-size: 41pt;}}.LB7kq .baZpAe{background-color: transparent;}.LB7kq .JYVBee{font-size: 28pt; line-height: 1.18;}@media only screen and (max-width: 479px){.LB7kq .JYVBee{font-size: 21pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.LB7kq .JYVBee{font-size: 25pt;}}.cJgDec .baZpAe{background-color: transparent;}.cJgDec .baZpAe .OUGEr{color: rgba(255,255,255,1);}.cJgDec .baZpAe .LRAOtb{background-color: transparent;}.cJgDec .zfr3Q{color: rgba(255,255,255,1);}.cJgDec .qnVSj{color: rgba(255,255,255,1);}.cJgDec .Glwbz{color: rgba(255,255,255,1);}.cJgDec .qLrapd{color: rgba(255,255,255,1);}.cJgDec .aHM7ed{color: rgba(255,255,255,1);}.cJgDec .NHD4Gf{color: rgba(255,255,255,1);}.cJgDec .IFuOkc:before{background-color: rgba(33,33,33,1); opacity: 0.5; display: block;}.cJgDec .QmpIrf{background-color: rgba(255,255,255,1); border-color: rgba(255,255,255,1); color: rgba(0,0,0,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}@media only screen and (max-width: 479px){.cJgDec .QmpIrf{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.cJgDec .QmpIrf{font-size: 13pt;}}.tpmmCb .baZpAe{background-color: transparent;}.tpmmCb .baZpAe .OUGEr{color: rgba(0,0,0,1);}.tpmmCb .baZpAe .LRAOtb{background-color: transparent;}.tpmmCb .zfr3Q{color: rgba(0,0,0,1);}.tpmmCb .qnVSj{color: rgba(0,0,0,1);}.tpmmCb .Glwbz{color: rgba(0,0,0,1);}.tpmmCb .qLrapd{color: rgba(0,0,0,1);}.tpmmCb .aHM7ed{color: rgba(0,0,0,1);}.tpmmCb .NHD4Gf{color: rgba(0,0,0,1);}.tpmmCb .IFuOkc:before{background-color: rgba(255,255,255,1); display: block;}.tpmmCb .Wew9ke{fill: rgba(0,0,0,1);}.tpmmCb .QmpIrf{background-color: rgba(255,255,255,1); border-color: rgba(255,255,255,1); color: rgba(0,0,0,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}@media only screen and (max-width: 479px){.tpmmCb .QmpIrf{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.tpmmCb .QmpIrf{font-size: 13pt;}}.gk8rDe{padding-top: 60px;}.gk8rDe .QmpIrf{background-color: rgba(183,159,124,1); border-color: rgba(183,159,124,1); color: rgba(33,33,33,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}@media only screen and (max-width: 479px){.gk8rDe .QmpIrf{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.gk8rDe .QmpIrf{font-size: 13pt;}}@media only screen and (max-width: 479px){.gk8rDe{padding-top: 32px; padding-bottom: 32px;}}.YSH9J{color: rgba(255,255,255,1);}.oNsfjf{color: rgba(255,255,255,1);}.wgxiMe{background-color: rgba(79,71,78,1);}.qV4dIc{border-bottom-color: rgba(255,255,255,0); border-bottom-style: solid; border-bottom-width: 8px; padding-top: 14px; padding-bottom: 6px; padding-left: 2px; padding-right: 2px; margin-left: 10px; margin-right: 10px;}.jgXgSe{line-height: 28px;}.u5fiyc{line-height: 28px;}.M9vuGd{border-bottom-color: rgba(183,159,124,1);}.eWDljc{background-color: rgba(79,71,78,1); padding-bottom: 28px;}.IKA38e{padding-left: 36px; margin-top: 20px;}.hDrhEe{font-family: Roboto; margin-left: 12px; margin-right: 12px; padding-left: 0px; display: inline-block; max-width: 90%;}.baH5ib .hDrhEe{margin-left: 4px; padding-left: 0px;}.PsKE7e:hover{opacity: 0.6;}.BFDQOb:hover{opacity: 0.6;}.QcmuFb{padding-left: 20px;}.vDPrib{padding-left: 40px;}.TBDXjd{padding-left: 60px;}.bYeK8e{padding-left: 80px;}.CuqSDe{padding-left: 100px;}.Havqpe{padding-left: 120px;}.JvDrRe{padding-left: 140px;}.o5lrIf{padding-left: 160px;}.yOJW7c{padding-left: 180px;}.rB8cye{padding-left: 200px;}.RuayVd{padding-right: 20px;}.YzcKX{padding-right: 40px;}.reTV0b{padding-right: 60px;}.vSYeUc{padding-right: 80px;}.PxtZIe{padding-right: 100px;}.ahQMed{padding-right: 120px;}.rzhcXb{padding-right: 140px;}.PBhj0b{padding-right: 160px;}.TlN46c{padding-right: 180px;}.GEdNnc{padding-right: 200px;}.xkUom{border-color: rgba(33,33,33,1); color: rgba(33,33,33,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.xkUom:hover{background-color: rgba(33,33,33,0.1000000015); font-size: 13pt; line-height: 22px;}.KjwKmc{color: rgba(33,33,33,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.KjwKmc:hover{background-color: rgba(33,33,33,0.1000000015); font-size: 13pt; line-height: 22px;}.lQAHbd .xkUom{border-color: rgba(33,33,33,1); color: rgba(33,33,33,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.lQAHbd .xkUom:hover{background-color: rgba(255,255,255,0.1000000015);}.lQAHbd .KjwKmc{color: rgba(33,33,33,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.lQAHbd .KjwKmc:hover{background-color: rgba(255,255,255,0.1000000015);}@media only screen and (max-width: 479px){.lQAHbd .xkUom{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.lQAHbd .xkUom{font-size: 13pt;}}@media only screen and (max-width: 479px){.lQAHbd .KjwKmc{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.lQAHbd .KjwKmc{font-size: 13pt;}}.cJgDec .xkUom{border-color: rgba(255,255,255,1); color: rgba(255,255,255,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.cJgDec .xkUom:hover{background-color: rgba(255,255,255,0.1000000015);}.cJgDec .KjwKmc{color: rgba(255,255,255,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.cJgDec .KjwKmc:hover{background-color: rgba(255,255,255,0.1000000015);}@media only screen and (max-width: 479px){.cJgDec .xkUom{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.cJgDec .xkUom{font-size: 13pt;}}@media only screen and (max-width: 479px){.cJgDec .KjwKmc{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.cJgDec .KjwKmc{font-size: 13pt;}}.tpmmCb .xkUom{border-color: rgba(0,0,0,1); color: rgba(0,0,0,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.tpmmCb .xkUom:hover{background-color: rgba(33,33,33,0.1000000015);}.tpmmCb .KjwKmc{color: rgba(0,0,0,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.tpmmCb .KjwKmc:hover{background-color: rgba(33,33,33,0.1000000015);}@media only screen and (max-width: 479px){.tpmmCb .xkUom{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.tpmmCb .xkUom{font-size: 13pt;}}@media only screen and (max-width: 479px){.tpmmCb .KjwKmc{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.tpmmCb .KjwKmc{font-size: 13pt;}}.gk8rDe .xkUom{border-color: rgba(33,33,33,1); color: rgba(33,33,33,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.gk8rDe .xkUom:hover{background-color: rgba(33,33,33,0.1000000015);}.gk8rDe .KjwKmc{color: rgba(33,33,33,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.gk8rDe .KjwKmc:hover{background-color: rgba(33,33,33,0.1000000015);}@media only screen and (max-width: 479px){.gk8rDe .xkUom{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.gk8rDe .xkUom{font-size: 13pt;}}@media only screen and (max-width: 479px){.gk8rDe .KjwKmc{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.gk8rDe .KjwKmc{font-size: 13pt;}}.O13XJf .xkUom{border-color: rgba(255,255,255,1); color: rgba(255,255,255,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.O13XJf .xkUom:hover{background-color: rgba(255,255,255,0.1000000015);}.O13XJf .KjwKmc{color: rgba(255,255,255,1); font-family: Roboto; font-size: 13pt; line-height: 22px;}.O13XJf .KjwKmc:hover{background-color: rgba(255,255,255,0.1000000015);}@media only screen and (max-width: 479px){.O13XJf .xkUom{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.O13XJf .xkUom{font-size: 13pt;}}@media only screen and (max-width: 479px){.O13XJf .KjwKmc{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.O13XJf .KjwKmc{font-size: 13pt;}}.Y4CpGd{font-family: Roboto; font-size: 13pt;}.CMArNe{background-color: rgba(237,236,234,1);}@media only screen and (max-width: 479px){.duRjpb{font-size: 26pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.duRjpb{font-size: 32pt;}}@media only screen and (max-width: 479px){.JYVBee{font-size: 21pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.JYVBee{font-size: 25pt;}}@media only screen and (max-width: 479px){.OmQG5e{font-size: 15pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.OmQG5e{font-size: 15pt;}}@media only screen and (max-width: 479px){.TMjjoe{font-size: 9pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.TMjjoe{font-size: 9pt;}}@media only screen and (max-width: 479px){.Zjiec{font-size: 14pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.Zjiec{font-size: 15pt;}}@media only screen and (max-width: 479px){.PsKE7e{font-size: 12pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.PsKE7e{font-size: 12pt;}}@media only screen and (max-width: 479px){.TlfmSc{font-size: 14pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.TlfmSc{font-size: 15pt;}}@media only screen and (max-width: 479px){.QmpIrf{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.QmpIrf{font-size: 13pt;}}@media only screen and (max-width: 479px){.xkUom{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.xkUom{font-size: 13pt;}}@media only screen and (max-width: 479px){.xkUom:hover{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.xkUom:hover{font-size: 13pt;}}@media only screen and (max-width: 479px){.KjwKmc{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.KjwKmc{font-size: 13pt;}}@media only screen and (max-width: 479px){.KjwKmc:hover{font-size: 13pt;}}@media only screen and (min-width: 480px) and (max-width: 767px){.KjwKmc:hover{font-size: 13pt;}}</style><script nonce="/c3Bnj4HxrE59MR+U1gLAw">_at_config = [null,"AIzaSyChg3MFqzdi1P5J-YvEyakkSA1yU7HRcDI","897606708560-a63d8ia0t9dhtpdt4i3djab2m42see7o.apps.googleusercontent.com",null,null,"v2",null,null,null,null,null,null,null,"https://content.googleapis.com","SITES_%s",null,null,null,null,null,null,null,null,null,["AHKXmL0AN2f0jOrO28aRRStSOt_fNT3F0cnWbAfxyAPOAbtLInmXmxue8GLeONplaLClClALAa5b",1,"CIWilrfp-fYCFWeAIwAdo3sJuw",1649055224926000,[5703839,5704621,5706832,5706836,5707711,5708870,5711808,5713207,5714550,5714628,5720060,5720925,5721344,5729664,5732942,5734571,5734896,5734954,5735806,5737337,5737441,5738509,5738529,5739802,5740814,5741775,5742462,5743124,5745622,5747263,5747741,5748029,5749679,5752694,5753006,5753329,5754229,5754788,5756315,5758193,5761523,5763092,5764268,14101306,14101502,14101510,14101530,14101534]],null,null,null,null,0,null,null,null,null,null,null,null,null,null,"https://drive.google.com",null,null,null,null,null,null,1,1,null,0,1,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,"v2internal","https://docs.google.com",null,null,null,null,null,null,"https://sites.google.com/new/",null,null,null,null,null,0,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,1,"",null,null,null,null,null,null,null,null,null,null,null,null,6,null,null,"https://accounts.google.com/o/oauth2/auth","https://accounts.google.com/o/oauth2/postmessageRelay",null,null,null,null,78,"https://sites.google.com/new/?usp\u003dviewer_footer",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,[],null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,"https://www.gstatic.com/atari/embeds/7925c5f8e01bacb9b4b0a3783ae0b867/intermediate-frame-minified.html",0,null,"v2beta",null,null,null,null,null,null,4,"https://accounts.google.com/o/oauth2/iframe",null,null,null,null,null,null,"https://270007630-atari-embeds.googleusercontent.com/embeds/16cb204cf3a9d4d223a0a3fd8b0eec5d/inner-frame-minified.html",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,0,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,"https://sites.google.com/search/chiamingliang?query\u003dabout+ming",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,0,null,null,null,null,null,null,0,null,"",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,0,null,null,null,null,null,null,null,null,null,null,null,null,null,1,null,1,null,null,[1649055224933,"atari_2022.12-Tue-0706_RC01","436543174",null,1,1,""],null,null,null,null,0,null,null,0,null,null,0,null,null,null,null,0,20,500,"https://domains.google.com",null,0,null,null,null,null,null,0,null,null,1,null,null,0,0,null,0,0,0,1,null,0,0,0,0,0]; window.globals = {"enableAnalytics":true,"webPropertyId":"","showDebug":false,"hashedSiteId":"3104bc7622ad0d68ec95c9912da5ef94103fa3dfb489e6e69ffe069ed955550c","normalizedPath":"view/chiamingliang/about-ming","pageTitle":"About Ming"}; function gapiLoaded() {if (globals.gapiLoaded == undefined) {globals.gapiLoaded = true;} else {globals.gapiLoaded();}}window.messages = []; window.addEventListener && window.addEventListener('message', function(e) {if (window.messages && e.data && e.data.magic == 'SHIC') {window.messages.push(e);}});</script><script src="https://apis.google.com/js/client.js?onload=gapiLoaded" nonce="/c3Bnj4HxrE59MR+U1gLAw"></script><script nonce="/c3Bnj4HxrE59MR+U1gLAw">(function(){/*

 Copyright The Closure Library Authors.
 SPDX-License-Identifier: Apache-2.0
*/
/*

 Copyright 2011 Google LLC.
 SPDX-License-Identifier: Apache-2.0
*/
/*

 Copyright 2013 Google LLC.
 SPDX-License-Identifier: Apache-2.0
*/
/*

 Copyright 2020 Google LLC.
 SPDX-License-Identifier: Apache-2.0
*/
var a=(this||self)._jsa||{};a._cfc=void 0;a._aeh=void 0;/*

 Copyright 2005 Google LLC.
 SPDX-License-Identifier: Apache-2.0
*/
}).call(this);
</script><script nonce="/c3Bnj4HxrE59MR+U1gLAw">const imageUrl =  null ;
      function bgImgLoaded() {
        if (!globals.headerBgImgLoaded) {
          globals.headerBgImgLoaded = new Date().getTime();
        } else {
          globals.headerBgImgLoaded();
        }
      }
      if (imageUrl) {
        const img = new Image();
        img.src = imageUrl;
        img.onload = bgImgLoaded;
        globals.headerBgImgExists = true;
      } else {
        globals.headerBgImgExists = false;
      }
      </script></head><body dir="ltr" itemscope itemtype="http://schema.org/WebPage" id="yDmH0d" css="yDmH0d"><div jscontroller="pc62j" jsmodel="iTeaXe" jsaction="rcuQ6b:WYd;GvneHb:og1FDd;vbaUQc:uAM5ec;"><div jscontroller="X4BaPc" jsaction="rcuQ6b:WYd;o6xM5b:Pg9eo;HuL2Hd:mHeCvf;VMhF5:FFYy5e;sk3Qmb:HI1Mdd;JIbuQc:rSzFEd(z2EeY),aSaF6e(ilzYPe);"><div jscontroller="o1L5Wb" data-sitename="chiamingliang" data-universe="1" jsmodel="fNFZH" jsaction="Pe9H6d:cZFEp;WMZaJ:VsGN3;hJluRd:UADL7b;zuqEgd:HI9w0;tr6QDd:Y8aXB;MxH79b:xDkBfb;JIbuQc:SPXMTb(uxAMZ);rcuQ6b:WYd;" jsname="G0jgYd"><div jsname="gYwusb" class="p9b27"></div><div jscontroller="RrXLpc" jsname="XeeWQc" role="banner" jsaction="keydown:uiKYid(OH0EC);rcuQ6b:WYd;zuqEgd:ufqpf;JIbuQc:XfTnxb(lfEfFf),AlTiYc(GeGHKb),AlTiYc(m1xNUe),zZlNMe(pZn8Oc);YqO5N:ELcyfe;"><div jsname="bF1uUb" class="BuY5Fd" jsaction="click:xVuwSc;"></div><div jsname="MVsrn" class="TbNlJb upKXGe"><div role="button" class="U26fgb mUbCce fKz7Od h3nfre M9Bg4d" jscontroller="VXdfxd" jsaction="click:cOuCgd; mousedown:UX7yZ; mouseup:lbsD7e; mouseenter:tfO1Yc; mouseleave:JywGue; focus:AHmuwe; blur:O22p3e; contextmenu:mg9Pef;touchstart:p6p2H; touchmove:FwuNnf; touchend:yfqBxc(preventMouseEvents=true|preventDefault=true); touchcancel:JMtRjd;" jsshadow jsname="GeGHKb" aria-label="Back to site" aria-disabled="false" tabindex="0" data-tooltip="Back to site" data-tooltip-vertical-offset="-12" data-tooltip-horizontal-offset="0"><div class="VTBa7b MbhUzd" jsname="ksKsZd"></div><span jsslot class="xjKiLb"><span class="Ce1Y1c" style="top: -12px"><svg class="V4YR2c" viewBox="0 0 24 24" focusable="false"><path d="M0 0h24v24H0z" fill="none"/><path d="M20 11H7.83l5.59-5.59L12 4l-8 8 8 8 1.41-1.41L7.83 13H20v-2z"/></svg></span></span></div><div class="E2UJ5" jsname="M6JdT"><div class="rFrNMe b7AJhc zKHdkd CDELXb" jscontroller="pxq3x" jsaction="clickonly:KjsqPd; focus:Jt1EX; blur:fpfTEe; input:Lg5SV" jsshadow jsname="OH0EC" aria-expanded="true"><div class="aCsJod oJeWuf"><div class="aXBtI I0VJ4d Wic03c"><span jsslot class="A37UZe qgcB3c iHd5yb"><div role="button" class="U26fgb mUbCce fKz7Od i3PoXe M9Bg4d" jscontroller="VXdfxd" jsaction="click:cOuCgd; mousedown:UX7yZ; mouseup:lbsD7e; mouseenter:tfO1Yc; mouseleave:JywGue; focus:AHmuwe; blur:O22p3e; contextmenu:mg9Pef;touchstart:p6p2H; touchmove:FwuNnf; touchend:yfqBxc(preventMouseEvents=true|preventDefault=true); touchcancel:JMtRjd;" jsshadow jsname="lfEfFf" aria-label="Search" aria-disabled="false" tabindex="0" data-tooltip="Search" data-tooltip-vertical-offset="-12" data-tooltip-horizontal-offset="0"><div class="VTBa7b MbhUzd" jsname="ksKsZd"></div><span jsslot class="xjKiLb"><span class="Ce1Y1c" style="top: -12px"><svg class="vu8Pwe" viewBox="0 0 24 24" focusable="false"><path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/><path d="M0 0h24v24H0z" fill="none"/></svg></span></span></div><div class="EmVfjc SKShhf" data-loadingmessage="Loading…" jscontroller="qAKInc" jsaction="animationend:kWijWc;dyRcpb:dyRcpb" jsname="aZ2wEe"><div class="Cg7hO" aria-live="assertive" jsname="vyyg5"></div><div jsname="Hxlbvc" class="xu46lf"><div class="ir3uv uWlRce co39ub"><div class="xq3j6 ERcjC"><div class="X6jHbb GOJTSe"></div></div><div class="HBnAAc"><div class="X6jHbb GOJTSe"></div></div><div class="xq3j6 dj3yTd"><div class="X6jHbb GOJTSe"></div></div></div><div class="ir3uv GFoASc Cn087"><div class="xq3j6 ERcjC"><div class="X6jHbb GOJTSe"></div></div><div class="HBnAAc"><div class="X6jHbb GOJTSe"></div></div><div class="xq3j6 dj3yTd"><div class="X6jHbb GOJTSe"></div></div></div><div class="ir3uv WpeOqd hfsr6b"><div class="xq3j6 ERcjC"><div class="X6jHbb GOJTSe"></div></div><div class="HBnAAc"><div class="X6jHbb GOJTSe"></div></div><div class="xq3j6 dj3yTd"><div class="X6jHbb GOJTSe"></div></div></div><div class="ir3uv rHV3jf EjXFBf"><div class="xq3j6 ERcjC"><div class="X6jHbb GOJTSe"></div></div><div class="HBnAAc"><div class="X6jHbb GOJTSe"></div></div><div class="xq3j6 dj3yTd"><div class="X6jHbb GOJTSe"></div></div></div></div></div><div role="button" class="U26fgb mUbCce fKz7Od JyJRXe M9Bg4d" jscontroller="VXdfxd" jsaction="click:cOuCgd; mousedown:UX7yZ; mouseup:lbsD7e; mouseenter:tfO1Yc; mouseleave:JywGue; focus:AHmuwe; blur:O22p3e; contextmenu:mg9Pef;touchstart:p6p2H; touchmove:FwuNnf; touchend:yfqBxc(preventMouseEvents=true|preventDefault=true); touchcancel:JMtRjd;" jsshadow jsname="m1xNUe" aria-label="Back to site" aria-disabled="false" tabindex="0" data-tooltip="Back to site" data-tooltip-vertical-offset="-12" data-tooltip-horizontal-offset="0"><div class="VTBa7b MbhUzd" jsname="ksKsZd"></div><span jsslot class="xjKiLb"><span class="Ce1Y1c" style="top: -12px"><svg class="V4YR2c" viewBox="0 0 24 24" focusable="false"><path d="M0 0h24v24H0z" fill="none"/><path d="M20 11H7.83l5.59-5.59L12 4l-8 8 8 8 1.41-1.41L7.83 13H20v-2z"/></svg></span></span></div></span><div class="Xb9hP"><input type="search" class="whsOnd zHQkBf" jsname="YPqjbf" autocomplete="off" tabindex="0" aria-label="Search this site" value="about ming" autofocus role="combobox" dir="ltr" data-initial-value="about ming"/><div jsname="LwH6nd" class="ndJi5d snByac" aria-hidden="true">Search this site</div></div><span jsslot class="A37UZe sxyYjd MQL3Ob"><div role="button" class="U26fgb mUbCce fKz7Od Kk06A M9Bg4d" jscontroller="VXdfxd" jsaction="click:cOuCgd; mousedown:UX7yZ; mouseup:lbsD7e; mouseenter:tfO1Yc; mouseleave:JywGue; focus:AHmuwe; blur:O22p3e; contextmenu:mg9Pef;touchstart:p6p2H; touchmove:FwuNnf; touchend:yfqBxc(preventMouseEvents=true|preventDefault=true); touchcancel:JMtRjd;" jsshadow jsname="pZn8Oc" aria-label="Clear search" aria-disabled="false" tabindex="0" data-tooltip="Clear search" data-tooltip-vertical-offset="-12" data-tooltip-horizontal-offset="0"><div class="VTBa7b MbhUzd" jsname="ksKsZd"></div><span jsslot class="xjKiLb"><span class="Ce1Y1c" style="top: -12px"><svg class="fAUEUd" viewBox="0 0 24 24" focusable="false"><path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"></path><path d="M0 0h24v24H0z" fill="none"></path></svg></span></span></div></span><div class="i9lrp mIZh1c"></div><div jsname="XmnwAc" class="OabDMe cXrdqd"></div></div></div><div class="LXRPh"><div jsname="ty6ygf" class="ovnfwe Is7Fhb"></div></div></div></div></div></div><div jsname="dCrrn" class="oykKbf"></div><div jsname="dHf4Wd" class="i3uTwd"><div class="DLXGJd"><div class="x8xhwb">Results from this site</div><div jscontroller="U720xd" jsaction="zuqEgd:AXNg4e;Pe9H6d:cZFEp;" class="lZsZxe"><div class="gtazFe"><div class="vH0yjd"><a href="https://sites.google.com/view/chiamingliang/about-ming" data-position="1" data-url="https://sites.google.com/view/chiamingliang/about-ming" jsaction="click:f8hwU(bkmUnc);" jsname="bkmUnc"><b>About</b> <b>Ming</b></a><div class="yDWqEe"><b>About</b> <b>Ming</b>. 梁家銘 Chia-<b>Ming</b> Liang. 現就讀: 國立高雄師範大學 軟體工程與管理學系 三年級學歷: 國立暨大附屬高級中學興趣 ...</div><div class="sVLFaf"><span>Last modified on Mar 29, 2022</span></div></div></div></div></div></div></div><script src="https://www.gstatic.com/_/atari/_/js/k=atari.vw.en_US.6AiQf2bJn6o.O/d=1/rs=AGEqA5mhP9jIsZfoES0BckAvOO7AYbOlcg/m=view" id="base-js" nonce="/c3Bnj4HxrE59MR+U1gLAw"></script></div></div><div jscontroller="YV8yqd" jsaction="rcuQ6b:npT2md"><div id="docs-aria-speakable" aria-live="assertive" aria-relevant="additions" aria-atomic="true" aria-hidden="false" role="region" class="IWfHH"></div></div></body></html>
'''