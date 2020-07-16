package com.dewen.project.repository;

import com.dewen.project.domain.CompanyInfo;
import com.dewen.project.domain.CompanyProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 主要产品生产情况表
 * <p>Description: CompanyProject Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Repository
public interface CompanyProjectRepository extends JpaRepository<CompanyProject, Integer>, JpaSpecificationExecutor<CompanyProject>{


    void deleteByCompanyId(CompanyInfo companyInfo);

    List<CompanyProject> findAllByCompanyIdAndWasteType(CompanyInfo companyId, String value);

    @Query(value = "select * from company_project where waste_type =:wasteType group by monitor_project", nativeQuery = true)
    List<CompanyProject> findAllByWasteTypeGroupByMonitorProject(String wasteType);
}