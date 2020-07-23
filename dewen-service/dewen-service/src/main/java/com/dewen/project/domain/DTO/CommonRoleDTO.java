package com.dewen.project.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class CommonRoleDTO {

	private Integer id;


    /**
      * 父编号
     */
    private Integer parentId;


    /**
      * 角色名称
     */
    private String roleName;


    /**
      * 角色代号
     */
    private String roleCode;

    private Integer orderTypeId;


    /**
      * 职员编号
     */
    private Integer employeeId;


    /**
      * 回收站
     */
    private Integer enabled;


    /**
      * 备注
     */
    private String remark;


    /**
      * 创建职员
     */
    private Integer createStaff;


    /**
      * 创建日期
     */
    private Date createDate;


    /**
      * 修改职员
     */
    private Date modifyDate;


    /**
      * 修改日期
     */
    private Integer modifyStaff;

    private String combinationName;

    @JsonProperty("rights")
    private List<CommonRightDTO> commonRightDTOList;

}