package com.dewen.project.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.util.ReflectionUtils;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NullAwareBeanUtilsBean extends BeanUtilsBean {


    private static Map<String, Class> clz;

    private static Map initClz(){
        if(clz == null){
            clz = new HashMap<String,Class>();
            try {
                Class cardClass = ClassUtils.getClass("com.dhq.oneoss.cmdb.pde.domain.PdeCard");
                Class portClass = ClassUtils.getClass("com.dhq.oneoss.cmdb.pde.domain.PdePort");
                Class trunkClass = ClassUtils.getClass("com.dhq.oneoss.cmdb.pde.domain.PdeTrunk");
                Class osCardClass = ClassUtils.getClass("com.dhq.oneoss.cmdb.bsy.domain.BsySwOsCard");
                Class osChassisClass = ClassUtils.getClass("com.dhq.oneoss.cmdb.bsy.domain.BsySwOsChassis");
                Class otherCardClass = ClassUtils.getClass("com.dhq.oneoss.cmdb.bsy.domain.BsySwOtherCard");
                Class otherChassisClass = ClassUtils.getClass("com.dhq.oneoss.cmdb.bsy.domain.BsySwOtherChassis");
                Class otherCloudDesktop = ClassUtils.getClass("com.dhq.oneoss.cmdb.bsy.domain.BsySwOtherCloudDesktop");
                Class osCardClassDto = ClassUtils.getClass("com.dhq.oneoss.cmdb.bsy.domain.dto.BsySwOsCardDto");
                Class osChassisClassDto = ClassUtils.getClass("com.dhq.oneoss.cmdb.bsy.domain.dto.BsySwOsChassisDto");
                clz.put("cardClass",cardClass);
                clz.put("portClass",portClass);
                clz.put("trunkClass",trunkClass);
                clz.put("osCardClass",osCardClass);
                clz.put("osChassisClass",osChassisClass);
                clz.put("otherCardClass",otherCardClass);
                clz.put("otherChassisClass",otherChassisClass);
                clz.put("otherCloudDesktop",otherCloudDesktop);
                clz.put("osCardClassDto", osCardClassDto);
                clz.put("osChassisClassDto", osChassisClassDto);
            }catch (ClassNotFoundException e){
            }
        }
        return clz;
    }

    /**
     * 复制属性不包括一对多的List
     * @param desc
     * @param src
     * @param <T>
     * @return
     */
    public static <T extends Object> T copyExculdeList(T desc, T src) {
        if(desc == null || src == null || desc.getClass() != src.getClass()){
            return null;
        }
        List<Field> manyFields = FieldUtils.getFieldsListWithAnnotation(desc.getClass(), OneToMany.class);
        List<String> ignoreProperties = manyFields.stream().map(field -> field.getName()).collect(Collectors.toList());
        if(ignoreProperties.isEmpty()){
            return src;
        }
        BeanUtils.copyProperties(src, desc, ignoreProperties.toArray(new String[ignoreProperties.size()]));
        return desc;
    }

    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value==null){
            return;
        }
        super.copyProperty(dest, name, value);
    }

    public static void mergeNotNullProperties(Object desc,Object orig){
        try {
            new NullAwareBeanUtilsBean().copyProperties(desc,orig);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 移除第二层的jpa关联
     * @param obj
     */
    public static void removeContentRelations(Object obj) {
        if(obj instanceof Page){
            Page page = (Page) obj;
            // 属性是list类型的，去除list里的 关联属性(关联对象或者list)
            page.getContent().forEach( t -> removeRelations(t,initClz()));
        }

    }
    public static void removeRelations(Object item) {
        removeRelations(item,initClz());
    }


    public static void removeRelations(Object item, Map<String,Class> clz) {
        if(clz == null){
            removeRelations(item,1,2);
        }else{
            if(item !=null && item.getClass().equals(clz.get("cardClass"))){
                removeRelations(item,1,3);
            }else if(item !=null && item.getClass().equals(clz.get("portClass"))){
                removeRelations(item,1,4);
            }else if(item !=null && item.getClass().equals(clz.get("trunkClass"))){
                removeRelations(item,1,6);
            }else if(item !=null && item.getClass().equals(clz.get("osCardClass"))){
                removeRelations(item,1,5);
            }else if(item !=null && item.getClass().equals(clz.get("osChassisClass"))){
                removeRelations(item,1,3);
            }else if(item !=null && item.getClass().equals(clz.get("otherCardClass"))){
                removeRelations(item,1,6);
            }else if(item !=null && item.getClass().equals(clz.get("otherChassisClass"))){
                removeRelations(item,1,4);
            }else if(item !=null && item.getClass().equals(clz.get("otherCloudDesktop"))){
                removeRelations(item,1,3);
            }else if(item !=null && item.getClass().equals(clz.get("osCardClassDto"))) {
                removeRelations(item, 1, 5);
            }else if(item !=null && item.getClass().equals(clz.get("osChassisClassDto"))) {
                removeRelations(item, 1, 3);
            }else{
                removeRelations(item,1,2);
            }
        }

    }
    public static void removeRelations(Object item,Integer currentLevel, Integer removeLevel) {
        if(item == null){
            return;
        }
        List<Field> fields = findRelationFields(item.getClass());
        fields.forEach( field ->{
            ReflectionUtils.makeAccessible(field);
            Object obj = ReflectionUtils.getField(field,item);
            if(obj == null){
                return;
            }
            if(obj instanceof List){
                ReflectionUtils.setField(field, item, new ArrayList());
            }else if(StringUtils.endsWithIgnoreCase(obj.getClass().getPackage().getName(), ".domain")){
                if(removeLevel.equals(currentLevel)){
                    ReflectionUtils.makeAccessible(field);
                    ReflectionUtils.setField(field, item,null);
                }else{
                    removeRelations(obj,currentLevel+1,removeLevel);
                }
            }
        });

    }

    private static void removeFkProperties(Object o) {
        if(o == null){
            return;
        }
        List<Field> allFields = findRelationFields(o.getClass());
        allFields.forEach( field -> {
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, o,null);
        });
    }

    private static List<Field> findRelationFields(Class clazz){
        List<Field> manyFields = FieldUtils.getFieldsListWithAnnotation(clazz, OneToMany.class);
        List<Field> oneFields = FieldUtils.getFieldsListWithAnnotation(clazz, ManyToOne.class);
        List<Field> allFields = new ArrayList<Field>();
        allFields.addAll(manyFields);
        allFields.addAll(oneFields);
        return  allFields;
    }

}
