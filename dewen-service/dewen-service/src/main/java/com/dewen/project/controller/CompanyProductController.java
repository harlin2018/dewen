package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CompanyProduct;
import com.dewen.project.service.ICompanyProductService;
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
 * 主要产品生产情况表
 * <p>Description: CompanyProduct Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@RestController
@RequestMapping("/project/companyProduct")
public class CompanyProductController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICompanyProductService CompanyProductService;

    /**
     * Get all list for CompanyProduct
     * @return
     */
    @ApiOperation(value="Get list for CompanyProduct", notes="Get list for CompanyProduct")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CompanyProduct>> list(@RequestBody(required = false)  CompanyProduct CompanyProduct , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                   @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                   @RequestParam(required = false) String sorts) {
        Page<CompanyProduct> CompanyProducts = CompanyProductService.list(CompanyProduct,pageNumber,pageSize,sorts);
        if(CompanyProducts==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanyProducts);
        }

    }

    /**
     * Add for CompanyProduct
     * @param CompanyProduct
     * @return
     */
    @ApiOperation(value="Create CompanyProduct", notes="According to CompanyProduct to create")
    @ApiImplicitParam(name = "CompanyProduct", value = "Detail entity CompanyProduct", required = true, dataType = "CompanyProduct")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CompanyProduct CompanyProduct) {
        int result = CompanyProductService.createCompanyProduct(CompanyProduct);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CompanyProduct);
            return baseManager.composeSuccessBaseResponse(CompanyProduct);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CompanyProduct information according to the id
     * @param CompanyProduct
     * @return
     */
    @ApiOperation(value="update CompanyProduct information ", notes="According to url id to update CompanyProduct information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CompanyProductID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CompanyProduct", value = "entity CompanyProduct", required = true, dataType = "CompanyProduct")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CompanyProduct CompanyProduct) {

        int result = CompanyProductService.updateCompanyProduct(CompanyProduct,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CompanyProductService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CompanyProduct according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CompanyProduct", notes="According to url id to find CompanyProduct")
    @ApiImplicitParam(name = "id", value = "CompanyProduct ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CompanyProduct> get(@PathVariable("id")Integer id) {
        CompanyProduct CompanyProduct = CompanyProductService.findById(id);
        if(CompanyProduct==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanyProduct);
        }

    }


    /**
     * Delete CompanyProduct according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CompanyProduct", notes="According to id to delete CompanyProduct")
    @ApiImplicitParam(name = "id", value = "CompanyProduct ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = CompanyProductService.deleteCompanyProduct(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}