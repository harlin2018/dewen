package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CommonRight;
import com.dewen.project.domain.DTO.CommonRightDTO;
import com.dewen.project.domain.DTO.RightListByRoleRequest;
import com.dewen.project.domain.DTO.RoleAssignRightRequest;
import com.dewen.project.service.ICommonRightService;
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
 * common_right
 * <p>Description: CommonRight Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-18
 */
@RestController
@RequestMapping("/project/commonRight")
public class CommonRightController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICommonRightService commonRightService;

    /**
     * Get all list for CommonRight
     * @return
     */
    @ApiOperation(value="Get list for CommonRight", notes="Get list for CommonRight")
    @RequestMapping(value = "/grant",method = RequestMethod.POST)
    public BaseResponse grant(@RequestBody @Valid RoleAssignRightRequest request) {
        boolean result = commonRightService.grant(request);
        if(result){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }

    /**
     * Get all list for CommonRight
     * @return
     */
    @ApiOperation(value="Get list for CommonRight", notes="Get list for CommonRight")
    @RequestMapping(value = "/listByRole",method = RequestMethod.POST)
    public BaseResponse<Page<CommonRight>> listByRole(@RequestBody @Valid RightListByRoleRequest request) {
        List<CommonRightDTO> rightDTOS = commonRightService.listByRole(request.getRoleId());

        if(rightDTOS==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(rightDTOS);
        }
    }

    /**
     * Get all list for CommonRight
     * @return
     */
    @ApiOperation(value="Get list for CommonRight", notes="Get list for CommonRight")
    @RequestMapping(value = "/listAll",method = RequestMethod.POST)
    public BaseResponse<Page<CommonRightDTO>> listAll(@RequestBody(required = false) CommonRight commonRight) {
        List<CommonRightDTO> rightDTOS = commonRightService.listAll(commonRight);
        if(rightDTOS==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(rightDTOS);
        }

    }
    
    /**
     * Get all list for CommonRight
     * @return
     */
    @ApiOperation(value="Get list for CommonRight", notes="Get list for CommonRight")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CommonRight>> list(@RequestBody(required = false)  CommonRight CommonRight , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                @RequestParam(required = false) String sorts) {
        Page<CommonRight> CommonRights = commonRightService.list(CommonRight,pageNumber,pageSize,sorts);
        if(CommonRights==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonRights);
        }

    }

    /**
     * Add for CommonRight
     * @param CommonRight
     * @return
     */
    @ApiOperation(value="Create CommonRight", notes="According to CommonRight to create")
    @ApiImplicitParam(name = "CommonRight", value = "Detail entity CommonRight", required = true, dataType = "CommonRight")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CommonRight CommonRight) {
        int result = commonRightService.createCommonRight(CommonRight);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CommonRight);
            return baseManager.composeSuccessBaseResponse(CommonRight);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CommonRight information according to the id
     * @param CommonRight
     * @return
     */
    @ApiOperation(value="update CommonRight information ", notes="According to url id to update CommonRight information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CommonRightID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CommonRight", value = "entity CommonRight", required = true, dataType = "CommonRight")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CommonRight CommonRight) {

        int result = commonRightService.updateCommonRight(CommonRight,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(commonRightService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CommonRight according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CommonRight", notes="According to url id to find CommonRight")
    @ApiImplicitParam(name = "id", value = "CommonRight ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CommonRight> get(@PathVariable("id")Integer id) {
        CommonRight CommonRight = commonRightService.findById(id);
        if(CommonRight==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonRight);
        }

    }


    /**
     * Delete CommonRight according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CommonRight", notes="According to id to delete CommonRight")
    @ApiImplicitParam(name = "id", value = "CommonRight ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = commonRightService.deleteCommonRight(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}