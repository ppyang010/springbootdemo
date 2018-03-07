layui.use(['table','form','layer',"jquery"], function(){
    var table = layui.table;
    var form = layui.form;
    //监听表头复选框选择
    table.on('checkbox(demo)', function(obj){
        console.log(obj)
    });
    //监听工具条
    table.on('tool(demo)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            $.get("/admin/role/"+data.id,function(d){
                if(d.success){
                    active.add(d.results.item,"detail");
                }else{
                    layer.msg(d.message);
                }

            });
        } else if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
//                    obj.del();
                $.ajax({
                    url: "/admin/role/"+data.id,
                    method: "delete",
                    dataType: "json",

                }).done(function(d){
                    if(d.success){
                        layer.msg("操作成功");
                        layer.close(index);
                        tableView.reload();
                    }else{
                        layer.msg(d.message);
                    }
                });

            });
        } else if(obj.event === 'edit'){
            $.get("/admin/role/"+data.id,function(d){
                if(d.success){
                    active.add(d.results.item);
                }else{
                    layer.msg(d.message);
                }
            });
        } else if(obj.event === 'rolePermission'){
            var that = this;
            //多窗口模式，层叠置顶
            var index = layer.open({
                type: 2 //此处以iframe举例
                ,title: '角色权限'
                ,area: ['800px', '600px']
                ,shade: 0
                ,maxmin: true
//                    ,offset: [ //为了演示，随机坐标
//                        Math.random()*($(window).height()-300)
//                        ,Math.random()*($(window).width()-390)
//                    ]
                ,content: '/p/role/role_permission'
//                    ,btn: ['继续弹出', '全部关闭123'] //只是为了演示
                ,yes: function(){
                    $(that).click();
                }
                ,btn2: function(){
                    layer.closeAll();
                }

                ,zIndex: layer.zIndex //重点1
                ,success: function(layero,index){
                    var body = layui.layer.getChildFrame('body', index);
                    body.find("[name=roleId]").val(data.id);
                }
            });
        }

    });
    //监听复选框事件
    form.on('switch(locked)', function(obj){
        console.dir(obj);
        var dom = obj.elem
            ,id = dom.dataset.id
            ,available = dom.dataset.available
            ,url = "/admin/role/"+id+"/available";
        $.post(url,{available : available},function (data) {
            var msg = "操作成功";
            if(!data.success){
                msg = data.message;
            }
            layer.msg(msg);
        })
    });

    var $ = layui.$,
        //按钮事件
        active = {
            getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('list')
                    ,data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ,getCheckLength: function(){ //获取选中数目
                var checkStatus = table.checkStatus('list')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){ //验证是否全选
                var checkStatus = table.checkStatus('list');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }
            ,add: function(edit,type){
                var that = this;
                //多窗口模式，层叠置顶
                var index = layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '编辑'
                    ,area: ['800px', '600px']
                    ,shade: 0
                    ,maxmin: true
//                    ,offset: [ //为了演示，随机坐标
//                        Math.random()*($(window).height()-300)
//                        ,Math.random()*($(window).width()-390)
//                    ]
                    ,content: '/p/role/add'
//                    ,btn: ['继续弹出', '全部关闭123'] //只是为了演示
                    ,yes: function(){
                        $(that).click();
                    }
                    ,btn2: function(){
                        layer.closeAll();
                    }

                    ,zIndex: layer.zIndex //重点1
                    ,success: function(layero,index){
//                        layer.setTop(layero); //重点2 置顶
                        var body = layui.layer.getChildFrame('body', index);
                        if(edit){
                            console.log(!!edit.locked ? "checked":"");
//                            console.dir(body);
                            console.dir(form);
                            body.find("[name=id]").val(edit.id);
                            body.find("[name=available]").val(edit.available);
                            body.find("[name=role]").val(edit.role);
                            body.find("[name=description]").val(edit.description);
//                            body.find("input[name=locked]").prop("checked","checked");
//                            body.find("[name=locked]").attr('checked',"checked");
//                            layui.jquery("input[name='locked']").prop('checked',true);
//                            layui.form.render('checkbox');
                            console.dir(body.find("[name=locked]"));
                            if(type=="detail"){
                                body.find(".form-ctr").hide();
                            }

                            form.render();
                        }
                    }
                });
            }
        };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    //执行渲染
    var tableView = table.render({
        elem: '#list' //指定原始表格元素选择器（推荐id选择器）
//            ,height: 315 //容器高度
        ,cols:  [[ //标题栏
            {checkbox: true}
            ,{field: 'id', title: 'ID', width: 80}
            ,{field: 'role', title: '角色', width: 120}
            ,{field: 'description', title: '描述', width: 120}
            ,{field: 'available', title: '是否可用', width: 120, align:'center', templet:function(d){
                console.log(d.available);
                if(!!d.available){
                    return '<input type="checkbox" name="available" data-id="'+d.id+'" data-locked="1" lay-filter="locked" lay-skin="switch" lay-text="是|否" checked>'
                }else{
                    return '<input type="checkbox" name="available" data-id="'+d.id+'" data-locked="0" lay-filter="locked" lay-skin="switch" lay-text="是|否">'
                }
            }}
            ,{fixed: 'right', width:300, align:'center', toolbar: '#barDemo'}

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
});