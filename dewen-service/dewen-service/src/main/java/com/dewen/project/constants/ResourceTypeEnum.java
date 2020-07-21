package com.dewen.project.constants;

import com.dewen.project.anontation.Dict;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Title:</p>
 * <p>Description:CommonRight  Controller </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 * @author Welge
 * @version 1.0
 * @date 2019/7/2
 */
@Getter
@AllArgsConstructor
@Dict("resourceType")
public enum ResourceTypeEnum implements EnumTypeInterface {
    Menu("Menu", "菜单", "menu"),
    Button("Button", "按键","button"),
    Api("Api", "接口", "api");
    String enLabel;
    String cnLabel;
    String value;
}
