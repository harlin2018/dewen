package com.dewen.project.domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:</p>
 * <p>Description: CommonRole Domain </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 * <p>角色</p>
 * @author Welge
 * @version 1.0
 * @date 2019-06-13
 */

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