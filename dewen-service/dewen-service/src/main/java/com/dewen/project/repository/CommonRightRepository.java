package com.dewen.project.repository;

import com.dewen.project.domain.CommonRight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

/**
 * common_right
 * <p>Description: CommonRight Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-18
 */
@Repository
public interface CommonRightRepository extends JpaRepository<CommonRight, Integer>, JpaSpecificationExecutor<CommonRight>{


    CommonRight findByParentIdAndRightCodeAndEnabled(Integer parentId, String rightCode, Integer enable);
}