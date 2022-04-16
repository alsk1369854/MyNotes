## React Life Cycle 
	class Life extends React.Component{
	    state = {
	        opacity:0.5
	    }

	    // 卸載組件
	    death = ()=>{
	        // 卸載組件
	        console.log(myh2);
	        ReactDOM.unmountComponentAtNode(document.getElementById('test'));
	    }
	    
	    // 組件掛載完畢
	    componentDidMount(){
	        this.timer = setInterval(()=>{
	            let {opacity} = this.state;
	            opacity -= 0.1;
	            if(opacity <= 0) opacity = 1;
	            this.setState({opacity});
	        },200)
	    }
	
	    // 組件將要卸載
	    componentWillUnmount(){
	        // 清除定時器
	        clearInterval(this.timer);
	    }
	
	    // 組件初始化渲染, 狀態更新
	    render(){
	        console.log("render");
	        return(
	            <div>
	                <h2 style={{opacity:this.state.opacity}}>React好難...</h2>
	                <button onClick={this.death}>不活了</button>
	            </div>
	        )
	    }
	}
	// 組件掛載
	ReactDOM.render(<Life/>, document.getElementById('root'));