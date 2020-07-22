package com.dewen.project.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserAssignRoleRequest extends BaseRequest {
    private Integer userId;
    private List<Integer> roleIds;
}
