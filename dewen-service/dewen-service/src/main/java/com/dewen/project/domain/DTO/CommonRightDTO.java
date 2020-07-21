package com.dewen.project.domain.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommonRightDTO {

	/**
	 * 编号
	 */
	private Integer id;


    /**
      * 父编号
     */
    private Integer parentId;


    /**
      * 权限名称
     */
    private String rightName;

    private String resourceType;


    /**
      * 系统类型
     */
    private String sysType;


    /**
      * 权限编码
     */
    private String rightCode;


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


    /**
      * 权限路径
     */
    private String rightUrl;


    private String icon;

    private String component;

    private Integer sortNum;

    private List<CommonRightDTO> children;

    private List<CommonRightDTO> apis;

    private String rightType;

    private Boolean checked;


}