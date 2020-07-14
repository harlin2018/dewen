package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;

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
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "hash_password")
    private String hashPassword;

    /**
     * 用户密钥
     */
    @Column(name = "secret")
    private String secret;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 登陆名称
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 登陆时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 最后登陆
     */
    @Column(name = "last_logintime")
    private Date lastLogintime;

    /**
     * 次数
     */
    @Column(name = "count")
    private Integer count;

    /**
     * mobile
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 微信号标识
     */
    @Column(name = "we_chat_openid")
    private String weChatOpenid;

    /**
     * 微信号核对状态 1:已核对 2:未核对
     */
    @Column(name = "we_chat_status")
    private Integer weChatStatus;

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
     * 头像url
     */
    @Column(name = "head_img")
    private String headImg;

    /**
     * 微信用户 1:微信端用户 2:非微信端用户
     */
    @Column(name = "we_chat_user")
    private Integer weChatUser;

    /**
     * 所属单位
     */
    @Column(name = "belong_company")
    private String belongCompany;

    /**
     * department
     */
    @Column(name = "department")
    private String department;

    /**
     * idaas，外部id
     */
    @Column(name = "external_id")
    private String externalId;

    public CommonUser() {
    }

}