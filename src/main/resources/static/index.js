//一般直接写在一个js文件中
$(function () {
    layui.use(['layer', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form;

        layer.msg('Hello World');
    });

    $.get("/json",function (data,status) {
        $("#getDiv").text("get:"+"name:"+data.name+",age:"+data.age)
    })

    $.post("/ps",{},function (data,status) {
        $("#postDiv").text("post:"+"name:"+data.name+",age:"+data.age)
    })




})