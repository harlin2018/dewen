package com.dewen.project.domain;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * 监测项目表
 * <p>Description: CompanyProject entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Entity
@Data
@Table(name="company_project")
public class CompanyProject implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 监测项目
     */
    @Column(name = "monitor_project")
    private String monitorProject;

    /**
     * 监测指标
     */
    @Column(name = "monitor_index")
    private String monitorIndex;

    /**
     * 检测指标
     */
    @Column(name = "test_item")
    private String testItem;

    /**
     * 检测时间
     */
    @Column(name = "test_time")
    private Date testTime;

    /**
     * 监测文件Id
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "monitor_file_id",referencedColumnName = "ID")
    private CommonFileSystem monitorFileId;

    /**
     * 公司Id
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "company_id",referencedColumnName = "ID")
    private CompanyInfo companyId;

    /**
     * 监测类型（1废水/2废气）
     */
    @Column(name = "waste_type")
    private String wasteType;

    public CompanyProject() {
    }

}