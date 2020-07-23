package com.dewen.project.repository;

import com.dewen.project.domain.CommonRoleRightRelationship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * common_role_right_relationship
 * <p>Description: CommonRoleRightRelationship Repository  </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-18
 */
@Repository
public interface CommonRoleRightRelationshipRepository extends JpaRepository<CommonRoleRightRelationship, Integer>, JpaSpecificationExecutor<CommonRoleRightRelationship>{


    default Map<Integer, CommonRoleRightRelationship> findRightByRoleMap(Integer id) {
        List<CommonRoleRightRelationship> rightByRole = findByCommonRoleId(id);
        Map<Integer, CommonRoleRightRelationship> hashMap = new HashMap();
        for (CommonRoleRightRelationship commonRoleRightRelationship : rightByRole) {
            hashMap.put(commonRoleRightRelationship.getCommonRight().getId(), commonRoleRightRelationship);
        }
        return hashMap;
    }

    List<CommonRoleRightRelationship> findByCommonRoleId(Integer commonRoleId);
}