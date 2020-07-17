package com.dewen.project.service.impl;

import java.util.Optional;

import com.dewen.project.constants.Constants;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import com.dewen.project.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dewen.project.domain.CommonModelFile;
import com.dewen.project.repository.CommonModelFileRepository;
import com.dewen.project.service.ICommonModelFileService;

/**
 * common_model_file
 * <p>Description: CommonModelFile ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-17
 */
@Service
public class CommonModelFileService implements ICommonModelFileService {

    @Autowired
    private CommonModelFileRepository CommonModelFileRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCommonModelFile(CommonModelFile CommonModelFile) {
        queryFk(CommonModelFile);
        CommonModelFileRepository.save(CommonModelFile);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCommonModelFile(CommonModelFile CommonModelFile,Integer id) {
        queryFk(CommonModelFile);
        Optional<CommonModelFile> CommonModelFileRes= CommonModelFileRepository.findById(id);
        if(CommonModelFileRes.isPresent()){
            CommonModelFile = NullAwareBeanUtilsBean.copyExculdeList(CommonModelFileRes.get(), CommonModelFile);
            CommonModelFileRepository.save(CommonModelFile);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CommonModelFile
     */
    private void queryFk(CommonModelFile CommonModelFile) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCommonModelFile(Integer id) {
        Optional<CommonModelFile> CommonModelFile= CommonModelFileRepository.findById(id);
        if(CommonModelFile.isPresent()){
            CommonModelFileRepository.deleteById(id);
            Optional<CommonModelFile> CommonModelFileRes= CommonModelFileRepository.findById(id);
            if(CommonModelFileRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CommonModelFile findById(Integer id) {
        Optional<CommonModelFile >  CommonModelFile = CommonModelFileRepository.findById(id);
        if(CommonModelFile.isPresent()){
            return CommonModelFile.get();
        }else{
            return null;
        }

        //return CommonModelFileRepository.findOne(id);
    }

    @Override
    public Page<CommonModelFile> list(CommonModelFile CommonModelFile,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CommonModelFile> CommonModelFilePages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CommonModelFile == null){
            CommonModelFilePages = CommonModelFileRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CommonModelFile> example = Example.of(CommonModelFile, matcher);
            CommonModelFilePages  = CommonModelFileRepository.findAll(example, pageable);
        }

        return CommonModelFilePages;
    }
}