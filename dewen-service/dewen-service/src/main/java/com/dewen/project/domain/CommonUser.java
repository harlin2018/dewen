package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * common_user
 * <p>Description: CommonUser entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Entity
@Data
@Table(name="common_user")
public class CommonUser implements Serializable {
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
     * 用户名
     * default value: null
     */
    @Length(max=200, message = "{commonUser.userName.name.length.limit}")
    @NotBlank(message = "用户名不能为空")
    @Column(name = "USER_NAME", nullable = false, length = 200)
    private String userName;


    /**
     * 密码
     * default value: null
     */
    @Column(name = "HASH_PASSWORD", nullable = true, length = 256)
    @NotBlank(message = "密码不能为空")
    private String hashPassword;


    /**
     * 邮箱
     * default value: null
     */
    @Column(name = "EMAIL", nullable = true, length = 200)
    private String email;


    /**
     * 登陆名称
     * default value: null
     */
    @Length(max=100, message = "{commonUser.loginName.name.length.limit}")
    @NotBlank(message = "登陆名称不能为空")
    @Column(name = "LOGIN_NAME", nullable = false, length = 100)
    private String loginName;


    /**
     * 登陆时间
     * default value: null
     */
    @Column(name = "LOGIN_TIME", nullable = true, length = 19)
    private Date loginTime;


    /**
     * 最后登陆
     * default value: null
     */
    @Column(name = "LAST_LOGINTIME", nullable = true, length = 19)
    private Date lastLogintime;


    /**
     * 次数
     * default value: null
     */
    @Column(name = "COUNT", nullable = true, length = 10)
    private Integer count;


    /**
     *
     * default value: null
     */
    @Column(name = "MOBILE", nullable = true, length = 11)
    private String mobile;


    /**
     * 微信昵称
     */
    @Column(name = "NICK_NAME", nullable = true, length = 100)
    private String nickName;

    /**
     * 微信openid
     */
    @Column(name = "WE_CHAT_OPENID", nullable = true, length = 50)
    private String weChatOpenid;

    /**
     * 微信号核对状态 1:已核对 2:未核对
     */
    @Column(name = "WE_CHAT_STATUS", nullable = true, length = 11)
    private Integer weChatStatus;

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
     * 头像url
     * default value: null
     */
    @Column(name = "HEAD_IMG", nullable = true, length = 256)
    private String headImg;

    /**
     * one to many -one is CommonUser,many is commonUserRoleRelationship
     * default value: null
     */
    @OneToMany(cascade=CascadeType.DETACH)
    @JoinColumn(name = "COM_USER_ID")
    private List<CommonUserRoleRelationship> commonUserRoleRelationship;

    /**
     * 微信用户 1:微信端用户 2:非微信端用户
     * default value: null
     */
    @Column(name = "WE_CHAT_USER", nullable = true, length = 1)
    private Integer weChatUser;

    @Column(name = "BELONG_COMPANY", nullable = true, length = 255)
    private String belongCompany;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "EXTERNAL_ID")
    private String externalId;

    /**
     * status（0未通过/1已通过）
     */
    @Column(name = "status")
    private String status;


    public CommonUser() {
    }

}