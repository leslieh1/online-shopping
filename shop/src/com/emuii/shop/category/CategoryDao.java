package com.emuii.shop.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CategoryDao extends HibernateDaoSupport{

    // DAO层的查询所有的一级分类的代码
    public List<Category> findAll() {
        return (List<Category>) this.getHibernateTemplate().find("from Category");
    }

    // Dao层添加一条一级列表数据
    public void save(Category category) {
        this.getHibernateTemplate().save(category);
    }

    // Dao层根据cid查询一级列表
    public Category findByCid(Integer cid) {
        return this.getHibernateTemplate().get(Category.class, cid);
    }

    // Dao层修改一级列表数据
    public void update(Category category) {
        this.getHibernateTemplate().update(category);
    }

    // Dao层删除数据
    public void delete(Category category) {
        Category category1 = this.getHibernateTemplate().get(Category.class, category.getCid());
        this.getHibernateTemplate().delete(category1);
    }
}
