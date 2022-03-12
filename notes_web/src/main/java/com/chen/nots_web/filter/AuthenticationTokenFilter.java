package com.chen.nots_web.filter;

import cn.hutool.core.util.StrUtil;
import com.chen.nots_web.global.BaseSysConf;
import com.chen.nots_web.global.RedisConf;
import com.chen.nots_web.global.SysConf;
import com.chen.nots_web.utils.JsonUtils;
import com.chen.nots_web.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        //得到请求头信息authorization信息
        String accessToken = request.getHeader("Authorization");

        if (accessToken != null) {
            //从Redis中获取内容
            String userInfo = redisUtil.get(RedisConf.LOGIN_TOKEN_KEY + RedisConf.SEGMENTATION + accessToken);
            if (!StrUtil.isBlank(userInfo)) {
                Map<String, Object> map = JsonUtils.jsonToMap(userInfo);
                //把userUid存储到 request中
                request.setAttribute(SysConf.TOKEN, accessToken);
                request.setAttribute(SysConf.USER_UID, map.get(BaseSysConf.USER_UID));
                request.setAttribute(SysConf.USER_NAME, map.get(BaseSysConf.USER_NAME));
                log.info("解析出来的用户:{}", map.get(BaseSysConf.USER_NAME));
            }
        }
        chain.doFilter(request, response);
    }
}
