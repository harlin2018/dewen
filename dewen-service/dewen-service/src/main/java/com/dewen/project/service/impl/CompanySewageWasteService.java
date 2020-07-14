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

import com.dewen.project.domain.CompanySewageWaste;
import com.dewen.project.repository.CompanySewageWasteRepository;
import com.dewen.project.service.ICompanySewageWasteService;

/**
 * 排污种类
 * <p>Description: CompanySewageWaste ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Service
public class CompanySewageWasteService implements ICompanySewageWasteService {

    @Autowired
    private CompanySewageWasteRepository CompanySewageWasteRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCompanySewageWaste(CompanySewageWaste CompanySewageWaste) {
        queryFk(CompanySewageWaste);
        CompanySewageWasteRepository.save(CompanySewageWaste);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCompanySewageWaste(CompanySewageWaste CompanySewageWaste,Integer id) {
        queryFk(CompanySewageWaste);
        Optional<CompanySewageWaste> CompanySewageWasteRes= CompanySewageWasteRepository.findById(id);
        if(CompanySewageWasteRes.isPresent()){
            CompanySewageWaste = NullAwareBeanUtilsBean.copyExculdeList(CompanySewageWasteRes.get(), CompanySewageWaste);
            CompanySewageWasteRepository.save(CompanySewageWaste);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CompanySewageWaste
     */
    private void queryFk(CompanySewageWaste CompanySewageWaste) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCompanySewageWaste(Integer id) {
        Optional<CompanySewageWaste> CompanySewageWaste= CompanySewageWasteRepository.findById(id);
        if(CompanySewageWaste.isPresent()){
            CompanySewageWasteRepository.deleteById(id);
            Optional<CompanySewageWaste> CompanySewageWasteRes= CompanySewageWasteRepository.findById(id);
            if(CompanySewageWasteRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CompanySewageWaste findById(Integer id) {
        Optional<CompanySewageWaste >  CompanySewageWaste = CompanySewageWasteRepository.findById(id);
        if(CompanySewageWaste.isPresent()){
            return CompanySewageWaste.get();
        }else{
            return null;
        }

        //return CompanySewageWasteRepository.findOne(id);
    }

    @Override
    public Page<CompanySewageWaste> list(CompanySewageWaste CompanySewageWaste,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CompanySewageWaste> CompanySewageWastePages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CompanySewageWaste == null){
            CompanySewageWastePages = CompanySewageWasteRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CompanySewageWaste> example = Example.of(CompanySewageWaste, matcher);
            CompanySewageWastePages  = CompanySewageWasteRepository.findAll(example, pageable);
        }

        return CompanySewageWastePages;
    }
}