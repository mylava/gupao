package com.gupao.edu.lotus.server.listener;

import com.gupao.edu.lotus.server.service.search.IndexService;
import com.gupao.edu.lotus.server.service.search.annotation.IndexSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 创建索引
 */
@Slf4j
@Component
public class CreateIndexListener implements ApplicationListener<ContextRefreshedEvent> , ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Autowired
    private IndexService indexService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> beansMap = applicationContext.getBeansWithAnnotation(IndexSettings.class);
        beansMap.forEach((beanName,bean) ->{
            try {
                if(!indexService.exists(bean.getClass())){
                    indexService.createIndex(bean.getClass());
                }
            } catch (Exception e) {
                log.error("创建索引失败",e);
            }
        });

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }




}
