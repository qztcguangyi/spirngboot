package com.learning.mvc.util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 描述 封装读取配置文件的工具类
 * @author Flex Hu
 * @created 2017年1月5日 下午4:10:42
 */
@Slf4j
public class PlatPropUtil {
    /**
     * 获取配置文件对象
     * @param propFileName:文件名称,例如(conf/config.properties)
     * @return
     */
    public static Properties readProperties(String propFileName){
        Properties p = new Properties();
        InputStreamReader inputStream = null;
        try {
            inputStream = new InputStreamReader(PlatPropUtil.class.getClassLoader().
                    getResourceAsStream(propFileName), "UTF-8");
            p.load(inputStream);
        } catch (IOException e) {
            log.error("read error",e);
        }finally{
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("io",e);
                }
            }
        }
        return p;
    }
    
    /**
     * 动态读取配置文件的值
     * @param propFileName:文件名称,例如(conf/config.properties)
     * @param key:KEY
     * @return
     */
    public static String getDynamicPropertyValue(String propFileName,String key){
        Properties pro = readProperties(propFileName);
        return (String) pro.get(key);
    }

    /**
     *
     *  //1.优先级：依次递增(取到就返回)
     *  //2.动态读取测试 默认无法动态 --》 使用PlatPropUtil解决(先执行 mvn clean package解压出lib) D:\evecom\springmvc\springboot\target\classes>java -classpath ".;./lib/*" -m com
     *  //.learning.mvc.SpringbootApplication
     *  //3.TODO T next.getSource() 为啥会打印出东西，而不是hashcode呢？ 2020年11月28日00:38:59
     * @Description: 获取所有环境变量(静态)
     * @Param: []
     * @return: void
     * @Author: Gavin Zhang
     * @Date: 2020/11/28
    */
    public static void printAllProperties() {
        StandardServletEnvironment bean = BeanUtil.getBean(StandardServletEnvironment.class);
        MutablePropertySources propertySources = bean.getPropertySources();

        Iterator<PropertySource<?>> iterator = propertySources.iterator();
        while (iterator.hasNext()) {
            PropertySource<?> next = iterator.next();
            log.info(next.getName());
            log.info(next.getSource().toString());
            log.info("********************************");
        }
    }


    /**
     * @Description: getAllProperties
     * @Param: []
     * @return: void
     * @Author: Gavin Zhang
     * @Date: 2021/3/17
    */
    public static Map<String,String> getAllProperties() {
        StandardServletEnvironment bean = BeanUtil.getBean(StandardServletEnvironment.class);
        MutablePropertySources propertySources = bean.getPropertySources();
        Map<String,String> env = new HashMap<>();
        Iterator<PropertySource<?>> iterator = propertySources.iterator();
        while (iterator.hasNext()) {
            PropertySource<?> next = iterator.next();
            //怎么读取Object呢？并最终转成map 并最终可以取到classpath:/static/
            //TODO servletConfigInitParams --》 java.lang.Object@154d7eb ，servletContextInitParams --》org.apache.catalina.core.ApplicationContextFacade@21de8565
            env.put(next.getName(),next.getSource().toString());
        }
        return env;
    }

    /**
     * @Description: 获取多个返回值(静态)
     * @Param: [key]，逗号分隔
     * @return: java.lang.String
     * @Author: Gavin Zhang
     * @Date: 2020/11/28
    */
    public static String getPropertiesByKey(String key) {
        StringBuilder stringBuilder = new StringBuilder();
        if(StringUtils.isEmpty(key)) {
            return "";
        }
        StandardServletEnvironment bean = BeanUtil.getBean(StandardServletEnvironment.class);
        String[] keySplit = key.split(",");
        for(String str : keySplit) {
            String tempStr = bean.getProperty(str);
            if(!StringUtils.isEmpty(str)) {
                stringBuilder.append("KEY:").append(str).append(",VALUE:").append(tempStr).append("\n");
            }
        }
        return stringBuilder.toString();
    }

}
