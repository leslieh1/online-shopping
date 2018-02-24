package com.emuii.shop.category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * 一级分类Action
 * Create by Leslie on 2018\1\1 0001.<br>
 */
public class CategoryAction extends ActionSupport implements ModelDriven<Category>{

    private Category category = new Category();
    @Override
    public Category getModel() {
        return category;
    }

    // 注入Service
    private CategoryService categoryService;
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

// 列出一级列表
public String adminFindAll(){
    List<Category> cList = categoryService.findAll();
    // 压栈
    ActionContext.getContext().getValueStack().set("cList", cList);
    return "adminFindAllSuccess";
}

    // 保存一级列表
    public String save(){
        categoryService.save(category);
        return "adminSaveSuccess";
    }

    // 编辑数据
    public String edit(){
        category = categoryService.findByCid(category.getCid());
        return "editSuccess";
    }

    // 修改数据
    public String update(){
        categoryService.update(category);
        return "adminUpdateSuccess";
    }
    // 删除数据
    public String delete(){
        categoryService.delete(category);
        return "adminDeleteSuccess";
    }
}
