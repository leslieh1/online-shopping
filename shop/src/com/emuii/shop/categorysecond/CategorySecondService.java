package com.emuii.shop.categorysecond;

import com.emuii.shop.category.Category;
import com.emuii.shop.category.CategoryDao;
import com.emuii.shop.utils.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create by Leslie on 2017\12\29 0029.<br>
 */
@Transactional
public class CategorySecondService {

    private CategorySecondDao categorySecondDao;

    public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
        this.categorySecondDao = categorySecondDao;
    }

    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    // 根据页数查询二级列表
    public PageBean<CategorySecond> findByPage(Integer page) {
        System.out.println(page+"===================================1");
        // 封装PageBean
        PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
        // 封装页数
        pageBean.setPage(page);
        // 每页显示的记录数
        Integer limit = 10;
        pageBean.setLimit(limit);
        // 总记录数
        Integer totalCount = categorySecondDao.findCount();
        System.out.println(totalCount+"=========================2");
        pageBean.setTotalCount(totalCount);
        // 总页数
        Integer totalPage = 0;
        if(totalCount % limit == 0){
            totalPage = totalCount/limit;
        }else {
            totalPage = totalCount/limit + 1;
        }
        System.out.println(totalPage+"=========================3");
        pageBean.setTotalPage(totalPage);
        // 每页显示的第一条数据
        Integer begin = (page - 1)* limit;
        List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
    }

    // Service层添加二级列表
    public void save(CategorySecond categorySecond) {
        categorySecondDao.save(categorySecond);
    }

    // Service层根据csid查询二级列表
    public CategorySecond findByCsid(Integer csid) {
        return categorySecondDao.findByCsid(csid);
    }

    // Service修改二级列表
    public void update(CategorySecond categorySecond) {
        categorySecondDao.update(categorySecond);
    }

    // Service删除当前二级列表
    public void delete(CategorySecond categorySecond) {
        categorySecondDao.delete(categorySecond);
    }

//
//    // Service层根据cid查询所属一级列表
//    public Category findByCid(Integer cid) {
//        return categorySecondDao.findByCid(cid);
//    }
public List<CategorySecond> findAll() {
    return categorySecondDao.findAll();
}
}
