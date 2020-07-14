package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CompanyProject;
import com.dewen.project.service.ICompanyProjectService;
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
 * 监测项目表
 * <p>Description: CompanyProject Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@RestController
@RequestMapping("/project/companyProject")
public class CompanyProjectController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICompanyProjectService CompanyProjectService;

    /**
     * Get all list for CompanyProject
     * @return
     */
    @ApiOperation(value="Get list for CompanyProject", notes="Get list for CompanyProject")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CompanyProject>> list(@RequestBody(required = false)  CompanyProject CompanyProject , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                   @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                   @RequestParam(required = false) String sorts) {
        Page<CompanyProject> CompanyProjects = CompanyProjectService.list(CompanyProject,pageNumber,pageSize,sorts);
        if(CompanyProjects==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanyProjects);
        }

    }

    /**
     * Add for CompanyProject
     * @param CompanyProject
     * @return
     */
    @ApiOperation(value="Create CompanyProject", notes="According to CompanyProject to create")
    @ApiImplicitParam(name = "CompanyProject", value = "Detail entity CompanyProject", required = true, dataType = "CompanyProject")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CompanyProject CompanyProject) {
        int result = CompanyProjectService.createCompanyProject(CompanyProject);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CompanyProject);
            return baseManager.composeSuccessBaseResponse(CompanyProject);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CompanyProject information according to the id
     * @param CompanyProject
     * @return
     */
    @ApiOperation(value="update CompanyProject information ", notes="According to url id to update CompanyProject information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CompanyProjectID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CompanyProject", value = "entity CompanyProject", required = true, dataType = "CompanyProject")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CompanyProject CompanyProject) {

        int result = CompanyProjectService.updateCompanyProject(CompanyProject,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CompanyProjectService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CompanyProject according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CompanyProject", notes="According to url id to find CompanyProject")
    @ApiImplicitParam(name = "id", value = "CompanyProject ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CompanyProject> get(@PathVariable("id")Integer id) {
        CompanyProject CompanyProject = CompanyProjectService.findById(id);
        if(CompanyProject==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CompanyProject);
        }

    }


    /**
     * Delete CompanyProject according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CompanyProject", notes="According to id to delete CompanyProject")
    @ApiImplicitParam(name = "id", value = "CompanyProject ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = CompanyProjectService.deleteCompanyProject(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}