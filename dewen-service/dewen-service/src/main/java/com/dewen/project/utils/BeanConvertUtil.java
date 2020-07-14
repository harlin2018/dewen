package com.dewen.project.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 对象转换类
 */
public class BeanConvertUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转Map (驼峰转下划线大写)
     * @param obj
     * @return
     */
    public static Map<String,Object> beanConvertToMap(Object obj){
        Map<String, Object> resoult = new HashMap<String,Object>();
        if(ObjectUtils.isEmpty(obj)){
            return resoult;
        }
        //参数转换
        try {
            //驼峰式转换
            objectMapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);
            String userTemp = objectMapper.writeValueAsString(obj);
            Map tempMap = objectMapper.readValue(userTemp,HashMap.class);
            // key 转大写
            Set<String> keys = tempMap.keySet();
            for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
                String key =  iterator.next();
                Object objTemp = tempMap.get(key);
                if(!ObjectUtils.isEmpty(objTemp)){
                    resoult.put(key.toUpperCase(),objTemp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resoult;
    }

    /**
     * bean 转查询对象
     * @return
     */
    public static QueryWrapper beanConvertToQueryWrapper(Object obj){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(ObjectUtils.isEmpty(obj)){
            return queryWrapper;
        }
        //参数转换
        try {
            //驼峰式转换
            objectMapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);
            String userTemp = objectMapper.writeValueAsString(obj);
            //转 Map
            Map<String, Object> mapTemp = objectMapper.readValue(userTemp,HashMap.class);
            //转 QueryWrapper
            Iterator iterator = mapTemp.entrySet().iterator();
            for (Iterator iterator1 = iterator; iterator1.hasNext(); ) {
                Map.Entry eTemp =  (Map.Entry)iterator1.next();
                if(!StringUtils.isEmpty(eTemp.getValue())){
                    queryWrapper.eq(eTemp.getKey().toString().toUpperCase(),eTemp.getValue().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return queryWrapper;
    }
}
