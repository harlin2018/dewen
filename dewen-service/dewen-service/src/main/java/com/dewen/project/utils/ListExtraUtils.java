package com.dewen.project.utils;

import com.dewen.project.constants.ErrorCode;
import com.dewen.project.exception.CommonException;
import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * <p>Title:List工具类</p>
 * <p>Description:List工具类</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 */
public class ListExtraUtils extends ListUtils {
    public static <S, T> List<T> toList(List<S> list, Class<T> tClass){
        return toList(list, tClass, null);
    }
    public static <S, T> List<T> toList(List<S> list, Class<T> tClass, Function<? super S, ? extends T> converter) {
        try {
            if (list == null) {
                return null;
            }
            List<T> arrayList = new ArrayList();
            for (S source : list) {
                T target;
                if(converter!=null){
                    target = converter.apply(source);
                    BeanExtraUtils.copyPropertiesNotCover(source, target);
                } else {
                     target = tClass.newInstance();
                     BeanExtraUtils.copyProperties(source, target);
                }
                arrayList.add(target);
            }
            return arrayList;
        } catch (InstantiationException e) {
            throw new CommonException(ErrorCode.RUNTIME_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            throw new CommonException(ErrorCode.RUNTIME_EXCEPTION, e);
        }
    }
    public static <T> List<T> toList(List<T> list, Comparator<T> comparable) {
        Set<T> arrayList = new TreeSet(comparable);
        for (T t : list) {
            if (!arrayList.contains(t)) {
                arrayList.add(t);
            }
        }
        return new ArrayList<>(arrayList);
    }
}
