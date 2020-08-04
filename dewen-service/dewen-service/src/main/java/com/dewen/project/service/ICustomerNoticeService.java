
package com.dewen.project.service;

import com.dewen.project.domain.CustomerNotice;
import org.springframework.data.domain.Page;

/**
 * customer_notice
 * <p>Description: CustomerNotice Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-08-04
 */
public interface ICustomerNoticeService {

    /**
     * create CustomerNotice
     * @param CustomerNotice
     * @return
     */
    public int createCustomerNotice(CustomerNotice CustomerNotice);

    /**
     * update CustomerNotice ,including logical delete
     * @param CustomerNotice
     * @param id
     * @return
     */
    public int updateCustomerNotice(CustomerNotice CustomerNotice, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCustomerNotice(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CustomerNotice findById(Integer id) ;

    /**
     *  list the  CustomerNotice ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CustomerNotice
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CustomerNotice> list(CustomerNotice CustomerNotice, int pageNumber, int pageSize, String sorts) ;

    int noReadCount();

    Integer cleanNoRead();
}