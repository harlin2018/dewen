
package com.dewen.project.service;

import com.dewen.project.domain.CommonRight;
import com.dewen.project.domain.DTO.CommonRightDTO;
import com.dewen.project.domain.DTO.RoleAssignRightRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * common_right
 * <p>Description: CommonRight Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-18
 */
public interface ICommonRightService {

    /**
     * create CommonRight
     * @param CommonRight
     * @return
     */
    public int createCommonRight(CommonRight CommonRight);

    /**
     * update CommonRight ,including logical delete
     * @param CommonRight
     * @param id
     * @return
     */
    public int updateCommonRight(CommonRight CommonRight, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCommonRight(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CommonRight findById(Integer id) ;

    /**
     *  list the  CommonRight ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CommonRight
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CommonRight> list(CommonRight CommonRight, int pageNumber, int pageSize, String sorts) ;

    List<CommonRightDTO> listAll(CommonRight commonRight);

    List<CommonRightDTO> listByRole(Integer roleId);

    boolean grant(RoleAssignRightRequest request);
}