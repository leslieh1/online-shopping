package com.emuii.shop.categorysecond;

import com.emuii.shop.category.Category;
import com.emuii.shop.utils.PageHibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Create by Leslie on 2017\12\29 0029.<br>
 */
public class CategorySecondDao extends HibernateDaoSupport{

    // Dao层查询二级列表的总条数
    public Integer findCount() {
        List<Long> list = (List<Long>) this.getHibernateTemplate().find("select count(*) from CategorySecond");
        if(list.size() > 0){
            return list.get(0).intValue();
        }
        return null;
    }

    /**
     * Dao层根据页数查询二级列表
     * @param begin 每页的第一条数据
     * @param limit 每页显示的数据数量
     * @return 带有页面的二级列表数据
     */
    public List<CategorySecond> findByPage(Integer begin, Integer limit) {
        String hql = "from CategorySecond";
        List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
        if(list.size() > 0){
            return list;
        }
        return null;
    }

    /**
     * Dao层添加二级列表
     * @param categorySecond
     */
    public void save(CategorySecond categorySecond) {
        this.getHibernateTemplate().save(categorySecond);
    }

    /**
     * Dao层根据csid查询二级列表
     * @param csid
     * @return
     */
    public CategorySecond findByCsid(Integer csid) {
        return this.getHibernateTemplate().get(CategorySecond.class, csid);
    }

    // Dao层修改二级列表
    public void update(CategorySecond categorySecond) {
        this.getHibernateTemplate().update(categorySecond);
    }

    // Dao层根据csid删除二级列表
    public void delete(CategorySecond categorySecond) {
        this.getHibernateTemplate().delete(categorySecond);
    }

    public List<CategorySecond> findAll() {
        return (List<CategorySecond>) this.getHibernateTemplate().find("from CategorySecond");
    }
}
