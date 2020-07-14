package com.dewen.project.service.impl;

import java.util.Optional;

import com.dewen.project.constants.Constants;
import com.dewen.project.repository.CompanyWasteRepository;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import com.dewen.project.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dewen.project.domain.CompanyWaste;
import com.dewen.project.service.ICompanyWasteService;

/**
 * company_waste
 * <p>Description: CompanyWaste ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Service
public class CompanyWasteService implements ICompanyWasteService {

    @Autowired
    private CompanyWasteRepository CompanyWasteRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCompanyWaste(CompanyWaste CompanyWaste) {
        queryFk(CompanyWaste);
        CompanyWasteRepository.save(CompanyWaste);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCompanyWaste(CompanyWaste CompanyWaste,Integer id) {
        queryFk(CompanyWaste);
        Optional<CompanyWaste> CompanyWasteRes= CompanyWasteRepository.findById(id);
        if(CompanyWasteRes.isPresent()){
            CompanyWaste = NullAwareBeanUtilsBean.copyExculdeList(CompanyWasteRes.get(), CompanyWaste);
            CompanyWasteRepository.save(CompanyWaste);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CompanyWaste
     */
    private void queryFk(CompanyWaste CompanyWaste) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCompanyWaste(Integer id) {
        Optional<CompanyWaste> CompanyWaste= CompanyWasteRepository.findById(id);
        if(CompanyWaste.isPresent()){
            CompanyWasteRepository.deleteById(id);
            Optional<CompanyWaste> CompanyWasteRes= CompanyWasteRepository.findById(id);
            if(CompanyWasteRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CompanyWaste findById(Integer id) {
        Optional<CompanyWaste >  CompanyWaste = CompanyWasteRepository.findById(id);
        if(CompanyWaste.isPresent()){
            return CompanyWaste.get();
        }else{
            return null;
        }

        //return CompanyWasteRepository.findOne(id);
    }

    @Override
    public Page<CompanyWaste> list(CompanyWaste CompanyWaste,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CompanyWaste> CompanyWastePages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CompanyWaste == null){
            CompanyWastePages = CompanyWasteRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CompanyWaste> example = Example.of(CompanyWaste, matcher);
            CompanyWastePages  = CompanyWasteRepository.findAll(example, pageable);
        }

        return CompanyWastePages;
    }
}