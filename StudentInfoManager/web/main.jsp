<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/16
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="image/jpeg">
    <title>学生信息管理系统主界面</title>
    <%
         //权限验证
        if(session.getAttribute("currentUser")==null){
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <link rel="stylesheet" type="text/css"
          href="jqueryUi/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="jqueryUi/themes/icon.css">
    <script type="text/javascript" src="jqueryUi/jquery.min.js"></script>
    <script type="text/javascript"
            src="jqueryUi/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="jqueryUi/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            // $(".easyui-layout").layout();
            //数据
            var treeDate = [{
                text: "根",
                children: [{
                    text: "班级信息管理",
                    attributes: {
                        url: "gradeInfoManage.jsp"
                    }
                }, {
                    text: "学生信息管理",
                    attributes: {
                        url: "studentInfoManage.jsp"
                    }
                }
                ]
            }];

            //实例化树菜单
            $("#tree").tree({
                data: treeDate,
                lines: true,
                onClick: function (node) {
                    if(node.attributes){
                        openTab(node.text,node.attributes.url);
                    }
                }
            });

            //新增Tab
            function openTab(text,url) {
                if($("#tabs").tabs("exists",text)){
                    $("#tabs").tabs("select",text);
                }else{
                    var content = "<iframe frameborder='0' scrolling='auto' style='width: 100%;height: 100%' src="+url+"></iframe>";
                    $("#tabs").tabs('add',{
                        title: text,
                        closable: true,
                        content: content
                    });
                }

            }
        });
    </script>
</head>

<body>
<div>
    <%--    <div data-options="region:'north',split:true" style="height:100px;">北</div>--%>
    <%--    <div data-options="region:'south',split:true" style="height:100px;">南</div>--%>
    <%--    <div data-options="region:'west'" style="width:100px;">西</div>--%>
    <%--    <div data-options="region:'center'" style="padding:5px;background:#eee;">中</div>--%>
    <div class="easyui-layout" style="width: 100%;height: 100%;">
        <div region="north" style="height: 80px;padding-right:10px;background-color:#E0EDFF" >
            <div align="left" style="width:80%;float: left"><img alt="" src="images/main.jpg"></div>
            <div style="padding-top: 50px;">
                当前用户:&nbsp;<font color="red">${currentUser.username}</font>
            </div>
        </div>
        <div region="south" style="height: 25px" align="center">版权所有<a href="main.jsp">www.zijian.com</a></div>
        <div region="west" title="导航菜单" split="true" style="width: 150px">
            <ul id="tree"></ul>
        </div>
        <div region="center">
            <div class="easyui-tabs" fit="true" border="false" id="tabs">
                <div title="首页">
                    <div align="center" style="padding-top: 150px"><font color="red" size="10">欢迎使用学生信息管理系统</font></div>
                </div>
            </div>
        </div>

    </div>

</div>

</body>


</html>
