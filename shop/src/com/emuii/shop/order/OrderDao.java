package com.emuii.shop.order;

import com.emuii.shop.user.User;
import com.emuii.shop.utils.PageHibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 订单的Dao
 * Create by Leslie on 2017\12\30 0030.<br>
 */
public class OrderDao extends HibernateDaoSupport {

    // 持久层保存订单
    public Integer save(Order order) {
        Integer oid = (Integer) this.getHibernateTemplate().save(order);
        return oid;
    }

    // 持久层根据oid查询订单
    public Order findByOid(Integer oid) {
        return this.getHibernateTemplate().get(Order.class, oid);
    }

    // 持久层删除订单
    public void removeOrder(Integer oid) {

        this.getHibernateTemplate().delete(oid);
    }

    // 持久层更新订单信息
    public void update(Order currOrder) {
        this.getHibernateTemplate().update(currOrder);
    }

    // 持久层根据uid查找用户所有订单
    public List<Order> findByUid(User existUser) {
        return (List<Order>) this.getHibernateTemplate().find("from Order o where o.user.uid = ? order by ordertime desc", existUser.getUid());
    }

    // 查询订单总记录数
    public Integer findCount() {
        List<Long> list = (List<Long>) this.getHibernateTemplate().find("select count(*) from Order ");
        return list.get(0).intValue();
    }

    // 根据页数查询订单
    public List<Order> findByPage(Integer begin, Integer limit) {
        String hql = "from Order order by ordertime desc";
        List<Order> list = (List<Order>) this.getHibernateTemplate().executeFind(new PageHibernateCallback<Order>(hql,null,begin,limit));
        return list;
    }
    public Integer findCount(Integer state) {
        List<Long> list = (List<Long>) this.getHibernateTemplate().find("select count(*) from Order where state = ?",state);
        return list.get(0).intValue();
    }

    public List<Order> findByPage(Integer state, Integer begin, Integer limit) {
        String hql = "from Order where state = ? order by ordertime desc";
        List<Order> list = (List<Order>) this.getHibernateTemplate().executeFind(new PageHibernateCallback<Order>(hql, new Object[]{state}, begin, limit));
        return list;
    }
}
