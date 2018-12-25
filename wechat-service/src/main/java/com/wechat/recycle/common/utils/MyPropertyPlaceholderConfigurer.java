package com.wechat.recycle.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static Logger logger = LoggerFactory.getLogger(MyPropertyPlaceholderConfigurer.class);

    private static final String KEY = "qwertyuiopasdfgh";

    /**
     * 处理加密参数
     */
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException {
        List<String> propNames = new ArrayList<>();
        propNames.add("jdbc.username");
        propNames.add("jdbc.password");
        propNames.add("jdbc.url");
        propNames.add("jdbc.driverClassName");
        propNames.add("email.emailAccount");
        propNames.add("email.emailPassword");

        try {
            for (String name : propNames) {
                String value = props.getProperty(name);
                if (value != null) {
                    props.setProperty(name, EncryptUtil.decryptHex(value, KEY));
                }
            }
            super.processProperties(beanFactory, props);
        } catch (Exception e) {
            logger.error("处理加密参数错误>\r\n{}");
        }
    }
}
