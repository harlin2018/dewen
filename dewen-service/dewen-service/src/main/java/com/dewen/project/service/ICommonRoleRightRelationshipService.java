
package com.dewen.project.service;

import com.dewen.project.domain.CommonRoleRightRelationship;
import org.springframework.data.domain.Page;

/**
 * common_role_right_relationship
 * <p>Description: CommonRoleRightRelationship Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-18
 */
public interface ICommonRoleRightRelationshipService {

    /**
     * create CommonRoleRightRelationship
     * @param CommonRoleRightRelationship
     * @return
     */
    public int createCommonRoleRightRelationship(CommonRoleRightRelationship CommonRoleRightRelationship);

    /**
     * update CommonRoleRightRelationship ,including logical delete
     * @param CommonRoleRightRelationship
     * @param id
     * @return
     */
    public int updateCommonRoleRightRelationship(CommonRoleRightRelationship CommonRoleRightRelationship, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCommonRoleRightRelationship(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CommonRoleRightRelationship findById(Integer id) ;

    /**
     *  list the  CommonRoleRightRelationship ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CommonRoleRightRelationship
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CommonRoleRightRelationship> list(CommonRoleRightRelationship CommonRoleRightRelationship, int pageNumber, int pageSize, String sorts) ;

}