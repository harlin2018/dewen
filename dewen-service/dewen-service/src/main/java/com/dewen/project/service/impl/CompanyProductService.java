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

import com.dewen.project.domain.CompanyProduct;
import com.dewen.project.repository.CompanyProductRepository;
import com.dewen.project.service.ICompanyProductService;

/**
 * 主要产品生产情况表
 * <p>Description: CompanyProduct ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Service
public class CompanyProductService implements ICompanyProductService {

    @Autowired
    private CompanyProductRepository CompanyProductRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCompanyProduct(CompanyProduct CompanyProduct) {
        queryFk(CompanyProduct);
        CompanyProductRepository.save(CompanyProduct);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCompanyProduct(CompanyProduct CompanyProduct,Integer id) {
        queryFk(CompanyProduct);
        Optional<CompanyProduct> CompanyProductRes= CompanyProductRepository.findById(id);
        if(CompanyProductRes.isPresent()){
            CompanyProduct = NullAwareBeanUtilsBean.copyExculdeList(CompanyProductRes.get(), CompanyProduct);
            CompanyProductRepository.save(CompanyProduct);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CompanyProduct
     */
    private void queryFk(CompanyProduct CompanyProduct) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCompanyProduct(Integer id) {
        Optional<CompanyProduct> CompanyProduct= CompanyProductRepository.findById(id);
        if(CompanyProduct.isPresent()){
            CompanyProductRepository.deleteById(id);
            Optional<CompanyProduct> CompanyProductRes= CompanyProductRepository.findById(id);
            if(CompanyProductRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CompanyProduct findById(Integer id) {
        Optional<CompanyProduct >  CompanyProduct = CompanyProductRepository.findById(id);
        if(CompanyProduct.isPresent()){
            return CompanyProduct.get();
        }else{
            return null;
        }

        //return CompanyProductRepository.findOne(id);
    }

    @Override
    public Page<CompanyProduct> list(CompanyProduct CompanyProduct,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CompanyProduct> CompanyProductPages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CompanyProduct == null){
            CompanyProductPages = CompanyProductRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CompanyProduct> example = Example.of(CompanyProduct, matcher);
            CompanyProductPages  = CompanyProductRepository.findAll(example, pageable);
        }

        return CompanyProductPages;
    }
}