
package com.dewen.project.service;

import com.dewen.project.domain.CompanyRecord;
import org.springframework.data.domain.Page;

/**
 * 记录类型表
 * <p>Description: CompanyRecord Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
public interface ICompanyRecordService {

    /**
     * create CompanyRecord
     * @param CompanyRecord
     * @return
     */
    public int createCompanyRecord(CompanyRecord CompanyRecord);

    /**
     * update CompanyRecord ,including logical delete
     * @param CompanyRecord
     * @param id
     * @return
     */
    public int updateCompanyRecord(CompanyRecord CompanyRecord, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCompanyRecord(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CompanyRecord findById(Integer id) ;

    /**
     *  list the  CompanyRecord ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CompanyRecord
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CompanyRecord> list(CompanyRecord CompanyRecord, int pageNumber, int pageSize, String sorts) ;

    int approvalData(Integer id, String completeContent, Integer completeFileId);
}