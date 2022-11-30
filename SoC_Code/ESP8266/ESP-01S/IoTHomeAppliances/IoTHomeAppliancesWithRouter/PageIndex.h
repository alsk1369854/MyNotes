const char MAIN_page[] PROGMEM = R"=====(
<!DOCTYPE html>
<html>

<head>
    <!-- 手機應用系統名稱 -->
    <meta name="apple-mobile-web-app-title" content="Switch Controller">
    <!-- 讓Safari在讀取網頁知道這個網頁有原生APP的特性 -->
    <meta name=apple-mobile-web-app-capable content=yes>
    <!-- 手機應用不顯示手機瀏海 -->
    <meta name=apple-mobile-web-app-status-bar-style content=black-translucent>
    <!-- 手機端排版設定 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;

            margin: 0;
            padding: 0;

            background-color: rgb(137, 127, 131);

            font-family: monospace;
            /* 禁止文字選取 */
            -webkit-user-select: none;
            -moz-user-select: none;
            -o-user-select: none;
            user-select: none;
        }

        #app {
            height: 100vh;
            width: 100vw;
            overflow: auto;

            background-color: rgb(234, 225, 217);
        }

        .title_container {
            display: flex;
            align-items: center;
            justify-content: center;

            width: 100%;
            height: auto;
            border-radius: 0px 0px 30px 30px;
            background-color: rgb(137, 127, 131);
            color: rgb(255, 255, 255);
        }

        .title_text {
            display: flex;
            align-items: flex-end;

            height: 105px;
            font-size: 75px;
            font-weight: bold;
        }

        .btn {
            display: flex;
            align-items: center;
            justify-content: center;

            width: 100%;
            height: 40%;

            font-size: 5rem;
            font-weight: bold;
            border-radius: 10px;

            transition-timing-function: ease-in-out;
        }

        .btn-on {
            color: rgb(103, 104, 114);
            background-color: rgb(225, 204, 187);

            border-width: 2px;
            border-style: solid;
            border-color: rgb(216, 191, 170);
        }

        .btn-on:active {
            background-color: rgb(216, 191, 170);
        }

        .btn-off {
            color: rgb(245, 241, 241);
            background-color: rgb(192, 163, 139);

            border-width: 2px;
            border-style: solid;
            border-color: rgb(150, 127, 107);
        }

        .btn-off:active {
            background-color: rgb(150, 127, 107);
        }

        .content_container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;

            margin: 0px 20px;
            padding: 0px 20px;
            height: 600px;
            height: calc(100% - 105px);

        }

        .line {
            background-color: rgb(161, 157, 161);
            border-radius: 10px;
            height: 2px;
            width: 100%;
            margin: 15px 0px;
        }
    </style>
</head>

<body>
    <div id="app">
        <div class="title_container">
            <div class="title_text">Switch</div>
        </div>
        <div class="content_container">
            <div onclick="click_on_btn()" class="btn btn-on">ON</div>
            <div class="line"></div>
            <div onclick="click_off_btn()" class="btn btn-off">OFF</div>
        </div>
    </div>
    <script>
        const send_operate_API = (operate) => {
            const xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    console.log(this.responseText);
                }
            };
            xhttp.open("GET", "operate?operate=" + operate, true);
            xhttp.send();
        }
        const click_on_btn = () => send_operate_API('on');
        const click_off_btn = () => send_operate_API('off');
        const btn_on_tag = document.querySelector('.btn_on');
        const btn_off_tag = document.querySelector('.btn_off');
        btn_on_tag.addEventListener("touchend", click_on_btn, false);
        btn_off_tag.addEventListener("touchend", click_off_btn, false);
    </script>
</body>

</html>
)=====";
