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

import com.dewen.project.domain.CommonUserRoleRelationship;
import com.dewen.project.repository.CommonUserRoleRelationshipRepository;
import com.dewen.project.service.ICommonUserRoleRelationshipService;

/**
 * common_user_role_relationship
 * <p>Description: CommonUserRoleRelationship ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Service
public class CommonUserRoleRelationshipService implements ICommonUserRoleRelationshipService {

    @Autowired
    private CommonUserRoleRelationshipRepository CommonUserRoleRelationshipRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCommonUserRoleRelationship(CommonUserRoleRelationship CommonUserRoleRelationship) {
        queryFk(CommonUserRoleRelationship);
        CommonUserRoleRelationshipRepository.save(CommonUserRoleRelationship);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCommonUserRoleRelationship(CommonUserRoleRelationship CommonUserRoleRelationship,Integer id) {
        queryFk(CommonUserRoleRelationship);
        Optional<CommonUserRoleRelationship> CommonUserRoleRelationshipRes= CommonUserRoleRelationshipRepository.findById(id);
        if(CommonUserRoleRelationshipRes.isPresent()){
            CommonUserRoleRelationship = NullAwareBeanUtilsBean.copyExculdeList(CommonUserRoleRelationshipRes.get(), CommonUserRoleRelationship);
            CommonUserRoleRelationshipRepository.save(CommonUserRoleRelationship);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CommonUserRoleRelationship
     */
    private void queryFk(CommonUserRoleRelationship CommonUserRoleRelationship) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCommonUserRoleRelationship(Integer id) {
        Optional<CommonUserRoleRelationship> CommonUserRoleRelationship= CommonUserRoleRelationshipRepository.findById(id);
        if(CommonUserRoleRelationship.isPresent()){
            CommonUserRoleRelationshipRepository.deleteById(id);
            Optional<CommonUserRoleRelationship> CommonUserRoleRelationshipRes= CommonUserRoleRelationshipRepository.findById(id);
            if(CommonUserRoleRelationshipRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CommonUserRoleRelationship findById(Integer id) {
        Optional<CommonUserRoleRelationship >  CommonUserRoleRelationship = CommonUserRoleRelationshipRepository.findById(id);
        if(CommonUserRoleRelationship.isPresent()){
            return CommonUserRoleRelationship.get();
        }else{
            return null;
        }

        //return CommonUserRoleRelationshipRepository.findOne(id);
    }

    @Override
    public Page<CommonUserRoleRelationship> list(CommonUserRoleRelationship CommonUserRoleRelationship,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CommonUserRoleRelationship> CommonUserRoleRelationshipPages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CommonUserRoleRelationship == null){
            CommonUserRoleRelationshipPages = CommonUserRoleRelationshipRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CommonUserRoleRelationship> example = Example.of(CommonUserRoleRelationship, matcher);
            CommonUserRoleRelationshipPages  = CommonUserRoleRelationshipRepository.findAll(example, pageable);
        }

        return CommonUserRoleRelationshipPages;
    }
}