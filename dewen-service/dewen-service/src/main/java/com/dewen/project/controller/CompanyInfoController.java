package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.constants.ExportFieIdConstant;
import com.dewen.project.domain.CompanyInfo;
import com.dewen.project.domain.ExportParam;
import com.dewen.project.service.ICompanyInfoService;
import com.dewen.project.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.*;

/**
 * 重点工业企业基本情况表
 * <p>Description: CompanyInfo Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@RestController
@RequestMapping("/project/companyInfo")
public class CompanyInfoController extends BaseController{
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICompanyInfoService CompanyInfoService;


    /**
     * Get all list for CompanyInfo
     * @return
     */
    @ApiOperation(value="Get list for CompanyInfo", notes="Get list for CompanyInfo")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CompanyInfo>> list(@RequestBody(required = false)  CompanyInfo CompanyInfo , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                @RequestParam(required = false) String sorts) {
        Page<CompanyInfo> CompanyInfos = CompanyInfoService.list(CompanyInfo,pageNumber,pageSize,sorts);
        if(CompanyInfos==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanyInfos);
        }

    }

    /**
     * Add for CompanyInfo
     * @param CompanyInfo
     * @return
     */
    @ApiOperation(value="Create CompanyInfo", notes="According to CompanyInfo to create")
    @ApiImplicitParam(name = "CompanyInfo", value = "Detail entity CompanyInfo", required = true, dataType = "CompanyInfo")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CompanyInfo CompanyInfo) {
        int result = CompanyInfoService.createCompanyInfo(CompanyInfo);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CompanyInfo);
            return baseManager.composeSuccessBaseResponse(CompanyInfo);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CompanyInfo information according to the id
     * @param CompanyInfo
     * @return
     */
    @ApiOperation(value="update CompanyInfo information ", notes="According to url id to update CompanyInfo information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CompanyInfoID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CompanyInfo", value = "entity CompanyInfo", required = true, dataType = "CompanyInfo")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CompanyInfo CompanyInfo) {

        int result = CompanyInfoService.updateCompanyInfo(CompanyInfo,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CompanyInfoService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CompanyInfo according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CompanyInfo", notes="According to url id to find CompanyInfo")
    @ApiImplicitParam(name = "id", value = "CompanyInfo ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CompanyInfo> get(@PathVariable("id")Integer id) {
        CompanyInfo CompanyInfo = CompanyInfoService.findById(id);
        if(CompanyInfo==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanyInfo);
        }

    }


    /**
     * Delete CompanyInfo according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CompanyInfo", notes="According to id to delete CompanyInfo")
    @ApiImplicitParam(name = "id", value = "CompanyInfo ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {
        int result = CompanyInfoService.deleteCompanyInfo(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }

    /**
     * 获取追加的历史记录
     */
    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public BaseResponse countInfo() {
        return baseManager.composeSuccessBaseResponse(CompanyInfoService.record());
    }

    @RequestMapping(value = "/download/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, @RequestParam List<String> fieIds, @RequestParam List<Integer> ids) throws Exception {
        try {
            //excel列头信息
            String[] rowsName;
            if (fieIds==null||fieIds.size()<=0){
                Map<String, String> titelMap = ExportFieIdConstant.getFieIds();
                rowsName = new String[titelMap.size()];
                int i = 0;
                for (String value : titelMap.values()) {
                    rowsName[i] = value;
                    i++;
                }
            } else {
                rowsName = new String[fieIds.size()];
                Map<String, String> titelMap = ExportFieIdConstant.getFieIds();
                for (int i = 0; i < fieIds.size(); i++) {
                    rowsName[i] = titelMap.get(fieIds.get(i));
                }
            }
            ExportParam exportParam = new ExportParam();
            exportParam.setFieIds(fieIds);
            exportParam.setIds(ids);
            List<Object> list = CompanyInfoService.getListData(exportParam);

            List<Object[]> dataList = new ArrayList<Object[]>();
            Object[] objs = null;
            for (int i = 0; i < list.size(); i++) {
                Object [] map = new Object[rowsName.length];
                if (rowsName.length==1){
                    map[0] = list.get(i);
                }else{
                    map = (Object[]) list.get(i);
                }
                objs = new Object[rowsName.length];
                for (int j = 0; j < map.length; j++) {
                    objs[j] = map[j];
                }
                dataList.add(objs);
            }

            String title = "废弃单元";
            response.setHeader("Content-Disposition", "attachment; filename=\"" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls\"");
            response.setContentType("APPLICATION/OCTET-STREAM");

            ServletOutputStream os = response.getOutputStream();

            ExportExcel excel = new ExportExcel(title, rowsName, dataList);
            excel.export(os);

            os.flush();
            os.close();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("数据导出异常");
        }
    }
    @RequestMapping(value = "/download/exportExcel", method = RequestMethod.POST)
    public BaseResponse exportExcel(@RequestBody ExportParam exportParam, HttpServletRequest request) throws Exception {
        // String path = ResourceUtils.getURL("classpath:").getPath();
        String path = "C:/dewen/vue2-manage-master/src/down/";
        try {
            List<String> fieIds = exportParam.getFieIds();
            List<Integer> ids = exportParam.getIds();
            //excel列头信息
            List<String> rowsName = new LinkedList<>();
            if (fieIds==null||fieIds.size()<=0){
                fieIds = new LinkedList<>();
                Map<String, String> titelMap = ExportFieIdConstant.getFieIds();
                int i = 0;
                for (String key : titelMap.keySet()) {
                    fieIds.add(key);
                    rowsName.add(titelMap.get(key));
                    i++;
                }
            } else {
                Map<String, String> titelMap = ExportFieIdConstant.getFieIds();
                for (int i = 0; i < fieIds.size(); i++) {
                    rowsName.add(titelMap.get(fieIds.get(i)));
                }
            }
            exportParam.setPath(path);

            List<Object> list = CompanyInfoService.getListData(exportParam);

            List<List<Object>> objects = new LinkedList<>();
            for (int i = 0; i < list.size(); i++) {
                List<Object> dataA = new LinkedList<>();
                Object [] map = new Object[rowsName.size()];
                if (rowsName.size()==1){
                    map[0] = list.get(i);
                }else{
                    map = (Object[]) list.get(i);
                }
                for (int j = 0; j < map.length; j++) {
                    dataA.add(map[j]);
                }
                objects.add(dataA);
            }
            String fileName = exportParam.getTitle() + "-" + String.valueOf(System.currentTimeMillis()).substring(4, 13);
            String title = StringUtils.isEmpty(exportParam.getTitle())?"导出数据":exportParam.getTitle();
            ExportUtil.writeExcel(exportParam.getPath(), fileName, title, rowsName, title, objects, false);
            return baseManager.composeSuccessBaseResponse(exportParam.getPath()+fileName+".xls");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("数据导出异常");
        }
    }

}