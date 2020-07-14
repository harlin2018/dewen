package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CommonUserRoleRelationship;
import com.dewen.project.service.ICommonUserRoleRelationshipService;
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
 * common_user_role_relationship
 * <p>Description: CommonUserRoleRelationship Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@RestController
@RequestMapping("/project/commonUserRoleRelationship")
public class CommonUserRoleRelationshipController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICommonUserRoleRelationshipService CommonUserRoleRelationshipService;

    /**
     * Get all list for CommonUserRoleRelationship
     * @return
     */
    @ApiOperation(value="Get list for CommonUserRoleRelationship", notes="Get list for CommonUserRoleRelationship")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CommonUserRoleRelationship>> list(@RequestBody(required = false)  CommonUserRoleRelationship CommonUserRoleRelationship , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                               @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                               @RequestParam(required = false) String sorts) {
        Page<CommonUserRoleRelationship> CommonUserRoleRelationships = CommonUserRoleRelationshipService.list(CommonUserRoleRelationship,pageNumber,pageSize,sorts);
        if(CommonUserRoleRelationships==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonUserRoleRelationships);
        }

    }

    /**
     * Add for CommonUserRoleRelationship
     * @param CommonUserRoleRelationship
     * @return
     */
    @ApiOperation(value="Create CommonUserRoleRelationship", notes="According to CommonUserRoleRelationship to create")
    @ApiImplicitParam(name = "CommonUserRoleRelationship", value = "Detail entity CommonUserRoleRelationship", required = true, dataType = "CommonUserRoleRelationship")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CommonUserRoleRelationship CommonUserRoleRelationship) {
        int result = CommonUserRoleRelationshipService.createCommonUserRoleRelationship(CommonUserRoleRelationship);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CommonUserRoleRelationship);
            return baseManager.composeSuccessBaseResponse(CommonUserRoleRelationship);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CommonUserRoleRelationship information according to the id
     * @param CommonUserRoleRelationship
     * @return
     */
    @ApiOperation(value="update CommonUserRoleRelationship information ", notes="According to url id to update CommonUserRoleRelationship information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CommonUserRoleRelationshipID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CommonUserRoleRelationship", value = "entity CommonUserRoleRelationship", required = true, dataType = "CommonUserRoleRelationship")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CommonUserRoleRelationship CommonUserRoleRelationship) {

        int result = CommonUserRoleRelationshipService.updateCommonUserRoleRelationship(CommonUserRoleRelationship,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CommonUserRoleRelationshipService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CommonUserRoleRelationship according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CommonUserRoleRelationship", notes="According to url id to find CommonUserRoleRelationship")
    @ApiImplicitParam(name = "id", value = "CommonUserRoleRelationship ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CommonUserRoleRelationship> get(@PathVariable("id")Integer id) {
        CommonUserRoleRelationship CommonUserRoleRelationship = CommonUserRoleRelationshipService.findById(id);
        if(CommonUserRoleRelationship==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonUserRoleRelationship);
        }

    }


    /**
     * Delete CommonUserRoleRelationship according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CommonUserRoleRelationship", notes="According to id to delete CommonUserRoleRelationship")
    @ApiImplicitParam(name = "id", value = "CommonUserRoleRelationship ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = CommonUserRoleRelationshipService.deleteCommonUserRoleRelationship(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}