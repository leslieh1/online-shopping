package com.emuii.shop.cart;

import com.emuii.shop.product.Product;
import com.emuii.shop.product.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by Leslie on 2017\12\30 0030.<br>
 */
public class CartAction extends ActionSupport {
    // 接收pid
    private Integer pid;//<input type="hidden" id="<s:properties value="model.pid" />" />
    // 接收count
    private Integer count;
    // 注入productService
    private ProductService productService;

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 从session范围获得购物车
     */
    public Cart getCart(HttpServletRequest request) {
        // 从session的范围获得Cart对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 判断:
        if (cart == null) {
            // 擦混关键购物车对象
            cart = new Cart();
            // 将购物车对象放入到session范围
            request.getSession().setAttribute("cart", cart);
        }
        return cart;
    }

    /**
     * 添加购物车
     *
     * @return
     */
    public String addCart() {
        // 查询商品信息
        Product product = productService.findByPid(pid);
        // 创建一个购物项
        CartItem cartItem = new CartItem();
        cartItem.setCount(count);
        cartItem.setProduct(product);
        // 获取购物车，需要依赖request对象
        HttpServletRequest request = ServletActionContext.getRequest();
        Cart cart = getCart(request);
        cart.addCart(cartItem);

        return "addCartSuccess";
    }

    /**
     * 清空购物车
     */
    public String clearCart() {
//        Cart cart = new Cart();// 每次清空动作都new一个cart不好，可以从session中取
        HttpServletRequest request = ServletActionContext.getRequest();
        // 获取Cart对象.
        Cart cart = getCart(request);
        cart.clearCart();

        return "clearCartSuccess";
    }

    /**
     * 移除购物项
     */
    public String removeCart() {
        HttpServletRequest request = ServletActionContext.getRequest();
        // 获取Cart对象.
        Cart cart = getCart(request);
        cart.removeCart(pid);

        return "removeCartSuccess";
    }

    /**
     * 跳转到购物车
     */
    public String myCart(){
        return "myCartSuccess";
    }
}
