package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * common_role_right_relationship
 * <p>Description: CommonRoleRightRelationship entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-18
 */
@Entity
@Data
@Table(name="common_role_right_relationship")
public class CommonRoleRightRelationship implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Integer id;

    /**
     * 权限类型
     */
    @Column(name = "right_type")
    private String rightType;

    /**
     * fk_common_right
     */
    @Column(name = "com_right_id")
    private Integer comRightId;

    /**
     * fk_common_role
     */
    @Column(name = "com_role_id")
    private Integer comRoleId;

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

    public CommonRoleRightRelationship() {
    }

}