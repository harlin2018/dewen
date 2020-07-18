package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CommonRoleRightRelationship;
import com.dewen.project.service.ICommonRoleRightRelationshipService;
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
 * common_role_right_relationship
 * <p>Description: CommonRoleRightRelationship Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-18
 */
@RestController
@RequestMapping("/commonRoleRightRelationship")
public class CommonRoleRightRelationshipController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICommonRoleRightRelationshipService CommonRoleRightRelationshipService;

    /**
     * Get all list for CommonRoleRightRelationship
     * @return
     */
    @ApiOperation(value="Get list for CommonRoleRightRelationship", notes="Get list for CommonRoleRightRelationship")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CommonRoleRightRelationship>> list(@RequestBody(required = false)  CommonRoleRightRelationship CommonRoleRightRelationship , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                                @RequestParam(required = false) String sorts) {
        Page<CommonRoleRightRelationship> CommonRoleRightRelationships = CommonRoleRightRelationshipService.list(CommonRoleRightRelationship,pageNumber,pageSize,sorts);
        if(CommonRoleRightRelationships==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonRoleRightRelationships);
        }

    }

    /**
     * Add for CommonRoleRightRelationship
     * @param CommonRoleRightRelationship
     * @return
     */
    @ApiOperation(value="Create CommonRoleRightRelationship", notes="According to CommonRoleRightRelationship to create")
    @ApiImplicitParam(name = "CommonRoleRightRelationship", value = "Detail entity CommonRoleRightRelationship", required = true, dataType = "CommonRoleRightRelationship")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CommonRoleRightRelationship CommonRoleRightRelationship) {
        int result = CommonRoleRightRelationshipService.createCommonRoleRightRelationship(CommonRoleRightRelationship);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CommonRoleRightRelationship);
            return baseManager.composeSuccessBaseResponse(CommonRoleRightRelationship);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CommonRoleRightRelationship information according to the id
     * @param CommonRoleRightRelationship
     * @return
     */
    @ApiOperation(value="update CommonRoleRightRelationship information ", notes="According to url id to update CommonRoleRightRelationship information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CommonRoleRightRelationshipID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CommonRoleRightRelationship", value = "entity CommonRoleRightRelationship", required = true, dataType = "CommonRoleRightRelationship")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CommonRoleRightRelationship CommonRoleRightRelationship) {

        int result = CommonRoleRightRelationshipService.updateCommonRoleRightRelationship(CommonRoleRightRelationship,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CommonRoleRightRelationshipService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CommonRoleRightRelationship according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CommonRoleRightRelationship", notes="According to url id to find CommonRoleRightRelationship")
    @ApiImplicitParam(name = "id", value = "CommonRoleRightRelationship ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CommonRoleRightRelationship> get(@PathVariable("id")Integer id) {
        CommonRoleRightRelationship CommonRoleRightRelationship = CommonRoleRightRelationshipService.findById(id);
        if(CommonRoleRightRelationship==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonRoleRightRelationship);
        }

    }


    /**
     * Delete CommonRoleRightRelationship according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CommonRoleRightRelationship", notes="According to id to delete CommonRoleRightRelationship")
    @ApiImplicitParam(name = "id", value = "CommonRoleRightRelationship ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = CommonRoleRightRelationshipService.deleteCommonRoleRightRelationship(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}