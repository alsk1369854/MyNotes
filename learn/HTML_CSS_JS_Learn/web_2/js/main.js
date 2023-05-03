		var simg = document.getElementById("showWindow");
        let showImgSrc = ["img/show1.jpg","img/show2.jpg","img/show3.jpg"];
        let len = 0;
        function init(){
            window.setInterval(()=>showWindow(), 2000);
        }
        function showWindow(){
            len = (len + 1) % 3;
            simg.src = showImgSrc[len];
        }