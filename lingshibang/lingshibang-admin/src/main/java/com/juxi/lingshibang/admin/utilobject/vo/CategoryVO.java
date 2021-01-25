package com.juxi.lingshibang.admin.utilobject.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CategoryVO {

    /**
     * 类目id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 父category_id
     */
    private Integer parentId;

    /**
     * 背景图片地址
     */
    private String categoryBackImage;

    /**
     * 有效状态：0 无效 1 有效
     */
    private Integer status;

    /**
     * 推荐分类0:不推荐,1:推荐
     */
    private Integer isRecommend;
}
