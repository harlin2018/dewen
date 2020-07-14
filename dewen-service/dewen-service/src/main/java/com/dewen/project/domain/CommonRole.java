package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * common_role
 * <p>Description: CommonRole entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Entity
@Data
@Table(name="common_role")
public class CommonRole implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 父编号
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色代号
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * order_type_id
     */
    @Column(name = "order_type_id")
    private Integer orderTypeId;

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

    public CommonRole() {
    }

}