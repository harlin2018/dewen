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
    @Column(name = "file_url")
    private CommonFileSystem fileUrl;

    /**
     * 记录类型:1巡查执法记录/2行政处罚记录
     */
    @Column(name = "record_type")
    private String recordType;

    public CompanyRecord() {
    }

}