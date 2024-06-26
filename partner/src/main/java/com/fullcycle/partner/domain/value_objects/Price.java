package com.fullcycle.partner.domain.value_objects;

import com.fullcycle.partner.domain.exceptions.Unprocessable;

public class Price {
    private Double price;

    public Price(Double price) throws Exception {
        this.price = this.validatePrice(price);
    }

    private double validatePrice(Double price) throws Exception {
        if(price < 0) throw new Unprocessable("Price is invalid");
        return price;
    }
    public double getPrice() {
        return this.price;
    }
}
