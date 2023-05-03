## Controlled Component [推薦]
	// 善用 onChange 事件 更新 State
	class Login extends React.Component{
	    // 初始化狀態
	    state = {
	        username: '',
	        password: ''
	    }
	
	    // 處理交替事件
	    handleSubmit = (event) => {
	        event.preventDefault();
	        const {username, password} = this.state;
	        alert(`User_Name: ${username}, Password: ${password}`)
	    }
	
	    // 保存Username
	    saveUsername = (event)=>{
	        this.setState({username:event.target.value});
	    }
	
	    // 保存Password
	    savePassword = (event)=>{
	        this.setState({password:event.target.value});
	    }
	
	    render(){
	        return(
	            <form action="https://w3.nknu.edu.tw/zh/" onSubmit={this.handleSubmit}>
	                帳號: <input onChange={this.saveUsername} type="text" name="username"/>
	                密碼: <input onChange={this.savePassword} type="password" name="password"/>
	                <button>登入</button>
	            </form>
	        )
	    }
	}
	ReactDOM.render(<Login/>, document.getElementById('test'));


## Uncontrolled Component [不推薦]
	// 透過ref的方式 在提交的那一刻 才更新 State
	class Login extends React.Component{
	    handleSubmit = (event) => {
	        event.preventDefault();
	        const {username, password} = this;
	        alert(`User_Name: ${username.value}, Password: ${password.value}`)
	    }
	
	    render(){
	        return(
	            <form action="https://w3.nknu.edu.tw/zh/" onSubmit={this.handleSubmit}>
	                帳號: <input ref={c => this.username = c} type="text" name="username"/>
	                密碼: <input ref={c => this.password = c} type="password" name="password"/>
	                <button>登入</button>
	            </form>
	        )
	    }
	}
	ReactDOM.render(<Login/>, document.getElementById('test'));