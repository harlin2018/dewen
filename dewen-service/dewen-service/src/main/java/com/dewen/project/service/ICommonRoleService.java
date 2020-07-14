
package com.dewen.project.service;

import com.dewen.project.domain.CommonRole;
import org.springframework.data.domain.Page;

/**
 * common_role
 * <p>Description: CommonRole Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
public interface ICommonRoleService {

    /**
     * create CommonRole
     * @param CommonRole
     * @return
     */
    public int createCommonRole(CommonRole CommonRole);

    /**
     * update CommonRole ,including logical delete
     * @param CommonRole
     * @param id
     * @return
     */
    public int updateCommonRole(CommonRole CommonRole, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCommonRole(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CommonRole findById(Integer id) ;

    /**
     *  list the  CommonRole ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CommonRole
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CommonRole> list(CommonRole CommonRole, int pageNumber, int pageSize, String sorts) ;

}