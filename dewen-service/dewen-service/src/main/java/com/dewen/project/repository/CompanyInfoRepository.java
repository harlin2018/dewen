package com.dewen.project.repository;

import com.dewen.project.domain.CompanyInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 重点工业企业基本情况表
 * <p>Description: CompanyInfo Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Integer>, JpaSpecificationExecutor<CompanyInfo>{

    @Query(value = ":sql", nativeQuery = true)
    List<Map<String, Object>> selectList(@Param("sql") String sql);

}