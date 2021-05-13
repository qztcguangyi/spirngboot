package com.learning.mvc.controller;
 
 
import cn.hutool.system.SystemUtil;
import com.learning.mvc.util.BeanUtil;
import com.learning.mvc.util.PlatPropUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class JsonController {

    /** 
     * @Description: TOTO 动态加载配置有没有注解 
     * @Param:  
     * @return:  
     * @Author: Gavin Zhang
     * @Date: 2020/11/5 
    */ 
    @Value("${age}")
    public int age;

    /** 
     * @Description: 默认集成了jsckson
     * @Param: [] 
     * @return: java.util.Map<java.lang.String,java.lang.Object> 
     * @Author: Gavin Zhang
     * @Date: 2020/11/5 
    */ 
    @RequestMapping("/json")
    public Map<String,Object> sayHello(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","123");
        map.put("age",age);
        return map;
    }

    /**
     * @Description: 默认集成了jsckson
     * @Param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Gavin Zhang
     * @Date: 2020/11/5
     */
    @PostMapping("/ps")
    public Map<String,Object> ps(){
        Map<String,Object> map = new HashMap<>();

        map.put("age", PlatPropUtil.getDynamicPropertyValue("application.properties","age"));
        map.put("name",PlatPropUtil.getDynamicPropertyValue("application.properties","name"));
        map.put("yml.name",PlatPropUtil.getDynamicPropertyValue("application.yml","logging.config"));


        return map;
    }

    @RequestMapping("/env")
    public Map<String,String> env() {
        PlatPropUtil.printAllProperties();
        return PlatPropUtil.getAllProperties();
    }

    
}