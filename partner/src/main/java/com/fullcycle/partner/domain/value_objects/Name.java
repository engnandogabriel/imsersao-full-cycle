package com.fullcycle.partner.domain.value_objects;

import com.fullcycle.partner.domain.exceptions.Unprocessable;

public class Name {
    private String value;

    public Name(String value) throws Exception {
        this.value = this.validate(value);
    }
    private String validate(String value) throws Exception {
        if(value.length() < 5) throw new Unprocessable("Length of name is invalid");
        return value;
    }

    public String getName() {
        return value;
    }
}
