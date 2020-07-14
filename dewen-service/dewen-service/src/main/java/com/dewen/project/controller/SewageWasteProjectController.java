package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.SewageWasteProject;
import com.dewen.project.service.ISewageWasteProjectService;
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
 * 排污种类检测项目
 * <p>Description: SewageWasteProject Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@RestController
@RequestMapping("/project/sewageWasteProject")
public class SewageWasteProjectController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ISewageWasteProjectService SewageWasteProjectService;

    /**
     * Get all list for SewageWasteProject
     * @return
     */
    @ApiOperation(value="Get list for SewageWasteProject", notes="Get list for SewageWasteProject")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<SewageWasteProject>> list(@RequestBody(required = false)  SewageWasteProject SewageWasteProject , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                       @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                       @RequestParam(required = false) String sorts) {
        Page<SewageWasteProject> SewageWasteProjects = SewageWasteProjectService.list(SewageWasteProject,pageNumber,pageSize,sorts);
        if(SewageWasteProjects==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(SewageWasteProjects);
        }

    }

    /**
     * Add for SewageWasteProject
     * @param SewageWasteProject
     * @return
     */
    @ApiOperation(value="Create SewageWasteProject", notes="According to SewageWasteProject to create")
    @ApiImplicitParam(name = "SewageWasteProject", value = "Detail entity SewageWasteProject", required = true, dataType = "SewageWasteProject")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody SewageWasteProject SewageWasteProject) {
        int result = SewageWasteProjectService.createSewageWasteProject(SewageWasteProject);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(SewageWasteProject);
            return baseManager.composeSuccessBaseResponse(SewageWasteProject);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify SewageWasteProject information according to the id
     * @param SewageWasteProject
     * @return
     */
    @ApiOperation(value="update SewageWasteProject information ", notes="According to url id to update SewageWasteProject information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "SewageWasteProjectID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "SewageWasteProject", value = "entity SewageWasteProject", required = true, dataType = "SewageWasteProject")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody SewageWasteProject SewageWasteProject) {

        int result = SewageWasteProjectService.updateSewageWasteProject(SewageWasteProject,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(SewageWasteProjectService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find SewageWasteProject according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find SewageWasteProject", notes="According to url id to find SewageWasteProject")
    @ApiImplicitParam(name = "id", value = "SewageWasteProject ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<SewageWasteProject> get(@PathVariable("id")Integer id) {
        SewageWasteProject SewageWasteProject = SewageWasteProjectService.findById(id);
        if(SewageWasteProject==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(SewageWasteProject);
        }

    }


    /**
     * Delete SewageWasteProject according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete SewageWasteProject", notes="According to id to delete SewageWasteProject")
    @ApiImplicitParam(name = "id", value = "SewageWasteProject ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = SewageWasteProjectService.deleteSewageWasteProject(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}