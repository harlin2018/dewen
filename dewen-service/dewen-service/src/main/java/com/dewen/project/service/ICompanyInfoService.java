
package com.dewen.project.service;

import com.dewen.project.domain.CompanyInfo;
import com.dewen.project.domain.ExportParam;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 重点工业企业基本情况表
 * <p>Description: CompanyInfo Service  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
public interface ICompanyInfoService {

    /**
     * create CompanyInfo
     * @param CompanyInfo
     * @return
     */
    public int createCompanyInfo(CompanyInfo CompanyInfo);

    /**
     * update CompanyInfo ,including logical delete
     * @param CompanyInfo
     * @param id
     * @return
     */
    public int updateCompanyInfo(CompanyInfo CompanyInfo, Integer id);

    /**
     * delete by id (physical delete)
     * @param id
     * @return
     */
    public int deleteCompanyInfo(Integer id);

    /**
     * find by id
     * @param id
     * @return
     */
    public CompanyInfo findById(Integer id) ;

    /**
     *  list the  CompanyInfo ,including page ,search , sorts (XX,YY,ZZ)
     * @param  CompanyInfo
     * @param pageNumber
     * @param pageSize
     * @param sorts
     * @return
     */
    public Page<CompanyInfo> list(CompanyInfo CompanyInfo, int pageNumber, int pageSize, String sorts) ;

    Map<String, Object> record();

    List<Object> getListData(ExportParam exportParam);

    ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response, String fileName, String path) throws IOException;
}