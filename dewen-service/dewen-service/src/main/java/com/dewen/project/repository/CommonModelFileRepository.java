package com.dewen.project.repository;

import com.dewen.project.domain.CommonFileSystem;
import com.dewen.project.domain.CommonModelFile;

import com.dewen.project.domain.CompanyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * common_model_file
 * <p>Description: CommonModelFile Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-17
 */
@Repository
public interface CommonModelFileRepository extends JpaRepository<CommonModelFile, Integer>, JpaSpecificationExecutor<CommonModelFile>{


    List<CommonModelFile> findAllByCompanyRecord(CompanyRecord companyRecord);
}