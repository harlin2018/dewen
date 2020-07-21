package com.dewen.project.utils;

import com.dewen.project.constants.ErrorCode;
import com.dewen.project.exception.CommonException;
import com.dewen.project.exception.SysException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * <p>Title: Bean工具类</p>
 * <p>Description: Bean工具类</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 */
public class BeanExtraUtils extends BeanUtils {


    public static String[] getNotNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue != null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesNotCover(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNotNullPropertyNames(target));
    }

    public static boolean copyPropertiesIgnoreNull(Object source, Object target) {
        try {
            String[] nullPropertyNames = getNullPropertyNames(source);

            if (nullPropertyNames.length < Introspector.getBeanInfo(source.getClass()).getPropertyDescriptors().length) {
                BeanUtils.copyProperties(source, target, nullPropertyNames);
                return true;
            }
            return false;
        } catch (IntrospectionException e) {
            throw new SysException(e);
        }
    }
    public static <T> List<T> transferToDTO(List source, Class<T> clazz){
        T target;
        List arrayList = new ArrayList();
        try{
            for (Object o : source) {
                target = clazz.newInstance();
                copyProperties(o, target);
                arrayList.add(target);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            throw new CommonException(ErrorCode.RUNTIME_EXCEPTION, e);
        }
        return arrayList;
    }
}
