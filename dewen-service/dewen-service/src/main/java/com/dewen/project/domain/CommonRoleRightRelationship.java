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
    /**
     * 编号
     * default value: null
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 11)
    private Integer id;


    /**
     * 权限类型
     * default value: null
     */
    @Column(name = "RIGHT_TYPE", nullable = true, length = 45)
    private String rightType;


    /**
     * many to one ,many is CommonRoleRightRelationship ,one is CommonRight
     * default value: null
     */
    @ManyToOne(cascade={CascadeType.DETACH})
    @JoinColumn(name = "COM_RIGHT_ID")
    private CommonRight commonRight;


    /**
     * many to one ,many is CommonRoleRightRelationship ,one is CommonRole
     * default value: null
     */
    @ManyToOne(cascade={CascadeType.DETACH})
    @JoinColumn(name = "COM_ROLE_ID")
    private CommonRole commonRole;


    /**
     * 职员编号
     * default value: null
     */
    @Column(name = "EMPLOYEE_ID", nullable = true, length = 10)
    private Integer employeeId;


    /**
     * 回收站
     * default value: null
     */
    @Column(name = "ENABLED", nullable = true, length = 1)
    private Integer enabled;


    /**
     * 备注
     * default value: null
     */
    @Column(name = "REMARK", nullable = true, length = 200)
    private String remark;


    /**
     * 创建职员
     * default value: null
     */
    @Column(name = "CREATE_STAFF", nullable = true, length = 10)
    private Integer createStaff;


    /**
     * 创建日期
     * default value: null
     */
    @Column(name = "CREATE_DATE", nullable = true, length = 19)
    private Date createDate;


    /**
     * 修改职员
     * default value: null
     */
    @Column(name = "MODIFY_DATE", nullable = true, length = 19)
    private Date modifyDate;


    /**
     * 修改日期
     * default value: null
     */
    @Column(name = "MODIFY_STAFF", nullable = true, length = 10)
    private Integer modifyStaff;

    public CommonRoleRightRelationship() {
    }

}