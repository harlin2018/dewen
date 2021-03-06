package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 记录类型表
 * <p>Description: CompanyRecord entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Entity
@Data
@Table(name="company_record")
public class CompanyRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 公司Id
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "company_id",referencedColumnName = "ID")
    private CompanyInfo companyId;


    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * file_url
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "file_url",referencedColumnName = "ID")
    private CommonFileSystem fileUrl;

    /**
     * 记录类型:1巡查执法记录/2行政处罚记录
     */
    @Column(name = "record_type")
    private String recordType;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    @Transient
    private List<CommonFileSystem> fileIdList;

    /**
     * 默认状态为0
     */
    @Column(name = "status")
    private String status ="0";

    /**
     * 完成时间
     */
    @Column(name = "complete_date")
    private Date completeDate;

    /**
     * 完成 内容
     */
    @Column(name = "complete_content")
    private String completeContent;

    /**
     * 完成附件
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "complete_file_id",referencedColumnName = "ID")
    private CommonFileSystem completeFileId;


    public CompanyRecord() {
    }

}