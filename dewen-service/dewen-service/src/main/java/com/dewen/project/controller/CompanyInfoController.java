package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CompanyInfo;
import com.dewen.project.service.ICompanyInfoService;
import com.dewen.project.utils.BaseResponse;
import com.dewen.project.utils.IBaseManager;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import javax.validation.Valid;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

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
}