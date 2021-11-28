package com.midastrash.bank.onlinebank.pojo;

public class TransferRequest {
    private int amount;
    private String route;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
