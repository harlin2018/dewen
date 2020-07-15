package com.dewen.project.repository;

import com.dewen.project.domain.CompanyInfo;
import com.dewen.project.domain.CompanySewageWaste;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 排污种类
 * <p>Description: CompanySewageWaste Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Repository
public interface CompanySewageWasteRepository extends JpaRepository<CompanySewageWaste, Integer>, JpaSpecificationExecutor<CompanySewageWaste>{


    void deleteByCompanyId(CompanyInfo companyInfo);

    List<CompanySewageWaste> findByCompanyId(CompanyInfo companyId);

    List<CompanySewageWaste> findByCompanyIdAndWasteType(CompanyInfo companyId, String value);

    List<CompanySewageWaste> findAllByCompanyIdAndWasteType(CompanyInfo companyId, String value);
}