
package com.dewen.project.service;

import com.dewen.project.domain.CompanyWaste;
import org.springframework.data.domain.Page;

/**
 * company_waste
 * <p>Description: CompanyWaste Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
public interface ICompanyWasteService {

    /**
     * create CompanyWaste
     * @param CompanyWaste
     * @return
     */
    public int createCompanyWaste(CompanyWaste CompanyWaste);

    /**
     * update CompanyWaste ,including logical delete
     * @param CompanyWaste
     * @param id
     * @return
     */
    public int updateCompanyWaste(CompanyWaste CompanyWaste, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCompanyWaste(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CompanyWaste findById(Integer id) ;

    /**
     *  list the  CompanyWaste ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CompanyWaste
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CompanyWaste> list(CompanyWaste CompanyWaste, int pageNumber, int pageSize, String sorts) ;

}