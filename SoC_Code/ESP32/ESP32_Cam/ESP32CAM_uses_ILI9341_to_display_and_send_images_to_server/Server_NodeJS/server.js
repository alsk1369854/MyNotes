let fs = require('file-system');
const { networkInterfaces } = require('os');
const http = require('http');
const nets = networkInterfaces();
const server = http.createServer();

let count = 0;

server.on('request', (request, response)=>{
    if(request.method == 'POST' && request.url === '/imageUpdate'){
        console.log(count++);
        let results = new Object();
        results.success = false;

        let ImageFile = fs.createWriteStream('./resources/test.jpeg', {encode: 'utf8'});
        request.on('data', function(data){
            ImageFile.write(data);
        });

        request.on('end', async function(){
            ImageFile.end();
            const label = [{x1:0, y1:10, x2:10, y2:20, name:'ming'}];
            response.writeHead(200, {'Content-Type': 'application/json'});
            response.end(JSON.stringify(label))
        })
    }else{
        console.log('error');
        response.writeHead(405, {'COntent-type' : 'text/plain'});
        response.end();
    }
})

const port = 8888;
server.listen(port);
console.log(`Listening at ${nets.en0[1].address}:${port}`);
// console.log(nets);