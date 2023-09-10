package com.yingxiu.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -8737457329352065074L;
    private Integer id;
    private String message;
}
