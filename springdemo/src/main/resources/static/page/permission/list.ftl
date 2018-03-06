<#include "../common/base_marco.ftl"/>
<@base base_title="权限" base_js=["/page/permission/list.js"]>

<div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
        <div class="layui-form" style="margin-bottom: 15px">
            <div class="layui-input-inline" >
                <input type="text" name="searchUsername" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
            <button data-type="add" class="layui-btn">搜索</button>
        </div>
        <div class="layui-btn-group demoTable">
            <button data-type="add" class="layui-btn">添加</button>
            <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
            <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
            <button class="layui-btn" data-type="isAll">验证是否全选</button>
        </div>
        <!--数据表格-->
        <table id="list" class="layui-table"  lay-filter="demo">

        </table>

        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>

    </div>
</div>


</@base>