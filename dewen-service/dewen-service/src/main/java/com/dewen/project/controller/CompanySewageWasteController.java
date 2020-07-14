package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CompanySewageWaste;
import com.dewen.project.service.ICompanySewageWasteService;
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
 * 排污种类
 * <p>Description: CompanySewageWaste Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@RestController
@RequestMapping("/project/companySewageWaste")
public class CompanySewageWasteController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICompanySewageWasteService CompanySewageWasteService;

    /**
     * Get all list for CompanySewageWaste
     * @return
     */
    @ApiOperation(value="Get list for CompanySewageWaste", notes="Get list for CompanySewageWaste")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CompanySewageWaste>> list(@RequestBody(required = false)  CompanySewageWaste CompanySewageWaste , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                       @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                       @RequestParam(required = false) String sorts) {
        Page<CompanySewageWaste> CompanySewageWastes = CompanySewageWasteService.list(CompanySewageWaste,pageNumber,pageSize,sorts);
        if(CompanySewageWastes==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanySewageWastes);
        }

    }

    /**
     * Add for CompanySewageWaste
     * @param CompanySewageWaste
     * @return
     */
    @ApiOperation(value="Create CompanySewageWaste", notes="According to CompanySewageWaste to create")
    @ApiImplicitParam(name = "CompanySewageWaste", value = "Detail entity CompanySewageWaste", required = true, dataType = "CompanySewageWaste")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CompanySewageWaste CompanySewageWaste) {
        int result = CompanySewageWasteService.createCompanySewageWaste(CompanySewageWaste);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CompanySewageWaste);
            return baseManager.composeSuccessBaseResponse(CompanySewageWaste);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CompanySewageWaste information according to the id
     * @param CompanySewageWaste
     * @return
     */
    @ApiOperation(value="update CompanySewageWaste information ", notes="According to url id to update CompanySewageWaste information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CompanySewageWasteID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CompanySewageWaste", value = "entity CompanySewageWaste", required = true, dataType = "CompanySewageWaste")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CompanySewageWaste CompanySewageWaste) {

        int result = CompanySewageWasteService.updateCompanySewageWaste(CompanySewageWaste,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CompanySewageWasteService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CompanySewageWaste according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CompanySewageWaste", notes="According to url id to find CompanySewageWaste")
    @ApiImplicitParam(name = "id", value = "CompanySewageWaste ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CompanySewageWaste> get(@PathVariable("id")Integer id) {
        CompanySewageWaste CompanySewageWaste = CompanySewageWasteService.findById(id);
        if(CompanySewageWaste==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanySewageWaste);
        }

    }


    /**
     * Delete CompanySewageWaste according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CompanySewageWaste", notes="According to id to delete CompanySewageWaste")
    @ApiImplicitParam(name = "id", value = "CompanySewageWaste ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = CompanySewageWasteService.deleteCompanySewageWaste(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}