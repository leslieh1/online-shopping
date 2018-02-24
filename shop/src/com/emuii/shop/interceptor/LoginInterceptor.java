package com.emuii.shop.interceptor;

import com.emuii.shop.adminuser.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * 登录拦截器
 * Create by Leslie on 2018\1\2 0002.<br>
 */
public class LoginInterceptor extends MethodFilterInterceptor{

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
        if(existAdminUser != null){
            return actionInvocation.invoke();
        }else {
            ActionSupport action = (ActionSupport) actionInvocation.getAction();
            action.addActionError("您还没有登录");
            return action.LOGIN;
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
