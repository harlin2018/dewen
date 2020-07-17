
package com.dewen.project.service;

import com.dewen.project.domain.CommonModelFile;
import org.springframework.data.domain.Page;

/**
 * common_model_file
 * <p>Description: CommonModelFile Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-17
 */
public interface ICommonModelFileService {

    /**
     * create CommonModelFile
     * @param CommonModelFile
     * @return
     */
    public int createCommonModelFile(CommonModelFile CommonModelFile);

    /**
     * update CommonModelFile ,including logical delete
     * @param CommonModelFile
     * @param id
     * @return
     */
    public int updateCommonModelFile(CommonModelFile CommonModelFile, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCommonModelFile(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CommonModelFile findById(Integer id) ;

    /**
     *  list the  CommonModelFile ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CommonModelFile
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CommonModelFile> list(CommonModelFile CommonModelFile, int pageNumber, int pageSize, String sorts) ;

}