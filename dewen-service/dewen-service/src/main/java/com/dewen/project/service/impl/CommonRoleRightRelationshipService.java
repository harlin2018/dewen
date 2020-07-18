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

import com.dewen.project.domain.CommonRoleRightRelationship;
import com.dewen.project.repository.CommonRoleRightRelationshipRepository;
import com.dewen.project.service.ICommonRoleRightRelationshipService;

/**
 * common_role_right_relationship
 * <p>Description: CommonRoleRightRelationship ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-18
 */
@Service
public class CommonRoleRightRelationshipService implements ICommonRoleRightRelationshipService {

    @Autowired
    private CommonRoleRightRelationshipRepository CommonRoleRightRelationshipRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCommonRoleRightRelationship(CommonRoleRightRelationship CommonRoleRightRelationship) {
        queryFk(CommonRoleRightRelationship);
        CommonRoleRightRelationshipRepository.save(CommonRoleRightRelationship);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCommonRoleRightRelationship(CommonRoleRightRelationship CommonRoleRightRelationship,Integer id) {
        queryFk(CommonRoleRightRelationship);
        Optional<CommonRoleRightRelationship> CommonRoleRightRelationshipRes= CommonRoleRightRelationshipRepository.findById(id);
        if(CommonRoleRightRelationshipRes.isPresent()){
            CommonRoleRightRelationship = NullAwareBeanUtilsBean.copyExculdeList(CommonRoleRightRelationshipRes.get(), CommonRoleRightRelationship);
            CommonRoleRightRelationshipRepository.save(CommonRoleRightRelationship);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CommonRoleRightRelationship
     */
    private void queryFk(CommonRoleRightRelationship CommonRoleRightRelationship) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCommonRoleRightRelationship(Integer id) {
        Optional<CommonRoleRightRelationship> CommonRoleRightRelationship= CommonRoleRightRelationshipRepository.findById(id);
        if(CommonRoleRightRelationship.isPresent()){
            CommonRoleRightRelationshipRepository.deleteById(id);
            Optional<CommonRoleRightRelationship> CommonRoleRightRelationshipRes= CommonRoleRightRelationshipRepository.findById(id);
            if(CommonRoleRightRelationshipRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CommonRoleRightRelationship findById(Integer id) {
        Optional<CommonRoleRightRelationship >  CommonRoleRightRelationship = CommonRoleRightRelationshipRepository.findById(id);
        if(CommonRoleRightRelationship.isPresent()){
            return CommonRoleRightRelationship.get();
        }else{
            return null;
        }

        //return CommonRoleRightRelationshipRepository.findOne(id);
    }

    @Override
    public Page<CommonRoleRightRelationship> list(CommonRoleRightRelationship CommonRoleRightRelationship,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CommonRoleRightRelationship> CommonRoleRightRelationshipPages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CommonRoleRightRelationship == null){
            CommonRoleRightRelationshipPages = CommonRoleRightRelationshipRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CommonRoleRightRelationship> example = Example.of(CommonRoleRightRelationship, matcher);
            CommonRoleRightRelationshipPages  = CommonRoleRightRelationshipRepository.findAll(example, pageable);
        }

        return CommonRoleRightRelationshipPages;
    }
}