package com.dewen.project.domain;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * common_model_file
 * <p>Description: CommonModelFile entity </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>

 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-17
 */
@Entity
@Data
@Table(name="common_model_file")
public class CommonModelFile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Integer id;

    /**
     * company_record_id
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "company_record_id",referencedColumnName = "ID")
    private CompanyRecord companyRecord;


    /**
     * company_file_id
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "company_file_id",referencedColumnName = "ID")
    private CommonFileSystem companyFileId;

    public CommonModelFile() {
    }

}