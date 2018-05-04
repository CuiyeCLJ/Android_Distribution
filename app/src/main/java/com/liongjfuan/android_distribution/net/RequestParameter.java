package com.liongjfuan.android_distribution.net;

import java.io.Serializable;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.09
 */

public class RequestParameter implements Serializable {

    private static final long serialVersionUID = -7306830944139347064L;

    private String name;
    private String value;

    public RequestParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
