package com.juxi.lingshibang.api.service.impl;

import com.juxi.lingshibang.api.entity.ChatMessageBackup;
import com.juxi.lingshibang.api.mapper.ChatMessageBackupMapper;
import com.juxi.lingshibang.api.service.IChatMessageBackupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天记录相关的表（内容从环信拉取） 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@Service
public class ChatMessageBackupServiceImpl extends ServiceImpl<ChatMessageBackupMapper, ChatMessageBackup> implements IChatMessageBackupService {

}
