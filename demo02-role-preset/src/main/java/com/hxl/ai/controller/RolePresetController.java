package com.hxl.ai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * 角色预设
 * @author hengxiaoliang
 */
@RestController
public class RolePresetController {

    @Resource(name = "chatClientTwo")
    private ChatClient client;

    @GetMapping("/chat")
    public String chat(@RequestParam("msg") String msg) {
        return client.prompt() // 创建一个 PromptBuilder 对象
                .user(msg) // 设置用户输入
                .call() // 调用 ChatClient 对象的 call 方法
                .content(); // 获取返回结果
    }

    /**
     * 角色预设流式输出
     */
    @GetMapping(value = "/chat/stream",
                produces = "text/html;charset=UTF-8")
    public Flux<String> chatStream(@RequestParam("msg") String msg) {
        return client.prompt() // 创建一个 PromptBuilder 对象
                .user(msg) // 设置用户输入
                .stream() // 调用 ChatClient 对象的 stream 方法
                .content(); // 获取返回结果
    }
}
