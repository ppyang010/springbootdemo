<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no">
    <title>腾讯云视频点播示例</title>
    <!-- 引入播放器 css 文件 -->
    <link href="//imgcache.qq.com/open/qcloud/video/tcplayer/tcplayer.css" rel="stylesheet">
    <!-- 如需在IE8、9浏览器中初始化播放器，浏览器需支持Flash并在页面中引入 -->
    <!--[if lt IE 9]>
    <script src="//imgcache.qq.com/open/qcloud/video/tcplayer/ie8/videojs-ie8.js"></script>
    <![endif]-->
    <!-- 如果需要在 Chrome Firefox 等现代浏览器中通过H5播放hls，需要引入 hls.js -->
    <script src="//imgcache.qq.com/open/qcloud/video/tcplayer/lib/hls.min.0.8.8.js"></script>
    <!-- 引入播放器 js 文件 -->
    <script src="//imgcache.qq.com/open/qcloud/video/tcplayer/tcplayer.min.js"></script>
    <script src="https://cdn.bootcss.com/blueimp-md5/2.12.0/js/md5.js"></script>
    <#--    <script src="https://cdn.bootcss.com/moment.js/2.24.0/locale/zh-cn.js"></script>-->
    <script src="https://cdn.bootcss.com/moment.js/2.24.0/moment.min.js"></script>
    <!-- 示例 CSS 样式可自行删除 -->
</head>
<body>
<!-- 设置播放器容器 -->
<video id="player-container-id" preload="auto" width="1152" height="480" playsinline webkit-playsinline
       x5-playsinline></video>
<!--
注意事项：
* 播放器容器必须为 video 标签
* player-container-id 为播放器容器的ID，可自行设置
* 播放器区域的尺寸请按需设置，建议通过 css 进行设置，通过css可实现容器自适应等效果
* playsinline webkit-playsinline x5-playsinline 这几个属性是为了在标准移动端浏览器不劫持视频播放的情况下实现行内播放，此处仅作示例，请按需使用
-->

<script>
    var playDefinition = '11';
    var appId = '1252348479';
    var fileId = '5285890793621156594';
    var signKey = 'c3ee5a3ee9d2db504d4d8f6b1b8da972';
    var t = (moment().add(7, 'days').unix()).toString(16);
    var us = Math.random().toString(36).slice(-10);
    console.log(t);
    // var md5Sign = md5(signKey + appId + fileId + t + us);
    var md5Sign = md5(signKey + appId + fileId + playDefinition + t + us);
    var player = TCPlayer("player-container-id", { // player-container-id 为播放器容器ID，必须与html中一致
        fileID: fileId, // 请传入需要播放的视频fileID 必须
        appID: appId, // 请传入点播账号的appID 必须
        autoplay: false, //是否自动播放,
        t: t,
        us: us,
        sign: md5Sign,
        playDefinition:playDefinition,
        plugins: {
            DRM: {
                token: 'bkhSa2tKYkFmMmpMMXFBanBRWDYzT01mZk5NPVNlY3JldElkPUFLSURyYVA1YXZGNEpOR3lVb0swc0psMzkzbUplbmtXN2J4TCZDdXJyZW50VGltZVN0YW1wPTE1Njc2NzYxNjkmRXhwaXJlVGltZVN0YW1wPTE1Njc3NjI1NjkmUmFuZG9tPW9pNFVZOUJoOUc1VVZHMktLcFY0VGFoS29WNkdKOTNCJkZpbGVJZD01Mjg1ODkwNzkzNjIxMTU2NTk0'
            }
        }

        //其他参数请在开发文档中查看
    });
    //
    // var player,
    //     request = new XMLHttpRequest();
    // /**
    //  * 播放DRM内容有一下两个步骤
    //  * 1. 获取播放 DRM 内容用到的 token
    //  * 2. 将准备好的参数传递给播放器进行初始化
    //  */
    // request.addEventListener('load', function (event) {
    //     player = TCPlayer('player-container-id', {
    //         appID: appId, // 请传入点播账号的appID 必须
    //         fileID: fileId // 请传入需要播放的视频filID 必须
    //
    //         , playDefinition: playDefinition // 请传入播放模版 必须 关于播放模版请看 **链接**
    //         , Html5: {
    //             nativeTextTracks: false // 在 safari 开启支持 webvtt 解析
    //         },
    //         t: t,
    //         us: us,
    //         sign: md5Sign,
    //         plugins: {
    //             DRM: {
    //                 token: event.target.response, // 传入您的后台服务签发的 token
    //                 // certificateUri: 'https://5000.drm.myqcloud.com/huaxida_test/fairplay.cer', // 传入 Fairplay 播放需要用到的证书地址
    //             },
    //             ProgressMarker: {},
    //         }
    //     });
    //
    // }, false);
    // request.open('GET', 'https://demo.vod2.myqcloud.com/drm/gettoken?fileId=' + fileId + '&appId=' + appId, true); // 去您的 token 签发服务地址获取token
    // request.send();


</script>
</body>
</html>