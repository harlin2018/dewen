package com.dewen.project.domain.DTO;

import lombok.Data;

/**
 * <p>Title:</p>
 * <p>Description:CommonRight  Controller </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 * @author Welge
 * @version 1.0
 * @date 2019/6/26
 */
@Data
public class RightListByRoleRequest extends BaseRequest {
    private Integer roleId;
}
