package com.emuii.shop.order;

import com.emuii.shop.cart.Cart;
import com.emuii.shop.cart.CartItem;
import com.emuii.shop.user.User;
import com.emuii.shop.utils.PageBean;
import com.emuii.shop.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单的Action
 * Create by Leslie on 2017\12\30 0030.<br>
 */
public class OrderAction extends ActionSupport {

    private Order order;

    private String pd_FrpId;
    // 付款成功后的需要的参数:
    private String r3_Amt;
    private String r6_Order;
    // 接受订单页面传过来的oid
    private Integer oid;
    // 接收页数
    private Integer page;
    public void setPage(Integer page) {
        this.page = page;
    }
    // 接收订单状态
    private Integer state;

    public void setState(Integer state) {
        this.state = state;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public void setR3_Amt(String r3_Amt) {
        this.r3_Amt = r3_Amt;
    }

    public void setR6_Order(String r6_Order) {
        this.r6_Order = r6_Order;
    }

    public void setPd_FrpId(String pd_FrpId) {
        this.pd_FrpId = pd_FrpId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // 注入OrderService
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    // action层保存订单
    public String saveOrder() throws ParseException {
        order = new Order();
        // 封装订单的数据 =========================
        // 日期格式和数据库不吻合，格式化日期时间格式
        Date initdate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String stringDate = dateFormat.format(initdate);
        Date date = dateFormat.parse(stringDate);
        System.out.println("===================================" + date);
        order.setOrdertime(date);// 下单时间
        order.setState(1);// 1 未付款   2 已经付款   3.已经发货   4 已经收货
        // 有些数据要从购物车取
        HttpServletRequest request = ServletActionContext.getRequest();
        // 获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            this.addActionMessage("您的购物车是空的，快去购物吧！");
            return "msg";
        }
        order.setTotal(cart.getTotal());// 订单总金额
        // 订单所属的用户
        User existUser = (User) request.getSession().getAttribute("existUser");
        if (existUser == null) {
            this.addActionMessage("请先登录再来购物~");
            return "msg";
        }
        order.setUser(existUser);// 订单所属用户
        // 封装订单项数据============================
        // 订单数据从购物项的数据获得
        for (CartItem cartItem : cart.getCartItems()) { // cart.getCartItems() ：获取购物车中的购物项-商品
            // 创建orderItem
            OrderItem orderItem = new OrderItem();
            // 设置订单项数量
            orderItem.setCount(cartItem.getCount());
            // 设置订单项小计
            orderItem.setSubtotal(cartItem.getSubtotal());
            // 设置订单项商品
            orderItem.setProduct(cartItem.getProduct());
            // 设置订单项oid
            orderItem.setOrder(order);// 关联订单

            order.getOrderItems().add(orderItem);
            System.out.println(order.getOrderItems());
        }

        // 清空购物车
        cart.clearCart();

        // 保存订单
        Integer oid = orderService.save(order);
        order.setOid(oid);
//        order = orderService.findByOid(oid);
//        System.out.println(order.toString());
        return "saveOrderSuccess";
    }

    /**
     * 为订单付款的方法:
     * @throws IOException
     */
    public String payOrder() throws IOException, IOException {
        // 修改订单:
        // 查询这个id的订单:
        Order currOrder = orderService.findByOid(order.getOid());
        currOrder.setAddr(order.getAddr());
        currOrder.setName(order.getName());
        currOrder.setPhone(order.getPhone());

        orderService.update(currOrder);
        // 付款:
        // 定义付款的参数:
        String p0_Cmd = "Buy";
        String p1_MerId = "10001126856";
        String p2_Order = order.getOid().toString();
        String p3_Amt = "0.01";
        String p4_Cur = "CNY";
        String p5_Pid = "";
        String p6_Pcat = "";
        String p7_Pdesc = "";
        String p8_Url = "http://localhost:8080/shop/order_callBack.action";
        String p9_SAF = "";
        String pa_MP = "";
        String pr_NeedResponse = "1";
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,pd_FrpId , pr_NeedResponse, keyValue);

        StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        sb.append("p0_Cmd=").append(p0_Cmd).append("&");
        sb.append("p1_MerId=").append(p1_MerId).append("&");
        sb.append("p2_Order=").append(p2_Order).append("&");
        sb.append("p3_Amt=").append(p3_Amt).append("&");
        sb.append("p4_Cur=").append(p4_Cur).append("&");
        sb.append("p5_Pid=").append(p5_Pid).append("&");
        sb.append("p6_Pcat=").append(p6_Pcat).append("&");
        sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        sb.append("p8_Url=").append(p8_Url).append("&");
        sb.append("p9_SAF=").append(p9_SAF).append("&");
        sb.append("pa_MP=").append(pa_MP).append("&");
        sb.append("pd_FrpId=").append(pd_FrpId).append("&");
        sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        sb.append("hmac=").append(hmac);
        System.out.println("================"+sb.toString());
        HttpServletResponse response = ServletActionContext.getResponse();
        response.sendRedirect(sb.toString());
        return NONE;
    }

    /**
     * 付款成功后的回调方法
     */
    public String callBack(){
        Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
        currOrder.setState(2);// 修改订单状态.
        orderService.update(currOrder);

        this.addActionMessage("订单付款成功!订单号:"+r6_Order+" 付款金额:"+r3_Amt);
        return "msg";
    }

    /**
     * 根据用户uid查看我的订单
     */
    public String myOrder(){
        // 获取用户
        User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        List<Order> oList = orderService.findByUid(existUser);
        // 压栈，方便页面取值
        ActionContext.getContext().getValueStack().set("oList",oList);
        return "myOrderSuccess";
    }

    /**
     * 查询订单:
     */
    public String findByOid(){
        order = orderService.findByOid(oid);
        return "findByOidSuccess";
    }

    /**
     * 后台查询所有订单
     */
    public String adminFindAll(){
        PageBean<Order> pageBean = orderService.findByPage(page);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return "adminFindAllSuccess";
    }

    /**
     * 后台按状态查询查询订单
     */
    public String adminFindByState() {
        PageBean<Order> pageBean = orderService.findByPage(state, page);
        // 将PageBean的数据保存到页面:
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return "adminFindByStateSuccess";
    }

    /**
     * 后台修改订单状态
     */
    public String adminUpdateState(){
        // 根据ID查询订单:
        order = orderService.findByOid(oid);
        order.setState(3);
        orderService.update(order);
        return "adminUpdateStateSuccess";
    }
    /**
     * 前台修改订单状态
     */
    public String updateState(){
        // 根据ID查询订单:
        order = orderService.findByOid(oid);
        order.setState(4);
        orderService.update(order);

        return "updateStateSuccess";
    }
}
