## Web Development
### nanoid (id 生成器)	
	https://www.npmjs.com/package/nanoid

	** Installation **
	$ npm i nanoid
	
	** Examples **
	import { nanoid } from 'nanoid'
	model.id = nanoid() //=> "V1StGXR8_Z5jdHi6B-myT"

### file-saver (動態文件下載)
	https://www.npmjs.com/package/file-saver

	** Installation **
	$ npm i file-saver

	**Usage**
	// imput
	var FileSaver = require('file-saver');
	
	// save file
	var blob = new Blob(["Hello, world!"], {type: "text/plain;charset=utf-8"});
	FileSaver.saveAs(blob, "hello world.txt");

	// save img
	FileSaver.saveAs("https://httpbin.org/image", "image.jpg");

### JSZip (動態文件壓縮)
	https://www.npmjs.com/package/jszip

	** Installation **
	$ npm i jszip

	**Usage**
	var zip = new JSZip();

	zip.file("Hello.txt", "Hello World\n");
	
	var img = zip.folder("images");
	img.file("smile.gif", imgData, {base64: true});
	
	zip.generateAsync({type:"blob"}).then(function(content) {
	    // see FileSaver.js
	    saveAs(content, "example.zip");
	});

### axios (發送請求)
	https://www.npmjs.com/package/axios

	** Installation **
	$ npm i axios

	**Usage**
	// Get request
	async function getUser() {
	  try {
	    const response = await axios.get('/user?ID=12345');
	    console.log(response);
	  } catch (error) {
	    console.error(error);
	  }
	}
	
	// Post request
	axios.post('/user', {
	    firstName: 'Fred',
	    lastName: 'Flintstone'
	  })
	  .then(function (response) {
	    console.log(response);
	  })
	  .catch(function (error) {
	    console.log(error);
	  });

	// Multiple concurrent requests
	function getUserAccount() {
	  return axios.get('/user/12345');
	}
	
	function getUserPermissions() {
	  return axios.get('/user/12345/permissions');
	}
	
	Promise.all([getUserAccount(), getUserPermissions()])
	  .then(function (results) {
	    const acct = results[0];
	    const perm = results[1];
	  });

### PubSub-js (函數遠程呼叫)
	https://www.npmjs.com/package/pubsub-js
	
	** Installation **
	$ npm i pubsub-js

	**Usage**
	import PubSub from 'pubsub-js'

	// 定義一個 function
	var mySubscriber = function (msg, data) {
	    console.log( msg, data );
	};

	// 訂閱消息(提供 function 方)
	var token = PubSub.subscribe('MY TOPIC', mySubscriber);

	// 結束訂閱
	PubSub.unsubscribe(token);

	// 發送消息(使用 function 方)
	PubSub.publish('MY TOPIC', 'hello world!');

## NFT Development
### nft-art-generator (圖畫隨機拼湊生產器)
	https://www.npmjs.com/package/nft-art-generator

	** Installation **
	$ npm i -g nft-art-generator
	
	**Usage**
	$ nft-generate [--save-config]

	**Documentation**  
	Before you start, make sure your file structure looks something like this:
	
	YOUR_PROJECT/  
	├─ images/  
	│  ├─ trait1_name/  
	│  │  ├─ file1.png  
	│  │  ├─ file2.png  
	│  │  ├─ file3.png  
	│  │  ├─ ...  
	│  ├─ trait2_name/  
	│  │  ├─ file4.png  
	│  │  ├─ file5.png  
	│  │  ├─ ...  
	│  ├─ trait3_name/  
	│  │  ├─ file6.png  
	│  │  ├─ ...  
	│  ├─ ...  
	

	