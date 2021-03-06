<html lang="en"><head><style class="vjs-styles-defaults">
        .video-js {
            width: 300px;
            height: 150px;
        }

        .vjs-fluid {
            padding-top: 56.25%
        }
    </style><style class="vjs-styles-dimensions">
        .player-container-id-dimensions {
            width: 640px;
            height: 360px;
        }

        .player-container-id-dimensions.vjs-fluid {
            padding-top: 56.25%;
        }
    </style>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no">
    <title>腾讯云视频点播示例</title>
    <!-- 引入播放器 css 文件 -->
    <link href="//imgcache.qq.com/open/qcloud/video/tcplayer/tcplayer.min.css" rel="stylesheet">
    <!--<script src="//imgcache.qq.com/open/qcloud/video/tcplayer/libs/vconsole.min.3.3.0.js"></script>-->
    <!-- 如需在IE8、9浏览器中初始化播放器，浏览器需支持Flash并在页面中引入 -->
    <!--[if lt IE 9]>
    <script src="//imgcache.qq.com/open/qcloud/video/tcplayer/ie8/videojs-ie8.js"></script>
    <![endif]-->
    <!-- 如果需要在 Chrome Firefox 等现代浏览器中通过H5播放hls Dash，需要引入 hls.js dash.js -->
    <script src="//imgcache.qq.com/open/qcloud/video/tcplayer/libs/hls.min.0.12.4.js"></script>
    <script src="//imgcache.qq.com/open/qcloud/video/tcplayer/libs/dash.all.min.2.9.3.js"></script>
    <!-- 引入播放器 js 文件 -->
    <script src="//imgcache.qq.com/open/qcloud/video/tcplayer/tcplayer.min.js"></script>
    <!-- 示例 CSS 样式可自行删除 -->
    <style>
        html,body{
            margin: 0;
            padding: 0;
        }
        .tcplayer {
            margin: 0 auto;
        }
        @media screen and (max-width: 640px) {
            #player-container-id {
                width: 100%;
                height: 270px;
            }
        }
        /* 设置logo在高分屏的显示样式 */
        @media only screen and (min-device-pixel-ratio: 2), only screen and (-webkit-min-device-pixel-ratio: 2) {
            .tcp-logo-img {
                width: 50%;
            }
        }
        .form-control{
            border-radius: 0px;
            width: 300px;
            height: 30px;
            display: block;
            margin: 10px;
        }
        button{
            margin: 0 10px;
        }
    </style>
    <script name="MTAH5" sid="500608092" cid="500608093" src="https://pingjs.qq.com/h5/stats.js?v2.0.4"></script></head>
<body>
<video id="player-container-id" preload="auto" width="1152" height="480" playsinline webkit-playsinline
       x5-playsinline></video>
<script>
    // var vConsole = new VConsole();

    var player,
        fileID = '5285890787511552106',
        appID = '1256468886',
        playDefinition = '11',
        request = new XMLHttpRequest();
    /**
     * 播放DRM内容有一下两个步骤
     * 1. 获取播放 DRM 内容用到的 token
     * 2. 将准备好的参数传递给播放器进行初始化
     */
    request.addEventListener('load', function (event) {
        player = TCPlayer('player-container-id', {
            appID:  appID, // 请传入点播账号的appID 必须
            fileID: fileID // 请传入需要播放的视频filID 必须
            ,playDefinition: playDefinition // 请传入播放模版 必须 关于播放模版请看 **链接**
            ,Html5: {
                nativeTextTracks: false // 在 safari 开启支持 webvtt 解析
            },
            plugins: {
                DRM: {
                    token: event.target.response, // 传入您的后台服务签发的 token
                    // certificateUri: 'https://5000.drm.myqcloud.com/huaxida_test/fairplay.cer', // 传入 Fairplay 播放需要用到的证书地址
                },
                ProgressMarker: {},
            }
        });

    }, false);
    request.open('GET', 'https://demo.vod2.myqcloud.com/drm/gettoken?fileId='+fileID+'&appId='+appID, true); // 去您的 token 签发服务地址获取token
    request.send();

</script>


</body></html>