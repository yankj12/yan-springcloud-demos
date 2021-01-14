package com.yan.springcloud.hello.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yan.springcloud.hello.service.HelloRemote;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloApi {

	@Autowired
    HelloRemote HelloRemote;
	
    @RequestMapping("/helloworld")
    public String helloworld(@RequestParam String name) {
        log.info("helloworld "+name+"，this is helloworld");
    	return "helloworld "+name+"，this is helloworld";
    }
    
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return HelloRemote.hello(name);
    }
}
