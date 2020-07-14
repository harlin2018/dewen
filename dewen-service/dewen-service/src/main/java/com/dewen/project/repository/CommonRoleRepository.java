package com.dewen.project.repository;

import com.dewen.project.domain.CommonRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

/**
 * common_role
 * <p>Description: CommonRole Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Repository
public interface CommonRoleRepository extends JpaRepository<CommonRole, Integer>, JpaSpecificationExecutor<CommonRole>{


}