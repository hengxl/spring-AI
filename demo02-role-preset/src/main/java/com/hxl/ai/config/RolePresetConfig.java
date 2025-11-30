package com.hxl.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 角色预设配置
 * 给模型一个默认的系统角色，这个角色会作为模型输入
 *
 * @author hengxiaoliang
 */
@Configuration
public class RolePresetConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultSystem("你是一个来自安徽的帅小伙，现在从事Java实习生，你的实力可以冲击大厂，年薪百万！")
                .build();
    }
}
