
package com.dewen.project.service;

import com.dewen.project.domain.SewageWasteProject;
import org.springframework.data.domain.Page;

/**
 * 排污种类检测项目
 * <p>Description: SewageWasteProject Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
public interface ISewageWasteProjectService {

    /**
     * create SewageWasteProject
     * @param SewageWasteProject
     * @return
     */
    public int createSewageWasteProject(SewageWasteProject SewageWasteProject);

    /**
     * update SewageWasteProject ,including logical delete
     * @param SewageWasteProject
     * @param id
     * @return
     */
    public int updateSewageWasteProject(SewageWasteProject SewageWasteProject, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteSewageWasteProject(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public SewageWasteProject findById(Integer id) ;

    /**
     *  list the  SewageWasteProject ,including page ,search , sorts (XX,YY,ZZ)
     * @param  SewageWasteProject
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<SewageWasteProject> list(SewageWasteProject SewageWasteProject, int pageNumber, int pageSize, String sorts) ;

}