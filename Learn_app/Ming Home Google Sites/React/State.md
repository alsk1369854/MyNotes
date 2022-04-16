## Component State
	//創建類式組件
	class Weather extends React.Component{
	    // 初始狀態
	    state = {
	            isHot: false,
	            wind: '微風'
	        }
	
	    render(){
	        // 讀取狀態
	        const {isHot, wind} = this.state
	        return(
	            <h1 onClick={this.changeWeather}>
					今天天氣很{isHot ? '炎熱' : '涼爽'}, {wind}
				</h1>
	        )
	    }
	
	    // 自定義方法 => 要用賦值語句的形式+箭頭函數
	    changeWeather = () => {
	        console.log(this);
	        this.setState({isHot: !this.state.isHot});
	    }
	}