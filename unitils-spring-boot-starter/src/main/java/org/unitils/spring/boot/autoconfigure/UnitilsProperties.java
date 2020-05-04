package org.unitils.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "unitils.spring.boot")
public class UnitilsProperties {

    private String msg = "default msg";

    public String getMsg() {

        return msg;

    }

    public void setMsg(String msg) {

        this.msg = msg;

    }

}
