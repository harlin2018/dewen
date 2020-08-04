package com.dewen.project.repository;

import com.dewen.project.domain.CompanyInfo;
import com.dewen.project.domain.CompanyRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    List<CompanyRecord> findAllByCompanyIdAndRecordType(CompanyInfo companyId, String value);

    @Query(value = "select * from company_record where record_type =:recordType group by content", nativeQuery = true)
    List<CompanyRecord> findAllByRecordTypeGroupByContent(String recordType);

    @Query(value = "select * from company_record where record_type =1 and status = 0 and complete_date < NOW() and id not in (select record_id from customer_notice)", nativeQuery = true)
    List<CompanyRecord> findRecord();
}