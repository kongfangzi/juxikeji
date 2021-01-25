package com.juxi.lingshibang.admin.mapper;

import com.juxi.lingshibang.admin.entity.ChatMessageBackup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 聊天记录相关的表（内容从环信拉取） Mapper 接口
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Mapper
public interface ChatMessageBackupMapper extends BaseMapper<ChatMessageBackup> {

}
