package com.dewen.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CompanyRecord;
import com.dewen.project.service.ICompanyRecordService;
import com.dewen.project.utils.BaseResponse;
import com.dewen.project.utils.IBaseManager;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import javax.validation.Valid;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 记录类型表
 * <p>Description: CompanyRecord Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@RestController
@RequestMapping("/project/companyRecord")
public class CompanyRecordController extends BaseController{
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICompanyRecordService CompanyRecordService;

    @ApiOperation(value = "审批完成", notes = "审批完成")
    @RequestMapping(value = "/approval", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse approvalData(@RequestBody JSONObject paramObj) {

        int result = CompanyRecordService.approvalData(getJSONInteger(paramObj, "id"), getJSONString(paramObj, "completeContent"),  getJSONInteger(paramObj, "completeFileId"));
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Get all list for CompanyRecord
     * @return
     */
    @ApiOperation(value="Get list for CompanyRecord", notes="Get list for CompanyRecord")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CompanyRecord>> list(@RequestBody(required = false)  CompanyRecord CompanyRecord , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                  @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                  @RequestParam(required = false) String sorts) {
        Page<CompanyRecord> CompanyRecords = CompanyRecordService.list(CompanyRecord,pageNumber,pageSize,sorts);
        if(CompanyRecords==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanyRecords);
        }

    }

    /**
     * Add for CompanyRecord
     * @param CompanyRecord
     * @return
     */
    @ApiOperation(value="Create CompanyRecord", notes="According to CompanyRecord to create")
    @ApiImplicitParam(name = "CompanyRecord", value = "Detail entity CompanyRecord", required = true, dataType = "CompanyRecord")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CompanyRecord CompanyRecord) {
        int result = CompanyRecordService.createCompanyRecord(CompanyRecord);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CompanyRecord);
            return baseManager.composeSuccessBaseResponse(CompanyRecord);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CompanyRecord information according to the id
     * @param CompanyRecord
     * @return
     */
    @ApiOperation(value="update CompanyRecord information ", notes="According to url id to update CompanyRecord information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CompanyRecordID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CompanyRecord", value = "entity CompanyRecord", required = true, dataType = "CompanyRecord")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CompanyRecord CompanyRecord) {

        int result = CompanyRecordService.updateCompanyRecord(CompanyRecord,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CompanyRecordService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CompanyRecord according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CompanyRecord", notes="According to url id to find CompanyRecord")
    @ApiImplicitParam(name = "id", value = "CompanyRecord ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CompanyRecord> get(@PathVariable("id")Integer id) {
        CompanyRecord CompanyRecord = CompanyRecordService.findById(id);
        if(CompanyRecord==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanyRecord);
        }

    }


    /**
     * Delete CompanyRecord according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CompanyRecord", notes="According to id to delete CompanyRecord")
    @ApiImplicitParam(name = "id", value = "CompanyRecord ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = CompanyRecordService.deleteCompanyRecord(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}