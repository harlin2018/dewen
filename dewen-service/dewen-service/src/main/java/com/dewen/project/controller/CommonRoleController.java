package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CommonRight;
import com.dewen.project.domain.CommonRole;
import com.dewen.project.domain.DTO.CommonRightDTO;
import com.dewen.project.service.ICommonRoleService;
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
 * common_role
 * <p>Description: CommonRole Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@RestController
@RequestMapping("/project/commonRole")
public class CommonRoleController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICommonRoleService CommonRoleService;



    /**
     * Get all list for CommonRole
     * @return
     */
    @ApiOperation(value="Get list for CommonRole", notes="Get list for CommonRole")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CommonRole>> list(@RequestBody(required = false)  CommonRole CommonRole , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                               @RequestParam(required = false, defaultValue = "10") int pageSize,
                                               @RequestParam(required = false) String sorts) {
        Page<CommonRole> CommonRoles = CommonRoleService.list(CommonRole,pageNumber,pageSize,sorts);
        if(CommonRoles==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonRoles);
        }

    }

    /**
     * Add for CommonRole
     * @param CommonRole
     * @return
     */
    @ApiOperation(value="Create CommonRole", notes="According to CommonRole to create")
    @ApiImplicitParam(name = "CommonRole", value = "Detail entity CommonRole", required = true, dataType = "CommonRole")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CommonRole CommonRole) {
        int result = CommonRoleService.createCommonRole(CommonRole);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CommonRole);
            return baseManager.composeSuccessBaseResponse(CommonRole);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CommonRole information according to the id
     * @param CommonRole
     * @return
     */
    @ApiOperation(value="update CommonRole information ", notes="According to url id to update CommonRole information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CommonRoleID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CommonRole", value = "entity CommonRole", required = true, dataType = "CommonRole")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CommonRole CommonRole) {

        int result = CommonRoleService.updateCommonRole(CommonRole,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CommonRoleService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CommonRole according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CommonRole", notes="According to url id to find CommonRole")
    @ApiImplicitParam(name = "id", value = "CommonRole ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CommonRole> get(@PathVariable("id")Integer id) {
        CommonRole CommonRole = CommonRoleService.findById(id);
        if(CommonRole==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonRole);
        }

    }


    /**
     * Delete CommonRole according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CommonRole", notes="According to id to delete CommonRole")
    @ApiImplicitParam(name = "id", value = "CommonRole ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = CommonRoleService.deleteCommonRole(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}
