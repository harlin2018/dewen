package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * customer_notice
 * <p>Description: CustomerNotice entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-08-04
 */
@Entity
@Data
@Table(name="customer_notice")
public class CustomerNotice implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 发送人员
     */
    @Column(name = "send_user")
    private String sendUser;

    /**
     * 接收人员
     */
    @Column(name = "receiver_user")
    private String receiverUser;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "modify_date")
    private Date modifyDate;

    /**
     * 发送内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * enabled
     */
    @Column(name = "enabled")
    private Integer enabled;

//    @ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name = "record_id",referencedColumnName = "ID")
    @Column(name = "record_id")
    private Integer companyRecord;

    /**
     * 是否已读 1为已读，0为未读
     */
    @Column(name = "is_read")
    private Integer isRead;


    public CustomerNotice() {
    }

}