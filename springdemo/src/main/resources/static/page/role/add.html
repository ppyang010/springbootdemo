<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>add</title>
    <link rel="stylesheet" href="/layui/css/layui.css">

</head>
<body>
<form class="layui-form" action="" method="post">
    <input type="hidden" name="id"/>
    <input type="hidden" name="available"/>

    <div class="layui-form-item">
        <label class="layui-form-label">角色名</label>
        <div class="layui-input-inline">
            <input type="text" name="role" required  lay-verify="required" placeholder="请输入角色名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-inline">
            <input type="text" name="description" required lay-verify="required" placeholder="角色描述" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">辅助文字</div>
    </div>


    <!--<div class="layui-form-item">-->
        <!--<label class="layui-form-label">锁</label>-->
        <!--<div class="layui-input-block">-->
            <!--<input type="checkbox" name="locked" value="1" lay-skin="switch" >-->
        <!--</div>-->
    <!--</div>-->

    <div class="layui-form-item form-ctr">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="/layui/layui.js"></script>
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
//            layer.msg(JSON.stringify(data.field));
            console.dir(data.field);
            //当你在iframe页面关闭自身时


            $.post("/admin/role",data.field,function(d){
                console.log(d);
                if(d.success){
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    //刷新父页面
                    parent.location.reload();
                }else{
                    layer.msg(JSON.stringify(d.message));
                }

            });

            return false;
        });
    });
</script>
</body>
</html>