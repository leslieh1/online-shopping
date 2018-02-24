package com.emuii.shop.user;

import java.util.List;

import com.emuii.shop.utils.PageHibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 用户模块Dao层代码
 */
public class UserDao extends HibernateDaoSupport {
    /**
     * DAO层保存用户的代码
     *
     * @param user
     */
    public void save(User user) {
        this.getHibernateTemplate().save(user);
        /*Session session = null;
        Transaction tx = null;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            if (user.getUsername() != null && user.getPassword() != null) {
                session = getSession();
                String sql = "insert into user(username, password, email,phone,name,sex,addr,code) values(" + user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "," + user.getPhone() + "," + user.getName() + "," + user.getSex() + "," + user.getAddr() + "," + user.getCode() + ")";
                System.out.println(sql);
                session = getHibernateTemplate().getSessionFactory().openSession();
                tx = session.beginTransaction();
                session.createSQLQuery(sql);
                conn = session.connection();
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
                tx.commit();
                session.close();
                conn.close();
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }


    /**
     * DAO层根据激活码查询用户
     *
     * @param code
     * @return
     */
    public User findByCode(String code) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User where code = ?", code);
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * DAO层修改用户的方法
     *
     * @param existUser
     */
    public void update(User existUser) {
        this.getHibernateTemplate().update(existUser);
    }

    /**
     * DAO层的登录方法
     *
     * @param user
     * @return
     */
    public User login(User user) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User where username = ? and password = ?", user.getUsername(), user.getPassword());
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public User findByUserName(String username) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User where username = ?", username);
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    // 查询所有用户的数量
    public Integer findTotalCount() {
        List<Long> list = (List<Long>) this.getHibernateTemplate().find("select count(*) from User");
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return null;
    }

    // 根据页数查询当前页的用户
    // String hql, Object[] params, int startIndex, int pageSize
    public List<User> findByPage(Integer begin, Integer limit) {
        String hql = "from User order by uid asc";
        return (List<User>) this.getHibernateTemplate().executeFind(new PageHibernateCallback<User>(hql, null, begin, limit));
    }

    public User findByUid(Integer uid) {
        return this.getHibernateTemplate().get(User.class, uid);
    }

    public void delete(User user) {
        this.getHibernateTemplate().delete(user);
    }
}
