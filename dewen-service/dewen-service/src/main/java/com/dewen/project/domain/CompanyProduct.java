package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 主要产品生产情况表
 * <p>Description: CompanyProduct entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Entity
@Data
@Table(name="company_product")
public class CompanyProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 产品名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 产品产量（计量单位）
     */
    @Column(name = "unit")
    private String unit;

    /**
     * 主要原辅材料名称
     */
    @Column(name = "main_materials_name")
    private String mainMaterialsName;

    /**
     * 主要污染物
     */
    @Column(name = "major_pollutants")
    private String majorPollutants;

    /**
     * 公司Id
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "company_id",referencedColumnName = "ID")
    private CompanyInfo companyId;

    public CompanyProduct() {
    }

}