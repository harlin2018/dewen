package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * common_user_role_relationship
 * <p>Description: CommonUserRoleRelationship entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Entity
@Data
@Table(name="common_user_role_relationship")
public class CommonUserRoleRelationship implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * fk_common_user
     */
    @Column(name = "com_user_id")
    private Integer comUserId;

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

    public CommonUserRoleRelationship() {
    }

}