package com.dewen.project.controller;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CommonModelFile;
import com.dewen.project.service.ICommonModelFileService;
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
 * common_model_file
 * <p>Description: CommonModelFile Controller </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-17
 */
@RestController
@RequestMapping("/commonModelFile")
public class CommonModelFileController {
    @Autowired
    private IBaseManager baseManager;
    @Autowired
    private ICommonModelFileService CommonModelFileService;

    /**
     * Get all list for CommonModelFile
     * @return
     */
    @ApiOperation(value="Get list for CommonModelFile", notes="Get list for CommonModelFile")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public BaseResponse<Page<CommonModelFile>> list(@RequestBody(required = false)  CommonModelFile CommonModelFile , @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                    @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                    @RequestParam(required = false) String sorts) {
        Page<CommonModelFile> CommonModelFiles = CommonModelFileService.list(CommonModelFile,pageNumber,pageSize,sorts);
        if(CommonModelFiles==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonModelFiles);
        }

    }

    /**
     * Add for CommonModelFile
     * @param CommonModelFile
     * @return
     */
    @ApiOperation(value="Create CommonModelFile", notes="According to CommonModelFile to create")
    @ApiImplicitParam(name = "CommonModelFile", value = "Detail entity CommonModelFile", required = true, dataType = "CommonModelFile")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse create(@Valid @RequestBody CommonModelFile CommonModelFile) {
        int result = CommonModelFileService.createCommonModelFile(CommonModelFile);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            NullAwareBeanUtilsBean.removeRelations(CommonModelFile);
            return baseManager.composeSuccessBaseResponse(CommonModelFile);
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Modify CommonModelFile information according to the id
     * @param CommonModelFile
     * @return
     */
    @ApiOperation(value="update CommonModelFile information ", notes="According to url id to update CommonModelFile information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "CommonModelFileID", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "CommonModelFile", value = "entity CommonModelFile", required = true, dataType = "CommonModelFile")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public BaseResponse update(@PathVariable("id")Integer id, @Valid @RequestBody CommonModelFile CommonModelFile) {

        int result = CommonModelFileService.updateCommonModelFile(CommonModelFile,id);
        if(result == Constants.RETURN_STATUS_SUCCESS){

            return baseManager.composeSuccessBaseResponse(CommonModelFileService.findById(id));
        }else{
            return baseManager.composeDBFailResponse();
        }

    }


    /**
     * Find CommonModelFile according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Find CommonModelFile", notes="According to url id to find CommonModelFile")
    @ApiImplicitParam(name = "id", value = "CommonModelFile ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CommonModelFile> get(@PathVariable("id")Integer id) {
        CommonModelFile CommonModelFile = CommonModelFileService.findById(id);
        if(CommonModelFile==null){
            return baseManager.composeDBFailResponse();
        }else{
            return baseManager.composeSuccessBaseResponse(CommonModelFile);
        }

    }


    /**
     * Delete CommonModelFile according to id
     * @param id
     * @return
     */
    @ApiOperation(value="Delete CommonModelFile", notes="According to id to delete CommonModelFile")
    @ApiImplicitParam(name = "id", value = "CommonModelFile ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id")Integer id) {

        int result = CommonModelFileService.deleteCommonModelFile(id);
        if(result == Constants.RETURN_STATUS_SUCCESS){
            return baseManager.composeCommonSuccessResponse();
        }else{
            return baseManager.composeDBFailResponse();
        }
    }
}