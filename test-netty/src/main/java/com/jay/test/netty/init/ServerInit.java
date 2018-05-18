package com.jay.test.netty.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/5/8 9:14
 * @Modified by :
 */
@Component
@Slf4j
public class ServerInit implements InitializingBean,ApplicationContextAware {

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("spring容器启动时调用");
        //启动netty服务端
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
