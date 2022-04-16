## Component Ref
### 回調形式 ref
	class Demo extends React.Component{
	    render(){
	        return(
	            <div>
	                <input ref={currentNode => this.input1 = currentNode} type="text" placeholder="點擊按鈕顯示"/>&nbsp;
	                <button onClick={this.showMsg1}>點擊顯示左側文字</button>&nbsp;
	                <input ref={c => this.input2 = c} onBlur={this.showMsg2} type="text" placeholder="失去焦點顯示"/>
	            </div>
	        )
	      }
	    
	    // 點擊按鈕顯示函數
	    showMsg1 = () =>{
	        const {input1} = this;
	        alert(input1.value);
	    }
	
	    // 失去焦點顯示函數
	    showMsg2 = () =>{
	        const {input2} = this;
	        alert(input2.value);
	    }
	}
	
	ReactDOM.render(<Demo/>, document.getElementById('root'));

### CreateRef
	class Demo extends React.Component{
	    render(){
	        return(
	            <div>
	                <input ref={this.myInput1} type="text" placeholder="點擊按鈕顯示"/>&nbsp;
	                <button onClick={this.showMsg1}>點擊顯示左側文字</button>&nbsp;
	                <input ref={this.myInput2} onBlur={this.showMsg2} type="text" placeholder="失去焦點顯示"/>
	            </div>
	        )
	    }
	
	    // React.createRef調用後返回一個容器, 該容器允許處存ref所標示的節點,該容器是"專人專用的"的 
	    myInput1 = React.createRef();
	    myInput2 = React.createRef();
	
	    // 點擊按鈕顯示函數
	    showMsg1 = () =>{
	        console.log(React.createRef);
	        console.log("@ myInput1",this.myInput1);
	        const {current} = this.myInput1;
	        alert(current.value);
	    }
	
	    // 失去焦點顯示函數
	    showMsg2 = () =>{
	        const {current} = this.myInput2;
	        alert(current.value);
	    }
	}
	
	ReactDOM.render(<Demo/>, document.getElementById('root'));