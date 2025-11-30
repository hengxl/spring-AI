package com.hxl.ai.config;

import com.hxl.ai.constant.SpringAIConst;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 聊天AI配置
 *
 * @author hengxiaoliang
 */
@Configuration
public class ChatAIConfig {

    @Bean
    public ChatClient chatClientOne(ChatClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public ChatClient chatClientTwo(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultSystem(SpringAIConst.ROLE_PRESET)
                .build();
    }
}
