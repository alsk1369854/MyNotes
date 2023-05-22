# Deploy GUI

### 1. 登入 AWS

- [Amazon Web Services Sign-In](https://signin.aws.amazon.com/signin?redirect_uri=https%3A%2F%2Fconsole.aws.amazon.com%2Fconsole%2Fhome%3FhashArgs%3D%2523%26isauthcode%3Dtrue%26nc2%3Dh_ct%26src%3Dheader-signin%26state%3DhashArgsFromTB_ap-southeast-2_dac62d780ef4971b&client_id=arn%3Aaws%3Asignin%3A%3A%3Aconsole%2Fcanvas&forceMobileApp=0&code_challenge=WSY-OdHF8n1beSup0MwLHc8jsVVqTRiGZnFoROFjG7o&code_challenge_method=SHA-256)

### 2. 部署 Lambda function

- [AWS Lambd console](https://ap-northeast-1.console.aws.amazon.com/lambda/home?region=ap-northeast-1#/functions)

#### 2-1. 建立 Lamdb function (若已現有則可跳過此步驟至 2-2.)

![](images/截圖%202023-05-22%20上午11.48.14.png)

![](images/截圖%202023-05-22%20上午11.59.05.png)

#### 2-2. 上傳打包程式

- 請先完成專案打包，詳細請參考 "build.md""

![](images/截圖%202023-05-22%20下午1.04.33.png)

![](images/截圖%202023-05-22%20下午1.11.33.png)

#### 2-3. 配置環境變數

![](images/截圖%202023-05-22%20下午2.12.01.png)

<img src="images/截圖%202023-05-22%20下午2.15.12.png" title="" alt="" width="640">

### 3. 部署 API Gateway (若以部署過則可跳過此步棸)

- [AWS API Gateway conosle](https://ap-northeast-1.console.aws.amazon.com/apigateway/main/apis?region=ap-northeast-1)

#### 3-1. 建立 API Gateway

![](images/截圖%202023-05-22%20下午1.27.11.png)

![](images/截圖%202023-05-22%20下午1.32.17.png)

#### 3-2. 配置路由

![](images/截圖%202023-05-22%20下午1.41.41.png)

![](images/截圖%202023-05-22%20下午1.53.49.png)

![](images/截圖%202023-05-22%20下午1.57.25.png)

#### 3-3. API 測試

![](images/截圖%202023-05-22%20下午2.26.00.png)

![](images/截圖%202023-05-22%20下午2.29.15.png)

#### 3-4. 部署至生產階段

![](images/截圖%202023-05-22%20下午2.33.30.png)

![](images/截圖%202023-05-22%20下午2.35.36.png)

![](images/截圖%202023-05-22%20下午2.38.38.png)