package com.juxi.lingshibang.api.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    private Identity identity;
    private Scope scope;
}
