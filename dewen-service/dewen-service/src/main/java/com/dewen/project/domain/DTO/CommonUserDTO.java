package com.dewen.project.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:</p>
 * <p>Description: CommonUserDTO Domain </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 * <p>用户表</p>
 *
 * @author Welge
 * @version 1.0
 * @date 2019-06-13
 */

@NoArgsConstructor
@Data
public class CommonUserDTO {

    /**
     * 编号
     */
    private Integer id;


    /**
     * 用户名
     */
    private String userName;


    /**
     * 密码
     */
    private String hashPassword;


    /**
     * 用户密钥
     */
    private String secret;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 登陆名称
     */
    private String loginName;


    /**
     * 登陆时间
     */
    private Date loginTime;


    /**
     * 最后登陆
     */
    private Date lastLogintime;


    /**
     * 次数
     */
    private Integer count;


    /**
     *
     */
    private String mobile;


    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 微信openid
     */
    private String weChatOpenid;

    /**
     * 微信号核对状态 1:已核对 2:未核对
     */
    private Integer weChatStatus;

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

    private String headImg;

    private String combinationName;

    @JsonProperty("roles")
    private List<CommonRoleDTO> commonRoleDTOList;
    @JsonProperty("rights")
    public List<CommonRightDTO> commonRightDTOList;

    public Integer weChatUser;

    public String belongCompany;

    public String department;

    public String roleName;

    public CommonUserDTO(String userName, String loginName, String roleName) {
        this.userName = userName;
        this.loginName = loginName;
        this.roleName = roleName;
    }

    public CommonUserDTO(String userName, String loginName, String mobile, String email, String weChatOpenid) {
        this.userName = userName;
        this.email = email;
        this.loginName = loginName;
        this.mobile = mobile;
        this.weChatOpenid = weChatOpenid;
    }

    @Override
    public String toString() {
        return "CommonUserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", secret='" + secret + '\'' +
                ", email='" + email + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginTime=" + loginTime +
                ", lastLogintime=" + lastLogintime +
                ", count=" + count +
                ", mobile='" + mobile + '\'' +
                ", nickName='" + nickName + '\'' +
                ", weChatOpenid='" + weChatOpenid + '\'' +
                ", weChatStatus=" + weChatStatus +
                ", employeeId=" + employeeId +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                ", createStaff=" + createStaff +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", modifyStaff=" + modifyStaff +
                ", headImg='" + headImg + '\'' +
                ", commonRoleDTOList=" + commonRoleDTOList +
                ", commonRightDTOList=" + commonRightDTOList +
                ", weChatUser=" + weChatUser +
                ", belongCompany='" + belongCompany + '\'' +
                '}';
    }
}