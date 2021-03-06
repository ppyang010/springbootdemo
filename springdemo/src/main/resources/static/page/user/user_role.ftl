<!DOCTYPE html>
<html lang="en" class="">
<head>
    <meta charset="UTF-8">
    <title>用户角色列表</title>
    <link rel="stylesheet" href="/layui/css/layui.css">

</head>
<body >
<!-- 内容主体区域 -->
<div style="padding: 15px;">

    <div class="layui-btn-group demoTable">
        <button class="layui-btn" data-type="addCheckData">添加选中数据</button>
        <button class="layui-btn" data-type="delCheckData">删除选中数据</button>
    </div>
    <input type="hidden" id="userId" name="userId" />
    <!--数据表格-->
    <table id="list" class="layui-table"  lay-filter="demo">

    </table>


</div>

</body>
<script src="/layui/layui.js"></script>

<script>
    layui.use(['table','form','layer',"jquery"], function(){
        var table = layui.table;
        var form = layui.form;
        var $ = layui.$
        //执行渲染
        var tableView = table.render({
            elem: '#list' //指定原始表格元素选择器（推荐id选择器）
//            ,height: 315 //容器高度
            ,cols:  [[ //标题栏
                {checkbox: true}
                ,{field: 'id', title: 'ID', width: 80}
                ,{field: 'role', title: '角色', width: 120}
                ,{field: 'description', title: '权限描述', width: 120}
                ,{field: 'isAdd', title: '是否添加', width: 120, align:'center', templet:function(d){
//                    console.log(d);
                    var isAdd = false;
                    var userId = $("#userId").val();
                    var userList = d.userSet;
                    console.log("userId = " + userList);
                    console.log(userList);
                    for(var u in userList){
                        if(userList[u].id == userId){
                            isAdd = true;
                            break;
                        }
                    }
                    if(isAdd){
                        return '<input type="checkbox" name="isAdd" data-id="'+d.id+'" data-isAdd="1" lay-filter="locked" lay-skin="switch" lay-text="是|否" checked>'
                    }else{
                        return '<input type="checkbox" name="isAdd" data-id="'+d.id+'" data-isAdd="0" lay-filter="locked" lay-skin="switch" lay-text="是|否">'
                    }
                }}

            ]]
            ,url:"/admin/role/list"
            ,page:true
            ,request: {
                pageName: 'pageNo' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,response: {
                statusName: 'errorCode' //数据状态的字段名称，默认：code
                ,statusCode: 0 //成功的状态码，默认：0
                ,msgName: 'message' //状态信息的字段名称，默认：msg
                ,countName: 'total' //数据总数的字段名称，默认：count
                ,dataName: 'data' //数据列表的字段名称，默认：data
            }
        });


        //监听开关
        form.on('switch(locked)', function(obj){
            console.dir(obj);
            var dom = obj.elem
                ,id = dom.dataset.id
                ,isAdd = +dom.dataset.isadd
                ,addUrl = "/admin/user/role/add"
                ,delUrl = "/admin/user/role/delete";
            console.dir(dom.dataset);
            console.log("isadd = "+isAdd);
            var url = !!isAdd ? delUrl : addUrl;
            $.post(url,{userId : $('#userId').val(),roleId: id},function (data) {
                var msg = "操作成功";
                if(!data.success){
                    msg = data.message;
                }
                layer.msg(msg);
            })
        });



        //按钮事件
        var active = {
            addCheckData: function(){ //添加选中权限
                var checkStatus = table.checkStatus('list')
                    ,data = checkStatus.data;
//                layer.alert(JSON.stringify(data));
                var url="/admin/user/role/add/list";
                var ids = [];
                for(var d in data){
                    ids.push(data[d].id);
                }

                $.post(url,{userId : $('#userId').val(),roleIdList : ids},function (data) {
                    var msg = "操作成功";
                    if(!data.success){
                        msg = data.message;
                    }
                    layer.msg(msg);
                });
                setTimeout(function(){ tableView.reload();},200);

            }
            ,delCheckData: function(){ //删除选中权限
                var checkStatus = table.checkStatus('list')
                    ,data = checkStatus.data;
                var url="/admin/user/role/delete/list";
                var ids = [];
                for(var d in data){
                    ids.push(data[d].id);
                }

                $.post(url,{userId : $('#userId').val(),roleIdList : ids},function (data) {
                    var msg = "操作成功";
                    if(!data.success){
                        msg = data.message;
                    }
                    layer.msg(msg);
                });
                setTimeout(function(){ tableView.reload();},200);
            }

        };
        //绑定按钮事件
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });


</script>
</html>