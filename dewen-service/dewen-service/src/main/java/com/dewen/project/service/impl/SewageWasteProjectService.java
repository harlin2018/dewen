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

import com.dewen.project.domain.SewageWasteProject;
import com.dewen.project.repository.SewageWasteProjectRepository;
import com.dewen.project.service.ISewageWasteProjectService;

/**
 * 排污种类检测项目
 * <p>Description: SewageWasteProject ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Service
public class SewageWasteProjectService implements ISewageWasteProjectService {

    @Autowired
    private SewageWasteProjectRepository SewageWasteProjectRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createSewageWasteProject(SewageWasteProject SewageWasteProject) {
        queryFk(SewageWasteProject);
        SewageWasteProjectRepository.save(SewageWasteProject);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateSewageWasteProject(SewageWasteProject SewageWasteProject,Integer id) {
        queryFk(SewageWasteProject);
        Optional<SewageWasteProject> SewageWasteProjectRes= SewageWasteProjectRepository.findById(id);
        if(SewageWasteProjectRes.isPresent()){
            SewageWasteProject = NullAwareBeanUtilsBean.copyExculdeList(SewageWasteProjectRes.get(), SewageWasteProject);
            SewageWasteProjectRepository.save(SewageWasteProject);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param SewageWasteProject
     */
    private void queryFk(SewageWasteProject SewageWasteProject) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteSewageWasteProject(Integer id) {
        Optional<SewageWasteProject> SewageWasteProject= SewageWasteProjectRepository.findById(id);
        if(SewageWasteProject.isPresent()){
            SewageWasteProjectRepository.deleteById(id);
            Optional<SewageWasteProject> SewageWasteProjectRes= SewageWasteProjectRepository.findById(id);
            if(SewageWasteProjectRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public SewageWasteProject findById(Integer id) {
        Optional<SewageWasteProject >  SewageWasteProject = SewageWasteProjectRepository.findById(id);
        if(SewageWasteProject.isPresent()){
            return SewageWasteProject.get();
        }else{
            return null;
        }

        //return SewageWasteProjectRepository.findOne(id);
    }

    @Override
    public Page<SewageWasteProject> list(SewageWasteProject SewageWasteProject,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<SewageWasteProject> SewageWasteProjectPages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(SewageWasteProject == null){
            SewageWasteProjectPages = SewageWasteProjectRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<SewageWasteProject> example = Example.of(SewageWasteProject, matcher);
            SewageWasteProjectPages  = SewageWasteProjectRepository.findAll(example, pageable);
        }

        return SewageWasteProjectPages;
    }
}