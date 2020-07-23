package com.dewen.project.repository;

import com.dewen.project.domain.CommonRight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    List<CommonRight> findByEnabled(Integer enable);

    @Query(value = "select * from common_right where id in (" +
            "select COM_RIGHT_ID from common_role_right_relationship left join common_user_role_relationship on common_role_right_relationship.COM_ROLE_ID = common_user_role_relationship.COM_ROLE_ID  where common_user_role_relationship.COM_USER_ID = :userId)", nativeQuery = true)
    List<CommonRight> findAllByUserId(Integer userId);

    @Query(value = "select * from common_right where id in (" +
            "select COM_RIGHT_ID from common_role_right_relationship left join common_user_role_relationship on common_role_right_relationship.COM_ROLE_ID = common_user_role_relationship.COM_ROLE_ID  where common_user_role_relationship.COM_USER_ID = :userId) and PARENT_ID = '0'", nativeQuery = true)
    List<CommonRight> selectParent();
}