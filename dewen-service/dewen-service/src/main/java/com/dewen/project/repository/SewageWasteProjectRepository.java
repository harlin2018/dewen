package com.dewen.project.repository;

import com.dewen.project.domain.SewageWasteProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

/**
 * 排污种类检测项目
 * <p>Description: SewageWasteProject Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Repository
public interface SewageWasteProjectRepository extends JpaRepository<SewageWasteProject, Integer>, JpaSpecificationExecutor<SewageWasteProject>{


}