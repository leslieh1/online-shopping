package com.emuii.shop.user;

import com.emuii.shop.utils.MailUtils;
import com.emuii.shop.utils.PageBean;
import com.emuii.shop.utils.UUIDUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户模块:业务层代码
 */
@Transactional
public class UserService {
    // 注入Dao
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 业务层注册用户的代码
     * @param user
     */
    public void regist(User user) {
        // 保存用户:
        user.setState(0);// 0 未激活  1已经激活.
        String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();//生成激活码
        user.setCode(code);
        userDao.save(user);
        // 发送邮件:
        try {
            MailUtils.sendMail(user.getEmail(), code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 业务层根据激活码查询用户 的方法
    public User findByCode(String code) {
        return userDao.findByCode(code);
    }

    // 业务层修改用户的方法
    public void update(User existUser) {
        userDao.update(existUser);
    }

    // 业务层登录的方法
    public User login(User user) {
        return userDao.login(user);
    }

    // 业务层通过用户名查询用户
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    // 业务层后台根据页面查询所有用户
    public PageBean<User> findByPage(Integer page) {
        // 封装PageBean
        PageBean<User> pageBean = new PageBean<User>();
        // 封装页数
        pageBean.setPage(page);
        // 每页显示的记录数
        Integer limit = 10;
        pageBean.setLimit(limit);
        // 总记录数
        Integer totalCount = userDao.findTotalCount();
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        Integer totalPage = 0;
        if(totalCount % limit == 0){
            totalPage = totalCount/limit;
        }else {
            totalPage = totalCount/limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        // 每页显示的第一条数据
        Integer begin = (page-1)*limit;
        System.out.println(begin);

        List<User> list = userDao.findByPage(begin, limit);
        System.out.println(list);
        pageBean.setList(list);
        return pageBean;
    }

    public User findByUid(Integer uid) {
       return userDao.findByUid(uid);
    }

    public void delete(User user) {
        userDao.delete(user);
    }
}
