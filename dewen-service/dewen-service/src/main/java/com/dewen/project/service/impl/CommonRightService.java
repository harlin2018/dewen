package com.dewen.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CommonRole;
import com.dewen.project.domain.CommonRoleRightRelationship;
import com.dewen.project.domain.DTO.CommonRightDTO;
import com.dewen.project.domain.DTO.RoleAssignRightRequest;
import com.dewen.project.domain.support.CommonRightSupport;
import com.dewen.project.repository.CommonRightRepository;
import com.dewen.project.repository.CommonRoleRepository;
import com.dewen.project.repository.CommonRoleRightRelationshipRepository;
import com.dewen.project.utils.ListExtraUtils;
import com.dewen.project.utils.MessageUtils;
import com.dewen.project.utils.PageUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dewen.project.domain.CommonRight;
import com.dewen.project.service.ICommonRightService;

import javax.persistence.criteria.Predicate;

/**
 * common_right
 * <p>Description: CommonRight ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-18
 */
@Service
@Slf4j
public class CommonRightService implements ICommonRightService {

    @Autowired
    private CommonRightRepository commonRightRepository;
    @Autowired
    private CommonRoleRightRelationshipRepository commonRoleRightRelationshipRepository;
    @Autowired
    private CommonRoleRepository commonRoleRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCommonRight(CommonRight commonRight) {
        if (commonRight.getParentId() == null){
            commonRight.setParentId(Constants.RIGHT_ROOT_PARENT);
        }
        commonRight.setEnabled(Constants.ENABLE);
        commonRight.setCreateDate(new Date());
        commonRight.setModifyDate(new Date());
        queryFk(commonRight);
        commonRightRepository.save(commonRight);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCommonRight(CommonRight commonRight,Integer id) {
        queryFk(commonRight);
        Optional<CommonRight> commonRightRes= commonRightRepository.findById(id);
        if(commonRightRes.isPresent()){
            commonRight.setId(id);
            CommonRight result = commonRightRes.get();
            commonRight.setModifyDate(new Date());
            commonRight.setCommonRoleRightRelationship(result.getCommonRoleRightRelationship());
            commonRightRepository.save(commonRight);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }
    }

    /**
     * 处理外键对象
     * @param commonRight
     */
    private void queryFk(CommonRight commonRight) {
        CommonRight right = commonRightRepository.findByParentIdAndRightCodeAndEnabled(
                commonRight.getParentId(), commonRight.getRightCode(), Constants.ENABLE);

        MessageUtils.isTrue(Objects.isNull(right) || Objects.equals(right.getId(), commonRight.getId()),
                "同级的权限编码[{}]已存在", commonRight.getRightCode());
    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCommonRight(Integer id) {
        Optional<CommonRight> CommonRight= commonRightRepository.findById(id);
        if(CommonRight.isPresent()){
            commonRightRepository.deleteById(id);
            Optional<CommonRight> CommonRightRes= commonRightRepository.findById(id);
            if(CommonRightRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CommonRight findById(Integer id) {
        Optional<CommonRight >  CommonRight = commonRightRepository.findById(id);
        if(CommonRight.isPresent()){
            return CommonRight.get();
        }else{
            return null;
        }

        //return commonRightRepository.findOne(id);
    }

    @Override
    public Page<CommonRight> list(CommonRight CommonRight,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CommonRight> CommonRightPages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CommonRight == null){
            CommonRightPages = commonRightRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CommonRight> example = Example.of(CommonRight, matcher);
            CommonRightPages  = commonRightRepository.findAll(example, pageable);
        }

        return CommonRightPages;
    }
    @Override
    public List<CommonRightDTO> listAll(CommonRight commonRight) {
        return listAll(commonRight, true);
    }

    @Override
    public List<CommonRightDTO> listByRole(Integer roleId) {
        List<CommonRight> all = commonRightRepository.findByEnabled(Constants.ENABLE);
        List<CommonRightDTO> rights = ListExtraUtils.toList(all, CommonRightDTO.class);

        Map<Integer, CommonRoleRightRelationship> rightByRoleMap = commonRoleRightRelationshipRepository.findRightByRoleMap(roleId);

        List<CommonRightDTO> rightDTOS = CommonRightSupport.toTree(rights, rightByRoleMap);


        return rightDTOS;
    }

    private List<CommonRightDTO> listAll(CommonRight commonRight, boolean rightToTree) {

        //add sorts to query
        Page<CommonRight> commonRightPages =null;
        //Pageable

        if(commonRight == null){
            commonRight = new CommonRight();
        }
        commonRight.setEnabled(Constants.ENABLE);
        //create matcher ,if need ,please modify here
        CommonRight finalCommonRight = commonRight;
        Specification<CommonRight> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateAndList = new ArrayList<Predicate>();
            // 根据name,assetNo 模糊查询
            List<Predicate> predicateList = Lists.newLinkedList();
            if (StringUtils.isNotBlank(finalCommonRight.getResourceType())) {
                predicateAndList.add(criteriaBuilder.equal(root.get("resourceType"), finalCommonRight.getResourceType()));
            }
            if (StringUtils.isNotEmpty(finalCommonRight.getSearchKeyWord())) {
                predicateList.add(criteriaBuilder.like(root.get("id").as(String.class), "%" + finalCommonRight.getSearchKeyWord() + "%"));
                predicateList.add(criteriaBuilder.like(root.get("rightName"), "%" + finalCommonRight.getSearchKeyWord() + "%"));
                predicateList.add(criteriaBuilder.like(root.get("sysType"), "%" + finalCommonRight.getSearchKeyWord() + "%"));
                predicateList.add(criteriaBuilder.like(root.get("rightCode"), "%" + finalCommonRight.getSearchKeyWord() + "%"));
                predicateList.add(criteriaBuilder.like(root.get("rightUrl"), "%" + finalCommonRight.getSearchKeyWord() + "%"));
                if (predicateAndList.size() > 0) {
                    return criteriaQuery.where(criteriaBuilder.and(predicateAndList.toArray(new Predicate[predicateAndList.size() - 1])), criteriaBuilder.or(predicateList.toArray(new Predicate[predicateList.size() - 1]))).getRestriction();
                } else {
                    return criteriaQuery.where(criteriaBuilder.or(predicateList.toArray(new Predicate[predicateList.size() - 1]))).getRestriction();
                }
            } else {
                if (predicateAndList.size() > 0) {
                    return criteriaQuery.where(criteriaBuilder.and(predicateAndList.toArray(new Predicate[predicateAndList.size() - 1]))).getRestriction();
                }
                return criteriaQuery.getRestriction();
            }
        };

        List<CommonRight> all = commonRightRepository.findAll(specification);
        List<CommonRightDTO> rights = ListExtraUtils.toList(all, CommonRightDTO.class);
        if (!rightToTree) {
            return rights;
        }
        return CommonRightSupport.toTree(rights);
    }


    @Override
    public boolean grant(RoleAssignRightRequest request) {
        Map<Integer, String> rightMap = request.getRightIds();
        Optional<CommonRole> role = commonRoleRepository.findById(request.getRoleId());
        if (!role.isPresent()) {
            log.error("角色（{}）不存在", request.getRoleId());
            return false;
        }
        List<CommonRoleRightRelationship> commonRoleRightRelationshipAdds = new ArrayList<>();
        List<CommonRoleRightRelationship> commonRoleRightRelationshipDeletes = new ArrayList<>();
        List<CommonRoleRightRelationship> commonRoleRightRelationships = role.get().getCommonRoleRightRelationship();
        // 找出要删除的，和要添加的
        for (int i = commonRoleRightRelationships.size() - 1; i >= 0; i--) {
            CommonRoleRightRelationship commonRoleRightRelationship = commonRoleRightRelationships.get(i);
            Integer rightId = commonRoleRightRelationship.getCommonRight().getId();
            if (!rightMap.keySet().contains(rightId)) {
                commonRoleRightRelationshipDeletes.add(commonRoleRightRelationship);
                role.get().getCommonRoleRightRelationship().remove(commonRoleRightRelationship);
            } else {
                String rightType = rightMap.get(rightId);
                rightMap.remove(rightId);
                // 需要更新的
                if (!Objects.equals(commonRoleRightRelationship.getRightType(),rightType)) {
                    commonRoleRightRelationship.setRightType(rightType);
                    commonRoleRightRelationshipAdds.add(commonRoleRightRelationship);
                }
            }
        }
        // 需要添加的
        CommonRoleRightRelationship commonRoleRightRelationship;
        for (Integer rightId : rightMap.keySet()) {
            Optional<CommonRight> right = commonRightRepository.findById(rightId);
            if (!right.isPresent()) {
                log.error("权限（{}）不存在", request.getRoleId());
                return false;
            }
            commonRoleRightRelationship = new CommonRoleRightRelationship();
            commonRoleRightRelationship.setCommonRole(role.get());
            commonRoleRightRelationship.setCommonRight(right.get());
            commonRoleRightRelationship.setRightType(rightMap.get(rightId));
            commonRoleRightRelationshipAdds.add(commonRoleRightRelationship);
        }

        if (CollectionUtils.isNotEmpty(commonRoleRightRelationshipDeletes)) {
            commonRoleRightRelationshipRepository.deleteAll(commonRoleRightRelationshipDeletes);
        }

        if (CollectionUtils.isNotEmpty(commonRoleRightRelationshipAdds)) {
            commonRoleRightRelationshipRepository.saveAll(commonRoleRightRelationshipAdds);
        }
        //192commonRoleRepository.save(role.get());
        return true;
    }
}