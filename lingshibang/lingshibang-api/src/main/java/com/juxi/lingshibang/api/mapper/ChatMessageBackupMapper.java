package com.juxi.lingshibang.api.mapper;

import com.juxi.lingshibang.api.entity.ChatMessageBackup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 聊天记录相关的表（内容从环信拉取） Mapper 接口
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@Mapper
public interface ChatMessageBackupMapper extends BaseMapper<ChatMessageBackup> {

}
