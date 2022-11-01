// +----------------------------------------------------------------------
// | 表单提交页面通用表单监听（如有特殊需求可参照此文件写一个单独的js文件，如:sys_config.js）
// +----------------------------------------------------------------------
layui.use(['element', 'form'], function () {
    var element = layui.element;
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function (data) {
        var action = data.form.action;//表单提交URL地址
        var station = $("#station").val();
        var location = $("#choice option:selected").text();
        var type = $("#type option:selected").text();
        var vacancy = $("#vacancy").val();
        var creatTime = $("#creatTime").val();
        var contentTwo = $("#contentTwo").val();
        var tel = $("#tel").val();
        var email = $("#email").val();
        var experience = $("#experience").val();
        var web = $("#web").val();
        var education = $("#education").val();
        console.log(JSON.stringify(data.field));//表单数据
        console.log(location);
        console.log(type);
        console.log(creatTime);
        // $.post(action,data.field,function(obj){
        //     data: JSON.stringify({
        //         station: station,
        //         location: location,
        //         type: type,
        //         vacancy: vacancy,
        //         creatTime: creatTime,
        //         contentTwo: contentTwo,
        //         tel: tel,
        //         email: email,
        //         experience: experience,
        //         web: web,
        //     })

        $.ajax({
            url: action,
            type: "post",
            data: JSON.stringify({
                station: station,
                location: location,
                type: type,
                vacancy: vacancy,
                creatTime: creatTime,
                contentTwo: contentTwo,
                tel: tel,
                email: email,
                experience: experience,
                web: web,
                education: education
            }),
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            success: function (data) {
                if (data != null) {
                    alert("信息发布成功");
                    window.location.href = "backMain"
                }
            }

            //根据返回结果作出相应处
        });
        return false;//注释掉这行代码后，表单将会以普通方式提交表单，否则以ajax方式提交表单
    });
});