package com.order.microservice;

public class CustomerOrder {

    private String item;

    private int quantity;

    private double amount;

    private String paymentMode;

    private long orderId;

    private String address;

    public CustomerOrder() {
    	
    }
    
    public CustomerOrder(String item, int quantity, double amount, String paymentMode, long orderId, String address) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.orderId = orderId;
		this.address = address;
	}

	public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
