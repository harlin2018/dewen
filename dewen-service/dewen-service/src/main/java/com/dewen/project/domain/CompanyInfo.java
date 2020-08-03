package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 重点工业企业基本情况表
 * <p>Description: CompanyInfo entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Entity
@Data
@Table(name="company_info")
public class CompanyInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 单位名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 单位地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 区域
     */
    @Column(name = "store_area")
    private String storeArea;

    /**
     * 法定代表人
     */
    @Column(name = "legal_representative")
    private String legalRepresentative;

    /**
     * 组织机构代码
     */
    @Column(name = "organization_code")
    private String organizationCode;

    /**
     * 环保负责人
     */
    @Column(name = "environmental_protection_officer")
    private String environmentalProtectionOfficer;

    /**
     * 联系电话
     */
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 行业类别
     */
    @Column(name = "industry_category")
    private String industryCategory;

    /**
     * 行业代码
     */
    @Column(name = "industry_code")
    private String industryCode;

    /**
     * 行业主管部门
     */
    @Column(name = "industry_dept")
    private String industryDept;

    /**
     * 建成时间
     */
    @Column(name = "completion_date")
    private Date completionDate;

    /**
     * 固定资产（万元）
     */
    @Column(name = "fixed_assets")
    private String fixedAssets;

    /**
     * 环保设施固定资产（万元）
     */
    @Column(name = "envir_prot_fixed_assets")
    private String envirProtFixedAssets;

    /**
     * 企业规模：1大型/2中型/3小型；备注：用枚举
     */
    @Column(name = "enterprise_size")
    private Integer enterpriseSize;

    /**
     * 污染源管理级别：1国控/2市控/3区控/4一般；备注：用枚举
     */
    @Column(name = "pollution_source_management_level")
    private Integer pollutionSourceManagementLevel;

    /**
     * 排污种类：1废水/废气/噪声/固废/其它
     */
    @Column(name = "sewage_type")
    private Integer sewageType;

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
     * 一般工业固体废物
     */
    @Column(name = "general_industrial_solid_waste")
    private String generalIndustrialSolidWaste;

    /**
     * 年产生量
     */
    @Column(name = "year_production")
    private String yearProduction;

    /**
     * 年处理量
     */
    @Column(name = "year_process")
    private String yearProcess;

    /**
     * 处置方式
     */
    @Column(name = "disposal_way")
    private String disposalWay;

    /**
     * 主要声源名称
     */
    @Column(name = "main_sound_source_name")
    private String mainSoundSourceName;

    /**
     * 污染防治的主要措施
     */
    @Column(name = "mmppc")
    private String mmppc;

    /**
     * 雨污分流
     */
    @Column(name = "sewerage_rain")
    private String sewerageRain;

    /**
     * 雨污分流备注
     */
    @Column(name = "sewerage_rain_remark")
    private String sewerageRainRemark;

    /**
     * 排向
     */
    @Column(name = "row_to")
    private String rowTo;

    /**
     * 排向说明
     */
    @Column(name = "row_to_remark")
    private String rowToRemark;

    /**
     * 企业预处理
     */
    @Column(name = "enterprise_pretreatment")
    private String enterprisePretreatment;

    /**
     * 企业预处理
     */
    @Column(name = "enterprise_pretreatment_remark")
    private String enterprisePretreatmentRemark;

    /**
     * 产生量（月平均）
     */
    @Column(name = "output")
    private String output;

    /**
     * 污水排向
     */
    @Column(name = "the_sewage_to")
    private String theSewageTo;

    /**
     * 污水排向说明
     */
    @Column(name = "the_sewage_to_remark")
    private String theSewageToRemark;

    /**
     * 生活污水化粪池
     */
    @Column(name = "stfds")
    private String stfds;

    /**
     * 生活产生量（月平均）
     */
    @Column(name = "life_produced")
    private String lifeProduced;

    /**
     * 生活排向
     */
    @Column(name = "life_line_to")
    private String lifeLineTo;

    /**
     * 生活排向说明
     */
    @Column(name = "life_line_to_remark")
    private String lifeLineToRemark;

    /**
     * 环保应急预案情况
     */
    @Column(name = "environmental_protection_plan")
    private String environmentalProtectionPlan;

    /**
     * 环保应急预案情况
     */
    @Column(name = "environmental_protection_plan_remark")
    private String environmentalProtectionPlanRemark;

    /**
     * 排污许可证
     */
    @Column(name = "emission_permit")
    private String emissionPermit;

    /**
     * 排污许可证
     */
    @Column(name = "emission_permit_remark")
    private String emissionPermitRemark;

    /**
     * 环评工艺
     */
    @Column(name = "eia_process")
    private String eiaProcess;

    /**
     * 环评工艺
     */
    @Column(name = "eia_process_remark")
    private String eiaProcessRemark;

    /**
     * 新环评
     */
    @Column(name = "new_eia")
    private String newEia;

    /**
     * 新环评
     */
    @Column(name = "new_eia_remark")
    private String newEiaRemark;

    /**
     * 环保应急预案情况
     */
    @Column(name = "epep")
    private String epep;

    /**
     * 环保应急预案情况备注
     */
    @Column(name = "epep_remark")
    private String epepRemark;

    /**
     * 监督性检测企业
     */
    @Column(name = "supervisory_inspection_enterprise")
    private String supervisoryInspectionEnterprise;

    /**
     * 监督性检测企业
     */
    @Column(name = "supervisory_inspection_enterprise_remark")
    private String supervisoryInspectionEnterpriseRemark;

    /**
     * 废水监督性检测周期
     */
    @Column(name = "sicfwwo")
    private String sicfwwo;

    /**
     * 废气监督性检测周期
     */
    @Column(name = "sicfwwt")
    private String sicfwwt;

    /**
     * 土壤监督性检测周期
     */
    @Column(name = "sicfwws")
    private String sicfwws;

    /**
     * 企业在职人数
     */
    @Column(name = "number_employees")
    private String numberEmployees;

    /**
     * 环评
     */
    @Column(name = "eia")
    private String eia;

    /**
     * 环评批复
     */
    @Column(name = "official_reply")
    private String officialReply;

    /**
    * 环评批复文件Id
    */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "official_reply_file_id",referencedColumnName = "ID")
    private CommonFileSystem officialReplyFileId;

    /**
     * 生产工艺描述
     */
    @Column(name = "breaks")
    private String breaks;

    /**
     * 环评时间
     */
    @Column(name = "official_time")
    private Date officialTime;

    /**
     * 月进水量（平均）
     */
    @Column(name = "mouth_inflow")
    private String mouthInflow;

    /**
     * 污水产生量
     */
    @Column(name = "swage_generation")
    private String swageGeneration;
    /**
     * 消耗量
     */
    @Column(name = "consumption")
    private String consumption;
    /**
     * 水平衡
     */
    @Column(name = "water_balance")
    private String waterBalance;


    /**
     * 记录状态 1 保存/2暂存
     */
    @Column(name = "record_status")
    private String recordStatus;

    /**
     * 主要生产产品表
     */
    @Transient
    private List<CompanyProduct> companyProductList;

    /**
     * 排污种类：废水
     */
    @Transient
    private List<CompanySewageWaste> wasteWaterList;

    /**
     * 排污种类：废气
     */
    @Transient
    private List<CompanySewageWaste> wasteGasList;

    /**
     * 监测项目（废水）
     */
    @Transient
    private List<CompanyProject> wasteWaterMonitorList;

    /**
     * 监测项目（废气）
     */
    @Transient
    private List<CompanyProject> wasteGasMonitorList;

    /**
     * 危废
     */
    @Transient
    private List<CompanyWaste> companyWasteList;

    /**
     * 巡查执法记录
     */
    @Transient
    private List<CompanyRecord> inspectRecordList;

    /**
     * 行政执法记录
     */
    @Transient
    private List<CompanyRecord> adminRecordList;

    @Transient
    private String basePath;

    public CompanyInfo() {
    }

}