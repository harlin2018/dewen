
package com.dewen.project.service;

import com.dewen.project.domain.CompanyProject;
import org.springframework.data.domain.Page;

/**
 * 主要产品生产情况表
 * <p>Description: CompanyProject Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
public interface ICompanyProjectService {

    /**
     * create CompanyProject
     * @param CompanyProject
     * @return
     */
    public int createCompanyProject(CompanyProject CompanyProject);

    /**
     * update CompanyProject ,including logical delete
     * @param CompanyProject
     * @param id
     * @return
     */
    public int updateCompanyProject(CompanyProject CompanyProject, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCompanyProject(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CompanyProject findById(Integer id) ;

    /**
     *  list the  CompanyProject ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CompanyProject
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CompanyProject> list(CompanyProject CompanyProject, int pageNumber, int pageSize, String sorts) ;

}