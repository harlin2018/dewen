package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 排污种类检测项目
 * <p>Description: SewageWasteProject entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Entity
@Data
@Table(name="sewage_waste_project")
public class SewageWasteProject implements Serializable {
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
    private String testTime;

    /**
     * 公司排污种类废气id
     */
    @Column(name = "sewage_waste_id")
    private Integer sewageWasteId;

    /**
     * waste_type
     */
    @Column(name = "waste_type")
    private String wasteType;

    public SewageWasteProject() {
    }

}