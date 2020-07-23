package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
     * 编号
     * default value: null
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 11)
    private Integer id;


    /**
     * 父编号
     * default value: null
     */
    @Column(name = "PARENT_ID", nullable = true, length = 11)
    private Integer parentId;


    /**
     * 角色名称
     * default value: null
     */
    @Length(max=80, message = "{commonRole.roleName.name.length.limit}")
    @NotBlank(message = "角色名称不能为空")
    @Column(name = "ROLE_NAME", nullable = false, length = 80)
    private String roleName;


    /**
     * 角色代号
     * default value: null
     */
    @Column(name = "ROLE_CODE", nullable = true, length = 8)
    @NotBlank(message = "角色编码不能为空")
    private String roleCode;


    /**
     * 角色代号
     * default value: null
     */
    @Column(name = "ORDER_TYPE_ID", nullable = true, length = 8)
    private Integer orderTypeId;


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


    /**
     * one to many -one is CommonRole,many is commonUserRoleRelationship
     * default value: null
     */
    @OneToMany(cascade=CascadeType.DETACH)
    //@OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "COM_ROLE_ID")
    @JsonIgnore
    private List<CommonUserRoleRelationship> commonUserRoleRelationship;

    /**
     * one to many -one is CommonRole,many is commonRoleRightRelationship
     * default value: null
     */
    @OneToMany(cascade=CascadeType.DETACH)
    //@OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "COM_ROLE_ID")
    @JsonIgnore
    private List<CommonRoleRightRelationship> commonRoleRightRelationship;

    public CommonRole() {
    }

}