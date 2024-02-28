package com.example.studybuddybackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求参数
 *
 * @author Willow
 **/
@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = -8705889985280765578L;

    /**
     * 页面大小
     */
    protected int pageSize = 10;

    /**
     * 页号
     */
    protected int pageNum = 1;

}
