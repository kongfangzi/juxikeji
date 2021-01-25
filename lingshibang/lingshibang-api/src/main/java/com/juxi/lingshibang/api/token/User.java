package com.juxi.lingshibang.api.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongqiang.chai
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Domain domain;
    private String name;
    private String password;
}
