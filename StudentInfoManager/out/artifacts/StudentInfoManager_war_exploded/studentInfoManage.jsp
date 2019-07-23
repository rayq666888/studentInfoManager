<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/17
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理</title>
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
        var url;

        function openStudentModifyDialog() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if(selectedRows.length!==1){
                $.messager.alert("系统提示","请选择一条要编辑的数据!");
                return ;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle","编辑学生信息");
            $("#fm").form("load",row);
            url = "StudentSave?stuId="+row.stuId;
        }
        
        function  openStudentAddDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "添加学生信息");
            url = "StudentSave";
        }
        function  saveStudent() {
            $("#fm").form("submit",{
                url: url,
                onSubmit : function () {
                    if($("#sex").combobox("getValue")===""){
                        $.messager.alert("系统提示","请选择性别");
                        return false ;

                    }
                    if($("#gradeId").combobox("getValue")===""){
                        $.messager.alert("系统提示","请选择班级");
                        return false ;

                    }

                    return $(this).form("validate");
                },
                success : function (result) {
                    if(result.errorMsg){
                        $.messager.alert("系统提示",result.errorMsg);
                        return;
                    }else{
                        $.messager.alert("系统提示","保存成功");
                        reset();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }

        function reset() {
            $("#stuNo").val("");
            $("#stuName").val("");
            $("#sex").combobox("setValue","");
            $("#birthday").datebox("setValue","");
            $("#gradeId").combobox("setValue","");
            $("#email").val("");
            $("#stuDesc").val("");
        }

        function closeStudentDialog() {
            $("#dlg").dialog("close");
            reset();
        }
        
        function searchStudent() {
            $("#dg").datagrid("load",{
                stuNo: $("#s_stuNo").val(),
                stuName: $("#s_stuName").val(),
                sex: $("#s_sex").datebox("getValue"),
                bbirthday: $("#s_bbirthday").combobox("getValue"),
                nnirthday: $("#s_nnirthday").combobox("getValue"),
                gradeId: $("#s_gradeId").datebox("getValue")
            });

        }

        function deleteStudent() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length === 0) {
                $.messager.alert("系统提示", "请选择要删除的数据");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].stuId);
            }
            var delIds = strIds.join(",");
            $.messager.confirm("系统提示", "您确定要删除这<font color='red'>" + selectedRows.length + "</font>条数据吗", function (r) {
                if (r) {
                    $.post("StudentDelete", {"delIds": delIds}, function (result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "您已成功删除<font color='red'>" + result.delNums + "</font>条数据!")
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("系统提示", result.errorMsg)
                        }
                    }, "json");
                } else {
                }
            });
        }
    </script>
</head>
<body style="margin: 5px">
<table id="dg" title="学生信息管理" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" fit="true" url="StudentList" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="stuId" width="50" align="center">编号</th>
        <th field="stuNo" width="100" align="center">学号</th>
        <th field="stuName" width="100" align="center">姓名</th>
        <th field="sex" width="100" align="center">性别</th>
        <th field="birthday" width="100" align="center">生日</th>
        <th field="gradeId" width="100" align="center" hidden="true">班级ID</th>
        <th field="gradeName" width="100" align="center">班级</th>
        <th field="email" width="100" align="center">邮箱</th>
        <th field="stuDesc" width="250" align="center">学生备注</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openStudentAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openStudentModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript: deleteStudent()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        &nbsp;学号:&nbsp;<input type="text" name="s_stuNo" id="s_stuNo" size="10">
        &nbsp;姓名:&nbsp;<input type="text" name="s_stuName" id="s_stuName" size="10">
        &nbsp;性别:&nbsp;<select style="width: 80px;" class="easyui-combobox" id="s_sex" name="s_sex" panelHeight="auto" editable="false">
            <option value="">请选择</option>
            <option value="男" selected>男</option>
            <option value="女">女</option>
        </select>
        &nbsp;出生日期:&nbsp;<input class="easyui-datebox" name="s_bbirthday" id="s_bbirthday" size="12" editable="false">-><input class="easyui-datebox" name="s_nnirthday" id="s_nnirthday" size="12" editable="false">
        &nbsp;班级:&nbsp;<input class="easyui-combobox" name="s_gradeId" id="s_gradeId"  size="12"
                              data-options="editable:false,valueField:'gradeId',textField:'gradeName',url:'GradeComboList'">
        <a href="javascript: searchStudent()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width:670px;height:350px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post" >
        <table cellspacing = "5px">
            <tr>
                <td >学号:</td>
                <td><input type="text" name="stuNo" id="stuNo" class="easyui-validatebox" required="true"></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td >姓名:</td>
                <td><input type="text" name="stuName" id="stuName" class="easyui-validatebox" required="true"></td>
            </tr>
            <tr>
                <td >性别:</td>
                <td>
                    <select  style="width: 160px;" class="easyui-combobox" id="sex" name="sex" panelHeight="auto"  editable="false">
                        <option value="" selected>请选择</option>
                        <option value="男" >男</option>
                        <option value="女">女</option>
                    </select>
                </td>
                <td></td>
                <td >出生日期:</td>
                <td><input class="easyui-datebox" name="birthday" id="birthday"  editable="false" required="true"></td>
            </tr>
            <tr>
                <td >班级名称:</td>
                <td><input class="easyui-combobox" name="gradeId" id="gradeId"
                           data-options="editable:false,valueField:'gradeId',textField:'gradeName',url:'GradeComboList'">
                </td>
                <td></td>
                <td >email:</td>
                <td><input type="text" name="email" id="email" class="easyui-validatebox" required="true" validType="email"></td>
            </tr>
            <tr>
                <td valign="top">学生备注</td>
                <td colspan="4"><textarea rows="9" cols="65" name="stuDesc" id="stuDesc"></textarea></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveStudent()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeStudentDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>
