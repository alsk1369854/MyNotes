## 錯誤邊界
### 理解:
###### 錯誤邊界(Error boundary): 用來捕捉後代組件錯誤，渲染出備用頁面
### 特點:
###### 只能捕捉後代組件生命週期產生的錯誤，不能捕捉自己組件產生的錯誤和其他組件在合成事件、定時器中產生的錯誤
### 使用方法:
	// 生命週期函數，一旦後代組件報錯了，就會觸發
	static getDerivedStateFromError(error){
		console.log(error);
		// 在render之前觸發
		// 返回新的state
		return{
			hasError: true,
		}
	}

	componentDidCatch(error, info){
		// 統計頁面出現的錯誤，發送請求送到後台去
		console.log(error, info);
	}