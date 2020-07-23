package com.dewen.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CommonRight;
import com.dewen.project.domain.CommonUser;
import com.dewen.project.domain.DTO.CommonUserDTO;
import com.dewen.project.domain.DTO.UserAssignRoleRequest;
import com.dewen.project.domain.support.CommonRightSupport;
import com.dewen.project.domain.support.UserInfo;
import com.dewen.project.service.ICommonUserService;
import com.dewen.project.utils.BaseResponse;
import com.dewen.project.utils.IBaseManager;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * common_user
 * <p>Description: CommonUser Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@RestController
@RequestMapping("/project/commonUser")
public class CommonUserController extends BaseController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICommonUserService CommonUserService;

    //登入用户
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResponse loginBak(@RequestBody(required = false) CommonUser userInfo) {
        CommonUser user =  CommonUserService.getUser(userInfo.getLoginName(),userInfo.getHashPassword());
        BaseResponse res = new BaseResponse();
        if(user!=null){
            Map<String, Object> map = new HashMap<String, Object>();
            List<UserInfo.RightInfo> rightList = CommonUserService.findRight(user.getId());
            map.put("menus", CommonRightSupport.toRightInfoTree(rightList));
            map.put("introduction","I am a super administrator");

            CommonUser userDTO = CommonUserService.findById(user.getId());
            String avatar = userDTO.getHeadImg();
            if(StringUtils.isBlank(avatar)){
                avatar = "/cmdb/pic.gif";
            }
            map.put("avatar", avatar);
            map.put("user",user);
            res.setPayload(map);
//            res.setPayload(user);
            res.setResultCode("0");
            res.setResultMsg("success");
        }else{
            res.setResultCode("100001");
            res.setResultMsg("账号或密码错误或无权限进入");
        }
        return res;
    }

    /**
     * Get all list for CommonUser
     * @return
     */
    @ApiOperation(value="Get list for CommonUser", notes="Get list for CommonUser")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CommonUser>> list(@RequestBody(required = false)  CommonUser CommonUser , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                               @RequestParam(required = false, defaultValue = "10") int pageSize,
                                               @RequestParam(required = false) String sorts) {
        Page<CommonUser> CommonUsers = CommonUserService.list(CommonUser,pageNumber,pageSize,sorts);
        if(CommonUsers==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonUsers);
        }

    }

    /**
     * Add for CommonUser
     * @param CommonUser
     * @return
     */
    @ApiOperation(value="Create CommonUser", notes="According to CommonUser to create")
    @ApiImplicitParam(name = "CommonUser", value = "Detail entity CommonUser", required = true, dataType = "CommonUser")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CommonUser CommonUser) {
        int result = CommonUserService.createCommonUser(CommonUser);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CommonUser);
            return baseManager.composeSuccessBaseResponse(CommonUser);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CommonUser information according to the id
     * @param CommonUser
     * @return
     */
    @ApiOperation(value="update CommonUser information ", notes="According to url id to update CommonUser information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CommonUserID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CommonUser", value = "entity CommonUser", required = true, dataType = "CommonUser")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CommonUser CommonUser) {

        int result = CommonUserService.updateCommonUser(CommonUser,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CommonUserService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CommonUser according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CommonUser", notes="According to url id to find CommonUser")
    @ApiImplicitParam(name = "id", value = "CommonUser ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CommonUser> get(@PathVariable("id")Integer id) {
        CommonUser CommonUser = CommonUserService.findById(id);
        if(CommonUser==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonUser);
        }

    }


    /**
     * Delete CommonUser according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CommonUser", notes="According to id to delete CommonUser")
    @ApiImplicitParam(name = "id", value = "CommonUser ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = CommonUserService.deleteCommonUser(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }

    /**
     * 审批
     *
     * @param paramObj
     * @return
     */
    @ApiOperation(value = "审批", notes = "审批")
    @RequestMapping(value = "/approvalUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse approvalUser(@RequestBody JSONObject paramObj) {
        int result = CommonUserService.approvalUser(getJSONInteger(paramObj, "id"), getJSONInteger(paramObj, "status"));
        if(result == Constants.RETURN_STATUS_SUCCESS) {
            return baseManager.composeCommonSuccessResponse();
        } else{
            return baseManager.composeDBFailResponse();
        }
    }

    /**
     * Modify CommonUser information according to the id
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "update CommonUser information ", notes = "According to url id to update CommonUser information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CommonUserID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "CommonUser", value = "entity CommonUser", required = true, dataType = "CommonUser")
    })
    @RequestMapping(value = "/assignRole", method = RequestMethod.POST)
    public BaseResponse assignRole(@RequestBody @Valid UserAssignRoleRequest request) {

        boolean result = CommonUserService.assignRole(request.getUserId(), request.getRoleIds());
        if (result) {

            return baseManager.composeCommonSuccessResponse();
        } else {
            return baseManager.composeDBFailResponse();
        }

    }
}