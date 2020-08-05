package com.dewen.project.repository;

import com.dewen.project.domain.CompanyRecord;
import com.dewen.project.domain.CustomerNotice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * customer_notice
 * <p>Description: CustomerNotice Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-08-04
 */
@Repository
public interface CustomerNoticeRepository extends JpaRepository<CustomerNotice, Integer>, JpaSpecificationExecutor<CustomerNotice>{

    @Query(value = "select count(*) from customer_notice where is_read = 0 or is_read is NULL", nativeQuery = true)
    int noReadCount();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update customer_notice set is_read = 1 where is_read = 0 or is_read is NULL", nativeQuery = true)
    void updateIsRead();

    void deleteByCompanyRecord(CompanyRecord companyRecord);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from customer_notice where record_id in (select id from company_record where company_id = :id)", nativeQuery = true)
    void deleteByCompanyId(@Param("id") Integer id);
}
