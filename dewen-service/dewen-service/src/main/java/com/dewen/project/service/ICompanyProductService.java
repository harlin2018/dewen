
package com.dewen.project.service;

import com.dewen.project.domain.CompanyProduct;
import org.springframework.data.domain.Page;

/**
 * 主要产品生产情况表
 * <p>Description: CompanyProduct Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
public interface ICompanyProductService {

    /**
     * create CompanyProduct
     * @param CompanyProduct
     * @return
     */
    public int createCompanyProduct(CompanyProduct CompanyProduct);

    /**
     * update CompanyProduct ,including logical delete
     * @param CompanyProduct
     * @param id
     * @return
     */
    public int updateCompanyProduct(CompanyProduct CompanyProduct, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCompanyProduct(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CompanyProduct findById(Integer id) ;

    /**
     *  list the  CompanyProduct ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CompanyProduct
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CompanyProduct> list(CompanyProduct CompanyProduct, int pageNumber, int pageSize, String sorts) ;

}