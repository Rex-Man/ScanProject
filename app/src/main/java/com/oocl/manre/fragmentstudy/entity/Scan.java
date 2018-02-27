package com.oocl.manre.fragmentstudy.entity;

import java.util.Date;

/**
 * Created by manre on 21/02/2018.
 */

public class Scan {

    private Long id;

    private String scanCode;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScanCode() {
        return scanCode;
    }

    public void setScanCode(String scanCode) {
        this.scanCode = scanCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}