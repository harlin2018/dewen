package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * company_waste
 * <p>Description: CompanyWaste entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Entity
@Data
@Table(name="company_waste")
public class CompanyWaste implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 名称与类别
     */
    @Column(name = "name_type")
    private String nameType;

    /**
     * 年生成量
     */
    @Column(name = "year_pro")
    private String yearPro;

    /**
     * 处理方式
     */
    @Column(name = "process_methods")
    private String processMethods;

    /**
     * 处理方式说明
     */
    @Column(name = "process_methods_remark")
    private String processMethodsRemark;


    /**
     * 年处理量
     */
    @Column(name = "year_process")
    private String yearProcess;

    /**
     * 公司Id
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "company_id",referencedColumnName = "ID")
    private CompanyInfo companyId;

    public CompanyWaste() {
    }

}