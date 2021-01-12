package com.yan.springcloud.echo.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EchoApi {

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        log.info("hello "+name+"，this is first messge");
    	return "hello "+name+"，this is first messge";
    }
	
}
