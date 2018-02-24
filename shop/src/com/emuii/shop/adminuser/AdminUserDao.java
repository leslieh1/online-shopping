package com.emuii.shop.adminuser;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Create by Leslie on 2017\12\31 0031.<br>
 */
public class AdminUserDao extends HibernateDaoSupport{

    // Dao层管理员登录
    public AdminUser login(AdminUser adminUser) {
        List<AdminUser> list = (List<AdminUser>) this.getHibernateTemplate().find("from AdminUser where username = ? and password = ?", adminUser.getUsername(), adminUser.getPassword());
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
