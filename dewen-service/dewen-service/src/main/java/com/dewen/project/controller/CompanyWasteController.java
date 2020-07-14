package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CompanyWaste;
import com.dewen.project.service.ICompanyWasteService;
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
/**
 * company_waste
 * <p>Description: CompanyWaste Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@RestController
@RequestMapping("/project/companyWaste")
public class CompanyWasteController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICompanyWasteService CompanyWasteService;

    /**
     * Get all list for CompanyWaste
     * @return
     */
    @ApiOperation(value="Get list for CompanyWaste", notes="Get list for CompanyWaste")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CompanyWaste>> list(@RequestBody(required = false)  CompanyWaste CompanyWaste , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                 @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                 @RequestParam(required = false) String sorts) {
        Page<CompanyWaste> CompanyWastes = CompanyWasteService.list(CompanyWaste,pageNumber,pageSize,sorts);
        if(CompanyWastes==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanyWastes);
        }

    }

    /**
     * Add for CompanyWaste
     * @param CompanyWaste
     * @return
     */
    @ApiOperation(value="Create CompanyWaste", notes="According to CompanyWaste to create")
    @ApiImplicitParam(name = "CompanyWaste", value = "Detail entity CompanyWaste", required = true, dataType = "CompanyWaste")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CompanyWaste CompanyWaste) {
        int result = CompanyWasteService.createCompanyWaste(CompanyWaste);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CompanyWaste);
            return baseManager.composeSuccessBaseResponse(CompanyWaste);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CompanyWaste information according to the id
     * @param CompanyWaste
     * @return
     */
    @ApiOperation(value="update CompanyWaste information ", notes="According to url id to update CompanyWaste information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CompanyWasteID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CompanyWaste", value = "entity CompanyWaste", required = true, dataType = "CompanyWaste")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CompanyWaste CompanyWaste) {

        int result = CompanyWasteService.updateCompanyWaste(CompanyWaste,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CompanyWasteService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CompanyWaste according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CompanyWaste", notes="According to url id to find CompanyWaste")
    @ApiImplicitParam(name = "id", value = "CompanyWaste ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CompanyWaste> get(@PathVariable("id")Integer id) {
        CompanyWaste CompanyWaste = CompanyWasteService.findById(id);
        if(CompanyWaste==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanyWaste);
        }

    }


    /**
     * Delete CompanyWaste according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CompanyWaste", notes="According to id to delete CompanyWaste")
    @ApiImplicitParam(name = "id", value = "CompanyWaste ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = CompanyWasteService.deleteCompanyWaste(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}