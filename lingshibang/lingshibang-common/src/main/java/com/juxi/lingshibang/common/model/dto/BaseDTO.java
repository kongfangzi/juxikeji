package com.juxi.lingshibang.common.model.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@Data
public abstract class BaseDTO implements Serializable {
    protected static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
