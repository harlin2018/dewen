
package com.dewen.project.service;

import com.dewen.project.domain.CommonUser;
import org.springframework.data.domain.Page;

/**
 * common_user
 * <p>Description: CommonUser Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
public interface ICommonUserService {

    /**
     * create CommonUser
     * @param CommonUser
     * @return
     */
    public int createCommonUser(CommonUser CommonUser);

    /**
     * update CommonUser ,including logical delete
     * @param CommonUser
     * @param id
     * @return
     */
    public int updateCommonUser(CommonUser CommonUser, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCommonUser(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CommonUser findById(Integer id) ;

    /**
     *  list the  CommonUser ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CommonUser
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CommonUser> list(CommonUser CommonUser, int pageNumber, int pageSize, String sorts) ;

    CommonUser getUser(String loginName, String hashPassword);

    int approvalUser(Integer id, Integer status);
}