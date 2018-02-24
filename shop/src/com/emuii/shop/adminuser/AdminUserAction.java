package com.emuii.shop.adminuser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

/**
 * 管理员Action
 * Create by Leslie on 2017\12\31 0031.<br>
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

    private AdminUser adminUser = new AdminUser();
    @Override
    public AdminUser getModel() {
        return adminUser;
    }

    // 注入Service
    private AdminUserService adminUserService;

    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    // 管理员登录
    public String login() {
        AdminUser existAdminUser = adminUserService.login(adminUser);
        if (existAdminUser == null) {
            // 登录失败
            this.addActionError("用户名或密码错误");
            return LOGIN;
        } else {
            // 登录成功
            ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
            return "loginSuccess";
        }
    }

}
