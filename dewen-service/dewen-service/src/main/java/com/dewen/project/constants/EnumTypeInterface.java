package com.dewen.project.constants;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>Title:枚举类型接口</p>
 * <p>Description:@code Dict.class 指定为枚举项所需要实现的接口 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 * @author Welge
 * @version 1.0
 * @date 2019/7/23
 */
public interface EnumTypeInterface {
    /**
     * @return 中文枚举项
     */
    default List<EnumType> getDict(){
        EnumTypeInterface[] enumConstants = this.getClass().getEnumConstants();
        return Arrays.stream(enumConstants).map(s->new EnumType(s.getCnLabel(), s.getValue())).collect(Collectors.toList());
    }

    /**
     * @return 英文枚举项
     */
    default List<EnumType> getEnDict(){
        EnumTypeInterface[] enumConstants = this.getClass().getEnumConstants();
        return Arrays.stream(enumConstants).map(s->new EnumType(s.getEnLabel(), s.getValue())).collect(Collectors.toList());
    }

    static String toCnLabel(Class<? extends EnumTypeInterface> clazz, String value){
        EnumTypeInterface[] enumConstants = clazz.getEnumConstants();
        return Arrays.stream(enumConstants).filter(s-> Objects.equals(s.getValue(), value)).map(s->s.getCnLabel()).findFirst().orElse(null);
    }

    static String toEnLabel(Class<? extends EnumTypeInterface> clazz, String value){
        EnumTypeInterface[] enumConstants = clazz.getEnumConstants();
        return Arrays.stream(enumConstants).filter(s-> Objects.equals(s.getValue(), value)).map(s->s.getEnLabel()).findFirst().orElse(null);
    }


    /**
     * @return 英文名
     */
    String getEnLabel();
    /**
     * @return 中文名
     */
    String getCnLabel();
    /**
     * @return 值
     */
    String getValue();
    /**
     * @return 整形值
     */
    default Integer intValue(){
        return Integer.parseInt(getValue());
    }

}
