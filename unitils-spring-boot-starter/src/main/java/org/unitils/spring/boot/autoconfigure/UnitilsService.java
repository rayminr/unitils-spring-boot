package org.unitils.spring.boot.autoconfigure;

public class UnitilsService {

    private String msg;

    public UnitilsService(String msg) {

        this.msg = msg;

    }

    public String getMsg() {

        return msg;

    }

    public void setMsg(String msg) {

        this.msg = msg;

    }

}
