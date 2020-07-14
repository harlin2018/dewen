
package com.dewen.project.service;

import com.dewen.project.domain.CompanySewageWaste;
import org.springframework.data.domain.Page;

/**
 * 排污种类
 * <p>Description: CompanySewageWaste Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
public interface ICompanySewageWasteService {

    /**
     * create CompanySewageWaste
     * @param CompanySewageWaste
     * @return
     */
    public int createCompanySewageWaste(CompanySewageWaste CompanySewageWaste);

    /**
     * update CompanySewageWaste ,including logical delete
     * @param CompanySewageWaste
     * @param id
     * @return
     */
    public int updateCompanySewageWaste(CompanySewageWaste CompanySewageWaste, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCompanySewageWaste(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CompanySewageWaste findById(Integer id) ;

    /**
     *  list the  CompanySewageWaste ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CompanySewageWaste
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CompanySewageWaste> list(CompanySewageWaste CompanySewageWaste, int pageNumber, int pageSize, String sorts) ;

}