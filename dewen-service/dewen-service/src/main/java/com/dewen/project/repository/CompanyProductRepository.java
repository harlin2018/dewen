package com.dewen.project.repository;

import com.dewen.project.domain.CompanyInfo;
import com.dewen.project.domain.CompanyProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

/**
 * 主要产品生产情况表
 * <p>Description: CompanyProduct Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Repository
public interface CompanyProductRepository extends JpaRepository<CompanyProduct, Integer>, JpaSpecificationExecutor<CompanyProduct>{


    void deleteByCompanyId(CompanyInfo companyInfo);
}