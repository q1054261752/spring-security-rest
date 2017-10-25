package com.imooc.web.async;

/**
 * Created by zkr on 2017/10/24.
 */
public class MockQueue {

    private String placeOrder;
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }
    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        System.out.println("接到下单请求");
        Thread.sleep(1000);
        this.completeOrder = placeOrder;
        System.out.println("下单请求完成," + placeOrder);
    }
    public String getCompleteOrder() {
        return completeOrder;
    }
    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
