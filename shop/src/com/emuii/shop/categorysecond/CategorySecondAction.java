package com.emuii.shop.categorysecond;

import com.emuii.shop.category.Category;
import com.emuii.shop.category.CategoryService;
import com.emuii.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.util.ValueStack;

import java.util.List;

/**
 * Create by Leslie on 2017\12\29 0029.<br>
 */
public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

    private CategorySecond categorySecond = new CategorySecond();
    @Override
    public CategorySecond getModel() {
        return categorySecond;
    }

    // 接收页数
    private Integer page;
    // 接收添加二级列表时关联的一级列表cid
    private Integer cid;
    // 注入Service
    private CategoryService categoryService;
    private CategorySecondService categorySecondService;

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }

    // 查询所有二级列表
    public String adminFindAll(){
        PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
        // 压栈
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return "adminFindAllSuccess";
    }

    /**
     * 二级分类管理:跳转到添加页面的方法.
     */
    public String addPage(){
        // 查询一级分类的列表:
        List<Category> cList = categoryService.findAll();
        // 压栈:
        ActionContext.getContext().getValueStack().set("cList", cList);
        return "adminAddPageSuccess";
    }

    /**
     * 二级分类的保存:
     */
    public String save(){
        Category category = new Category();
        category.setCid(cid);
        // 二级分类对象:
        categorySecond.setCategory(category);
        // 调用Serviec保存
        categorySecondService.save(categorySecond);
        return "adminSaveSuccess";

    }

    /**
     * 编辑二级分类列表
     */
    public String edit(){
        // 查询当前的二级列表数据
        Integer csid = categorySecond.getCsid();
        System.out.println(csid+"================================csid");
        categorySecond = categorySecondService.findByCsid(csid);
        // 查询所有一级列表
        List<Category> cList = categoryService.findAll();
        ActionContext.getContext().getValueStack().set("cList", cList);
        // 查询当前的一级列表数据
        Integer id = categorySecond.getCategory().getCid();
        System.out.println(id+"================================id");
        Category category = categoryService.findByCid(id);
        ActionContext.getContext().getValueStack().set("category", category);
        return "csEditSuccess";
    }
    /**
     * 修改二级列表
     */
    public String update(){
        Category category = categoryService.findByCid(cid);
        categorySecond.setCategory(category);
        categorySecondService.update(categorySecond);
        return "adminUpdateSuccess";
    }

    /**
     * 删除当前二级分类列表
     */
    public String delete(){
        /*Integer page1 = 0;
        Integer index = categorySecond.getCsid();
        if(index % 10 == 0){
            page1 = index/10;
        }else {
            page1 = index/10 + 1;
        }
        ActionContext.getContext().getValueStack().set("page1", page1);*/
        categorySecondService.delete(categorySecond);

        return "adminDeleteSuccess";
    }
}
