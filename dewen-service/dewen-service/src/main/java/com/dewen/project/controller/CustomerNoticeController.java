package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CustomerNotice;
import com.dewen.project.service.ICustomerNoticeService;
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
 * customer_notice
 * <p>Description: CustomerNotice Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-08-04
 */
@RestController
@RequestMapping("/project/customerNotice")
public class CustomerNoticeController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICustomerNoticeService CustomerNoticeService;

    /**
     * 未读数量
     * @return
     */
    @ApiOperation(value = "未读数量", notes = "未读数量")
    @RequestMapping(value = "/noReadCount", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse noReadCount() {
        Integer result = CustomerNoticeService.noReadCount();
        if(result==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(result);
        }
    }

    /**
     * 清空未读数量
     * @return
     */
    @ApiOperation(value = "清空未读数量", notes = "清空未读数量")
    @RequestMapping(value = "/cleanNoRead", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse cleanNoRead() {
        Integer result = CustomerNoticeService.cleanNoRead();
        if(result==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(result);
        }
    }

    /**
     * Get all list for CustomerNotice
     * @return
     */
    @ApiOperation(value="Get list for CustomerNotice", notes="Get list for CustomerNotice")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CustomerNotice>> list(@RequestBody(required = false)  CustomerNotice CustomerNotice , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                   @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                   @RequestParam(required = false) String sorts) {
        Page<CustomerNotice> CustomerNotices = CustomerNoticeService.list(CustomerNotice,pageNumber,pageSize,sorts);
        if(CustomerNotices==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CustomerNotices);
        }

    }

    /**
     * Add for CustomerNotice
     * @param CustomerNotice
     * @return
     */
    @ApiOperation(value="Create CustomerNotice", notes="According to CustomerNotice to create")
    @ApiImplicitParam(name = "CustomerNotice", value = "Detail entity CustomerNotice", required = true, dataType = "CustomerNotice")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CustomerNotice CustomerNotice) {
        int result = CustomerNoticeService.createCustomerNotice(CustomerNotice);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CustomerNotice);
            return baseManager.composeSuccessBaseResponse(CustomerNotice);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CustomerNotice information according to the id
     * @param CustomerNotice
     * @return
     */
    @ApiOperation(value="update CustomerNotice information ", notes="According to url id to update CustomerNotice information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CustomerNoticeID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CustomerNotice", value = "entity CustomerNotice", required = true, dataType = "CustomerNotice")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CustomerNotice CustomerNotice) {

        int result = CustomerNoticeService.updateCustomerNotice(CustomerNotice,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CustomerNoticeService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CustomerNotice according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CustomerNotice", notes="According to url id to find CustomerNotice")
    @ApiImplicitParam(name = "id", value = "CustomerNotice ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CustomerNotice> get(@PathVariable("id")Integer id) {
        CustomerNotice CustomerNotice = CustomerNoticeService.findById(id);
        if(CustomerNotice==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CustomerNotice);
        }

    }


    /**
     * Delete CustomerNotice according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CustomerNotice", notes="According to id to delete CustomerNotice")
    @ApiImplicitParam(name = "id", value = "CustomerNotice ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = CustomerNoticeService.deleteCustomerNotice(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}