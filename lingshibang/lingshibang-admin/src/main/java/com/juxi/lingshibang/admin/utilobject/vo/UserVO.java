package com.juxi.lingshibang.admin.utilobject.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 企业
     */
    private String company;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 加盐值
     */
    private String salt;

    /**
     * 性别：0女，1男，2未知
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String telPhone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 微信号
     */
    private String weixinId;

    /**
     * 星座
     */
    private String constellation;

    /**
     * 身份:0 访客，1
     */
    private Integer identity;

    /**
     * 化身
     */
    private String avatar;

    /**
     * 状态：1.生效 2. 修改 3.待验证 0 逻辑删除
     */
    private Boolean status;

    /**
     * 用户积分
     */
    private Integer score;

    /**
     * 头衔
     */
    private String rank;

    /**
     * 用户简介
     */
    private String summary;

    /**
     * 注册来源
     */
    private String source;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 邀请人userCode
     */
    private String inviterCode;

    /**
     * 邀请时间
     */
    private LocalDateTime inviteTime;

    /**
     * 类型 1：企业管理员  2：操作员
     */
    private Boolean type;

    /**
     * 费率类型：
     */
    private Integer poundageType;

    /**
     * 到期时间
     */
    private LocalDateTime dueTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否删除 0-否 1-是
     */
    private Boolean isDelete;

    /**
     * 邮件
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 购买力低价
     */
    private BigDecimal buyLevelLow;

    /**
     * 购买力高价
     */
    private BigDecimal buyLevelHigh;

    /**
     * 用户标签
     */
    private String userTags;

    /**
     * 备注
     */
    private String memo;

    /**
     * 购买商品数
     */
    private Integer orderGoodsNum;

    /**
     * 购买商品均价
     */
    private BigDecimal orderAveragePrice;

    /**
     * 最后登录ip地址
     */
    private String lastLoadIp;

    /**
     * 总交易金额
     */
    private BigDecimal tradingMoney;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoadTime;

    /**
     * 微信二维码图片
     */
    private String wecharImg;

    /**
     * 预留字符串字段
     */
    private String strtag;

    /**
     * 预留整型字段
     */
    private Integer inttag;

    /**
     * 修改人
     */
    private String updator;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "登录Token")
    private String loginToken;


}
