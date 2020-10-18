package ru.link.todoix.Test;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TestController {
    private static final String template = "Это всего лишь тест, %s:)";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/test")
    public TestObject test(@RequestParam(value = "name", required = false, defaultValue = "аноним") String name){
        return new TestObject(counter.incrementAndGet(), String.format(template, name));
    }
}
