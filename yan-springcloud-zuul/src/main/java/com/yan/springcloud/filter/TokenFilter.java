package com.yan.springcloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre"; // 可以在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return 0; // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("--->>> TokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());

        // 如果要管控到每个微服务中的每个具体服务的话
        // senderMsNo 调用者微服务编号, token, url
        String token = request.getParameter("token");// 获取请求的参数
        String sender = request.getParameter("sender");
        String url = "";
        
        if (this.checkToken(token, sender, url)) {
            ctx.setSendZuulResponse(true); //对请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty");
            ctx.set("isSuccess", false);
            return null;
        }
    }
    
    /**
     * 校验token是否有效
     * @param token
     * @param sender
     * @param url
     * @return
     */
    public boolean checkToken(String token, String sender, String url) {
    	// TODO 这里可以写具体的校验逻辑，示例程序部分只判断是否有token
    	return StringUtils.isNotBlank(token);
    }

}
