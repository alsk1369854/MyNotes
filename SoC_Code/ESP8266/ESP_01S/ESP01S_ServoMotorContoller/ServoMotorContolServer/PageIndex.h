const char MAIN_page[] PROGMEM = R"=====(
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 手機應用系統名稱 -->
    <meta name="apple-mobile-web-app-title" content="iotctr">
    <!-- 讓Safari在讀取網頁知道這個網頁有原生APP的特性 -->
    <meta name=apple-mobile-web-app-capable content=yes>
    <!-- 手機應用不顯示手機瀏海 -->
    <meta name=apple-mobile-web-app-status-bar-style content="black">
    <!-- 手機端排版設定 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>IoT Ctr</title>
    <style>
        body {
            margin: 0px;
            padding: 0px;
            height: 100vh;
            background-color: rgb(232, 237, 244);
            font-family: 'Helvetica', 'Arial', 'LiHei Pro', '黑體-繁', '微軟正黑體', sans-serif;
            /* 禁止文字選取 */
            -webkit-user-select: none;
            -moz-user-select: none;
            -o-user-select: none;
            user-select: none;
        }

        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            position: absolute;
            left: 0;
            top: 0;
            width: calc(100% - 30px);
            height: 100px;
            color: rgb(255, 255, 255);
            font-size: 55px;
            font-weight: bold;
            box-shadow: 0px 0px 5px 4px rgb(200, 200, 200);
            background-image: linear-gradient(to top left, rgb(100, 135, 224), rgb(55, 95, 195));
            border-radius: 0px 0px 25px 25px;
            padding: 25px 0px 0px 30px;

        }

        .header_icon {
            padding: 10px 45px 0px 0px;
        }


        .content {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            padding-top: 125px;
        }

        .card_on,
        .card_off {
            font-weight: bold;
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin: 15px;
            border-radius: 30px;
            box-shadow: 0px 0px 5px 2px rgb(200, 200, 200);
            height: 150px;
            width: 38%;
            transition: background-color 0.3s;
        }

        .card_on {
            background-color: rgb(240, 240, 240);
            color: rgb(89, 125, 217);
        }

        .card_on:active,
        .card_on_active {
            background-color: rgb(200, 200, 200);
        }

        .card_off {
            background-color: rgb(150, 170, 190);
            color: rgb(255, 255, 255)
        }

        .card_off:active,
        .card_off_active {
            background-color: rgb(110, 130, 150);
        }

        .card_text {
            padding-left: 25px;
            font-size: 40px;
            -webkit-writing-mode: vertical-lr;
            writing-mode: vertical-lr;
            pointer-events: none;
        }

        .card_icon {
            pointer-events: none;
            padding-right: 18px;
        }
    </style>
</head>


<body>
    <div class="header">
        <span>遙 控 器</span>
        <svg class="header_icon" width="30" height="30" fill="currentColor" viewBox="0 0 16 16">
            <path
                d="M4.5 9a3.5 3.5 0 1 0 0 7h7a3.5 3.5 0 1 0 0-7h-7zm7 6a2.5 2.5 0 1 1 0-5 2.5 2.5 0 0 1 0 5zm-7-14a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zm2.45 0A3.49 3.49 0 0 1 8 3.5 3.49 3.49 0 0 1 6.95 6h4.55a2.5 2.5 0 0 0 0-5H6.95zM4.5 0h7a3.5 3.5 0 1 1 0 7h-7a3.5 3.5 0 1 1 0-7z" />
        </svg>
    </div>
    <div class="content">
        <!-- 主燈 -->
        <div class="card_on" onclick="cardOnClickHandler(event, 0,'off')">
            <span class="card_text">主燈開</span>
            <svg class="card_icon" width="45" height="45" fill="currentColor" viewBox="0 0 16 16">
                <path
                    d="M2 6a6 6 0 1 1 10.174 4.31c-.203.196-.359.4-.453.619l-.762 1.769A.5.5 0 0 1 10.5 13a.5.5 0 0 1 0 1 .5.5 0 0 1 0 1l-.224.447a1 1 0 0 1-.894.553H6.618a1 1 0 0 1-.894-.553L5.5 15a.5.5 0 0 1 0-1 .5.5 0 0 1 0-1 .5.5 0 0 1-.46-.302l-.761-1.77a1.964 1.964 0 0 0-.453-.618A5.984 5.984 0 0 1 2 6zm6-5a5 5 0 0 0-3.479 8.592c.263.254.514.564.676.941L5.83 12h4.342l.632-1.467c.162-.377.413-.687.676-.941A5 5 0 0 0 8 1z" />
            </svg>
        </div>
        <div class="card_off" onclick="cardOnClickHandler(event, 0,'on')">
            <span class="card_text">主燈關</span>
            <svg class="card_icon" width="45" height="45" fill="currentColor" viewBox="0 0 16 16">
                <path fill-rule="evenodd"
                    d="M2.23 4.35A6.004 6.004 0 0 0 2 6c0 1.691.7 3.22 1.826 4.31.203.196.359.4.453.619l.762 1.769A.5.5 0 0 0 5.5 13a.5.5 0 0 0 0 1 .5.5 0 0 0 0 1l.224.447a1 1 0 0 0 .894.553h2.764a1 1 0 0 0 .894-.553L10.5 15a.5.5 0 0 0 0-1 .5.5 0 0 0 0-1 .5.5 0 0 0 .288-.091L9.878 12H5.83l-.632-1.467a2.954 2.954 0 0 0-.676-.941 4.984 4.984 0 0 1-1.455-4.405l-.837-.836zm1.588-2.653.708.707a5 5 0 0 1 7.07 7.07l.707.707a6 6 0 0 0-8.484-8.484zm-2.172-.051a.5.5 0 0 1 .708 0l12 12a.5.5 0 0 1-.708.708l-12-12a.5.5 0 0 1 0-.708z" />
            </svg>
        </div>
        <!-- 風扇 -->
        <div class="card_on" onclick="cardOnClickHandler(event, 1,'off')">
            <span class="card_text">風扇開</span>
            <svg class="card_icon" width="45" height="45" fill="currentColor" viewBox="0 0 16 16">
                <path
                    d="M10 3c0 1.313-.304 2.508-.8 3.4a1.991 1.991 0 0 0-1.484-.38c-.28-.982-.91-2.04-1.838-2.969a8.368 8.368 0 0 0-.491-.454A5.976 5.976 0 0 1 8 2c.691 0 1.355.117 1.973.332.018.219.027.442.027.668Zm0 5c0 .073-.004.146-.012.217 1.018-.019 2.2-.353 3.331-1.006a8.39 8.39 0 0 0 .57-.361 6.004 6.004 0 0 0-2.53-3.823 9.02 9.02 0 0 1-.145.64c-.34 1.269-.944 2.346-1.656 3.079.277.343.442.78.442 1.254Zm-.137.728a2.007 2.007 0 0 1-1.07 1.109c.525.87 1.405 1.725 2.535 2.377.2.116.402.222.605.317a5.986 5.986 0 0 0 2.053-4.111c-.208.073-.421.14-.641.199-1.264.339-2.493.356-3.482.11ZM8 10c-.45 0-.866-.149-1.2-.4-.494.89-.796 2.082-.796 3.391 0 .23.01.457.027.678A5.99 5.99 0 0 0 8 14c.94 0 1.83-.216 2.623-.602a8.359 8.359 0 0 1-.497-.458c-.925-.926-1.555-1.981-1.836-2.96-.094.013-.191.02-.29.02ZM6 8c0-.08.005-.16.014-.239-1.02.017-2.205.351-3.34 1.007a8.366 8.366 0 0 0-.568.359 6.003 6.003 0 0 0 2.525 3.839 8.37 8.37 0 0 1 .148-.653c.34-1.267.94-2.342 1.65-3.075A1.988 1.988 0 0 1 6 8Zm-3.347-.632c1.267-.34 2.498-.355 3.488-.107.196-.494.583-.89 1.07-1.1-.524-.874-1.406-1.733-2.541-2.388a8.363 8.363 0 0 0-.594-.312 5.987 5.987 0 0 0-2.06 4.106c.206-.074.418-.14.637-.199ZM8 9a1 1 0 1 0 0-2 1 1 0 0 0 0 2Z" />
                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14Zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16Z" />
            </svg>
        </div>
        <div class="card_off" onclick="cardOnClickHandler(event, 1,'on')">
            <span class="card_text">風扇關</span>
            <svg class="card_icon" width="45" height="45" fill="currentColor" viewBox="0 0 16 16">
                <path d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1ZM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8Z" />
                <path
                    d="M9.653 5.496A2.986 2.986 0 0 0 8 5c-.61 0-1.179.183-1.653.496L4.694 2.992A5.972 5.972 0 0 1 8 2c1.222 0 2.358.365 3.306.992L9.653 5.496Zm1.342 2.324a2.986 2.986 0 0 1-.884 2.312 3.01 3.01 0 0 1-.769.552l1.342 2.683c.57-.286 1.09-.66 1.538-1.103a5.986 5.986 0 0 0 1.767-4.624l-2.994.18Zm-5.679 5.548 1.342-2.684A3 3 0 0 1 5.005 7.82l-2.994-.18a6 6 0 0 0 3.306 5.728ZM10 8a2 2 0 1 1-4 0 2 2 0 0 1 4 0Z" />
            </svg>
        </div>
        <!-- 外燈 -->
        <div class="card_on" onclick="cardOnClickHandler(event, 2,'on')">
            <span class="card_text">外燈開</span>
            <svg class="card_icon" width="45" height="45" fill="currentColor" viewBox="0 0 16 16">
                <path
                    d="M8 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z" />
            </svg>
        </div>
        <div class="card_off" onclick="cardOnClickHandler(event, 2,'off')">
            <span class="card_text">外燈關</span>
            <svg class="card_icon" width="45" height="45" fill="currentColor" viewBox="0 0 16 16">
                <path
                    d="M8 11a3 3 0 1 1 0-6 3 3 0 0 1 0 6zm0 1a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z" />
            </svg>
        </div>
    </div>
</body>
<script>
    const baseUrl = "http://www.iotctr.com/ctr";
    const cardOnClickHandler = (event, dev_num, operate) => {
        console.log(event);
        console.log(dev_num, operate);
        const { target } = event;

        if (Object.values(target.classList).includes("card_on")) {
            target.classList.add("card_on_active");
        }
        if (Object.values(target.classList).includes("card_off")) {
            target.classList.add("card_off_active");
        }

        // setTimeout(() => {
        //     target.classList.remove("card_on_active", "card_off_active");
        // }, 1000);
        const url = baseUrl + `?dev_num=${dev_num}&operate=${operate}`;
        fetch(url)
            .then(() => {
            })
            .catch((err) => {
                console.log(err);
            }).finally(() => {
                target.classList.remove("card_on_active", "card_off_active");
            })
    }
</script>

</html>
)=====";
