
package com.dewen.project.service;

import com.dewen.project.domain.CommonUserRoleRelationship;
import org.springframework.data.domain.Page;

/**
 * common_user_role_relationship
 * <p>Description: CommonUserRoleRelationship Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
public interface ICommonUserRoleRelationshipService {

    /**
     * create CommonUserRoleRelationship
     * @param CommonUserRoleRelationship
     * @return
     */
    public int createCommonUserRoleRelationship(CommonUserRoleRelationship CommonUserRoleRelationship);

    /**
     * update CommonUserRoleRelationship ,including logical delete
     * @param CommonUserRoleRelationship
     * @param id
     * @return
     */
    public int updateCommonUserRoleRelationship(CommonUserRoleRelationship CommonUserRoleRelationship, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCommonUserRoleRelationship(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CommonUserRoleRelationship findById(Integer id) ;

    /**
     *  list the  CommonUserRoleRelationship ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CommonUserRoleRelationship
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CommonUserRoleRelationship> list(CommonUserRoleRelationship CommonUserRoleRelationship, int pageNumber, int pageSize, String sorts) ;

}