<#compress>
    <#macro base base_title base_keywords="" base_js=[] base_css=[]>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>layout 后台大布局 - Layui</title>
        <link rel="stylesheet" href="/layui/css/layui.css">
    </head>
    <body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <#--layui-header-->
        <#include "top.ftl"/>

        <#--layui-left-->
        <#include "left.ftl"/>

        <#--<div class="layui-body">-->
            <#--<!-- 内容主体区域 &ndash;&gt;-->
            <#--<div style="padding: 15px;">内容主体区域-->
            <#--</div>-->
        <#--</div>-->
        <#--﻿//该指令表明下一级扩展页内容将被嵌套至此 layui-body-->
        <#--layui-body-->
        <#nested>
        <#--footer-->
        <#include "footer.ftl" />
    </div>
    <script src="/layui/layui.js"></script>
    ﻿<#--//遍历js-->
    <#list base_js as j>
        <script src="${j}"></script>
    </#list>
    <#--<script>-->
        <#--//JavaScript代码区域-->
        <#--layui.use('element', function(){-->
            <#--var element = layui.element;-->

        <#--});-->
    <#--</script>-->
    </body>
    </html>
    </#macro>
</#compress>