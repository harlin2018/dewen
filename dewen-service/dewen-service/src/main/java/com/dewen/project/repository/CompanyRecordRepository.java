package com.dewen.project.repository;

import com.dewen.project.domain.CompanyInfo;
import com.dewen.project.domain.CompanyRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

/**
 * 记录类型表
 * <p>Description: CompanyRecord Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Repository
public interface CompanyRecordRepository extends JpaRepository<CompanyRecord, Integer>, JpaSpecificationExecutor<CompanyRecord>{


    void deleteByCompanyId(CompanyInfo companyInfo);
}