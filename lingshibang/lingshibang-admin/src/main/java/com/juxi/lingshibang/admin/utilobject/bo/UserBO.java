package com.juxi.lingshibang.admin.utilobject.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("登录")
@Data
public class UserBO {

    /**
     * 用户编号
     */
    @ExcelProperty("用户编号")
    private String userCode;

    /**
     * 企业
     */
    @ExcelProperty("用户企业")
    private String company;

    /**
     * 姓名
     */
    @ExcelProperty("用户姓名")
    private String userName;

    /**
     * 昵称
     */
    @ExcelProperty("用户昵称")
    private String nickName;

    @ExcelProperty("用户角色")
    private String roles;

    private LocalDateTime dueTime;

    private List<String> departmentIdList;

    private List<String> roleIdList;
}
