package com.emuii.shop.category;

import java.util.List;

import com.emuii.shop.categorysecond.CategorySecond;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CategoryService {
    // 注入DAO
    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    // 业务层查询所有的一级分类的方法
    public List<Category> findAll() {
        System.out.println("findAll==============Service");
        return categoryDao.findAll();
    }

    // 业务层添加一级列表数据
    public void save(Category category) {
        categoryDao.save(category);
    }

    // 业务层根据cid查询一级列表
    public Category findByCid(Integer cid) {
        return categoryDao.findByCid(cid);
    }

    // 业务层修改一级列表
    public void update(Category category) {
        categoryDao.update(category);
    }

    // 业务层删除一级列表
    public void delete(Category category) {
        categoryDao.delete(category);
    }

}
