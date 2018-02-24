<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
</HEAD>
<body>
<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/user_update.action"
      method="post" enctype="multipart/form-data">
    &nbsp;
    <table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee"
           style="border: 1px solid #8ba7e3" border="0">
        <input type="hidden" name="uid" value="<s:property value="model.uid" />">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
                height="26">
                <STRONG>编辑用户</STRONG>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01" class="bg">
                用户名：
            </td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <input type="text" name="username" value="<s:property value="model.username"/>" id="username" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                姓名：
            </td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <input type="text" name="name" value="<s:property value="model.name"/>" id="name" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                电子邮箱：
            </td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <input type="text" name="email" value="<s:property value="model.email"/>" id="email" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                性别：
            </td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <input type="text" name="sex" value="<s:property value="model.sex"/>" id="sex" class="bg"/>
            </td>
        </tr>



        <TR>
            <td align="center" colSpan="4" class="sep1">
                <img src="${pageContext.request.contextPath}/images/shim.gif">
            </td>
        </TR>


        <tr>
            <td class="ta_01" style="WIDTH: 100%" align="center"
                bgColor="#f5fafe" colSpan="4">
                <button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
                    &#30830;&#23450;
                </button>

                <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
                <button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

                <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
                <INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
                <span id="Label1"></span>
            </td>
        </tr>
    </table>
</form>
</body>
</HTML>