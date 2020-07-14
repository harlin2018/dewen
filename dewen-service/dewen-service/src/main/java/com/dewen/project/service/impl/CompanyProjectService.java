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

import com.dewen.project.domain.CompanyProject;
import com.dewen.project.repository.CompanyProjectRepository;
import com.dewen.project.service.ICompanyProjectService;

/**
 * 主要产品生产情况表
 * <p>Description: CompanyProject ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Service
public class CompanyProjectService implements ICompanyProjectService {

    @Autowired
    private CompanyProjectRepository CompanyProjectRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCompanyProject(CompanyProject CompanyProject) {
        queryFk(CompanyProject);
        CompanyProjectRepository.save(CompanyProject);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCompanyProject(CompanyProject CompanyProject,Integer id) {
        queryFk(CompanyProject);
        Optional<CompanyProject> CompanyProjectRes= CompanyProjectRepository.findById(id);
        if(CompanyProjectRes.isPresent()){
            CompanyProject = NullAwareBeanUtilsBean.copyExculdeList(CompanyProjectRes.get(), CompanyProject);
            CompanyProjectRepository.save(CompanyProject);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CompanyProject
     */
    private void queryFk(CompanyProject CompanyProject) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCompanyProject(Integer id) {
        Optional<CompanyProject> CompanyProject= CompanyProjectRepository.findById(id);
        if(CompanyProject.isPresent()){
            CompanyProjectRepository.deleteById(id);
            Optional<CompanyProject> CompanyProjectRes= CompanyProjectRepository.findById(id);
            if(CompanyProjectRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CompanyProject findById(Integer id) {
        Optional<CompanyProject >  CompanyProject = CompanyProjectRepository.findById(id);
        if(CompanyProject.isPresent()){
            return CompanyProject.get();
        }else{
            return null;
        }

        //return CompanyProjectRepository.findOne(id);
    }

    @Override
    public Page<CompanyProject> list(CompanyProject CompanyProject,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CompanyProject> CompanyProjectPages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CompanyProject == null){
            CompanyProjectPages = CompanyProjectRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CompanyProject> example = Example.of(CompanyProject, matcher);
            CompanyProjectPages  = CompanyProjectRepository.findAll(example, pageable);
        }

        return CompanyProjectPages;
    }
}