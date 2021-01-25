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
public class Identity {
    private PassWord password;
    private String [] methods;
}
