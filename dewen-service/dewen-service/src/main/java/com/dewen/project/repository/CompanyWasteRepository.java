package com.dewen.project.repository;

import com.dewen.project.domain.CompanyInfo;
import com.dewen.project.domain.CompanyWaste;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * company_waste
 * <p>Description: CompanyWaste Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Repository
public interface CompanyWasteRepository extends JpaRepository<CompanyWaste, Integer>, JpaSpecificationExecutor<CompanyWaste>{


    void deleteByCompanyId(CompanyInfo companyInfo);

    List<CompanyWaste> findAllByCompanyId(CompanyInfo companyId);
}