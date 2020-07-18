package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * common_right
 * <p>Description: CommonRight entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-18
 */
@Entity
@Data
@Table(name="common_right")
public class CommonRight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Integer id;

    /**
     * 父编号
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 权限名称
     */
    @Column(name = "right_name")
    private String rightName;

    /**
     * 系统类型
     */
    @Column(name = "sys_type")
    private String sysType;

    /**
     * 权限编码
     */
    @Column(name = "right_code")
    private String rightCode;

    /**
     * 职员编号
     */
    @Column(name = "employee_id")
    private Integer employeeId;

    /**
     * 回收站
     */
    @Column(name = "enabled")
    private Integer enabled;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建职员
     */
    @Column(name = "create_staff")
    private Integer createStaff;

    /**
     * 创建日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改职员
     */
    @Column(name = "modify_date")
    private Date modifyDate;

    /**
     * 修改日期
     */
    @Column(name = "modify_staff")
    private Integer modifyStaff;

    /**
     * 权限路径
     */
    @Column(name = "right_url")
    private String rightUrl;

    /**
     * 路由图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 路由部件
     */
    @Column(name = "component")
    private String component;

    /**
     * 排序
     */
    @Column(name = "sort_num")
    private Integer sortNum;

    /**
     * 资源类型
     */
    @Column(name = "resource_type")
    private String resourceType;

    public CommonRight() {
    }

}