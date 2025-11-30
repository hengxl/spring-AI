package com.hxl.ai.controller;

import com.hxl.ai.constant.SpringAIConst;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * 定义提示语
 * @author hengxiaoliang
 */
@RestController
public class DefinePromptController {

    @Resource(name = "chatClientOne")
    private ChatClient chatClient;

    @GetMapping(value = "/chat", produces = "text/html;charset=UTF-8")
    public Flux<String> recommendFoods(@RequestParam("name") String name,
                                       @RequestParam("style") String style) {
        // 1. 构建动态参数Map（统一传递所有占位符）
        Map<String, Object> params = Map.of(
                SpringAIConst.PLACEHOLDER_NAME, name,
                SpringAIConst.PLACEHOLDER_STYLE, style
        );

        // 2. 系统提示模板（先定义AI角色和规则）
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(SpringAIConst.SYSTEM_TEMPLATE_FOOD);
        Message systemMessage = systemPromptTemplate.createMessage(params);

        // 3. 动态用户需求（后传递精准需求，结合style参数）
        PromptTemplate userPromptTemplate = new PromptTemplate(SpringAIConst.USER_NEEDING_TEMPLATE);
        Message userMessage = userPromptTemplate.createMessage(params);

        // 4. TODO: 关键：Prompt顺序 = SystemMessage → UserMessage
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));

        // 5. 流式返回结果
        return chatClient.prompt(prompt)
                .stream()
                .content();
    }

}
