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

import com.dewen.project.domain.CommonRole;
import com.dewen.project.repository.CommonRoleRepository;
import com.dewen.project.service.ICommonRoleService;


/**
 * common_role
 * <p>Description: CommonRole ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Service
public class CommonRoleService implements ICommonRoleService {

    @Autowired
    private CommonRoleRepository CommonRoleRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCommonRole(CommonRole CommonRole) {
        queryFk(CommonRole);
        CommonRole.setEnabled(Constants.ENABLE);
        CommonRoleRepository.save(CommonRole);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCommonRole(CommonRole CommonRole,Integer id) {
        queryFk(CommonRole);
        Optional<CommonRole> CommonRoleRes= CommonRoleRepository.findById(id);
        if(CommonRoleRes.isPresent()){
            CommonRole = NullAwareBeanUtilsBean.copyExculdeList(CommonRoleRes.get(), CommonRole);
            CommonRoleRepository.save(CommonRole);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CommonRole
     */
    private void queryFk(CommonRole CommonRole) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCommonRole(Integer id) {
        Optional<CommonRole> CommonRole= CommonRoleRepository.findById(id);
        if(CommonRole.isPresent()){
            CommonRoleRepository.deleteById(id);
            Optional<CommonRole> CommonRoleRes= CommonRoleRepository.findById(id);
            if(CommonRoleRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CommonRole findById(Integer id) {
        Optional<CommonRole >  CommonRole = CommonRoleRepository.findById(id);
        if(CommonRole.isPresent()){
            return CommonRole.get();
        }else{
            return null;
        }

        //return CommonRoleRepository.findOne(id);
    }

    @Override
    public Page<CommonRole> list(CommonRole CommonRole,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CommonRole> CommonRolePages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CommonRole == null){
            CommonRolePages = CommonRoleRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CommonRole> example = Example.of(CommonRole, matcher);
            CommonRolePages  = CommonRoleRepository.findAll(example, pageable);
        }

        return CommonRolePages;
    }
}