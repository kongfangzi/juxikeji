package com.juxi.lingshibang.admin.service.impl;

import com.juxi.lingshibang.admin.entity.ChatMessageBackup;
import com.juxi.lingshibang.admin.mapper.ChatMessageBackupMapper;
import com.juxi.lingshibang.admin.service.IChatMessageBackupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天记录相关的表（内容从环信拉取） 服务实现类
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@Service
public class ChatMessageBackupServiceImpl extends ServiceImpl<ChatMessageBackupMapper, ChatMessageBackup> implements IChatMessageBackupService {

}
