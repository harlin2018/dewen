package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 排污种类
 * <p>Description: CompanySewageWaste entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Entity
@Data
@Table(name="company_sewage_waste")
public class CompanySewageWaste implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 工艺
     */
    @Column(name = "craft")
    private String craft;

    /**
     * 污源物
     */
    @Column(name = "wu_yuan_content")
    private String wuYuanContent;

    /**
     * 环保治理设施
     */
    @Column(name = "environmental_protection_facilities")
    private String environmentalProtectionFacilities;

    /**
     * 公司Id
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "company_id",referencedColumnName = "ID")
    private CompanyInfo companyId;

    /**
     * 排污口
     */
    @Column(name = "drain_outlet")
    private String drainOutlet;

    /**
     * waste_type
     */
    @Column(name = "waste_type")
    private String wasteType;

    public CompanySewageWaste() {
    }

}