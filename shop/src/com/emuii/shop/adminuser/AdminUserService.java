package com.emuii.shop.adminuser;

import org.springframework.transaction.annotation.Transactional;

/**
 * Create by Leslie on 2017\12\31 0031.<br>
 */
@Transactional
public class AdminUserService {

    private AdminUserDao adminUserDao;

    public void setAdminUserDao(AdminUserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
    }

    // Service层管理员登录
    public AdminUser login(AdminUser adminUser) {
        return adminUserDao.login(adminUser);
    }
}
