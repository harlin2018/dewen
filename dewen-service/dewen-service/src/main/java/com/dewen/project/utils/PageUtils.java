package com.dewen.project.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Paging;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

public class PageUtils {


    public static Pageable pageable(Integer pageNumber, Integer pageSize){
        return pageable(pageNumber,pageSize,null);
    }

    public static <T> Page<T> paging(List<T> content, Integer pageNumber, Integer pageSize, Long total) {
        return Paging.of(content, pageable(pageNumber, pageSize, null), total);
    }

    public static <T> Page<T> paging(List<T> content, Integer pageNumber, Integer pageSize, Long total, String sorts) {
        return Paging.of(content, pageable(pageNumber, pageSize, sorts), total);
    }

    /**
     * 构建分页对象
     *
     * @param pageNumber
     * @param pageSize
     * @param sorts      排序字段,格式：id|asc,createDate|desc
     * @return
     */
    public static Pageable pageable(Integer pageNumber, Integer pageSize, String sorts) {
        if (!StringUtils.hasText(sorts) || !sorts.contains("|")) {
            return PageRequest.of(pageNumber, pageSize);
        }
        String[] orders = StringUtils.commaDelimitedListToStringArray(sorts);
        Sort sort = null;
        for (String order : orders) {
            String[] fieldAndDirect = StringUtils.delimitedListToStringArray(order, "|");
            if (fieldAndDirect.length != 2) {
                continue;
            }
            Optional<Sort.Direction> optional = Sort.Direction.fromOptionalString(fieldAndDirect[1]);
            if (optional.isPresent()) {
                if (sort == null) {
                    sort = Sort.by(optional.get(), fieldAndDirect[0]);
                } else {
                    sort = sort.and(Sort.by(optional.get(), fieldAndDirect[0]));
                }
            }
        }
        Pageable pageable = null;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return pageable;
    }


//    public Specification specification(EpfPort epfPort) {
//        return new Specification<EpfPort>() {
//            @Override
//            public Predicate toPredicate(Root<EpfPort> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicates = new ArrayList<Predicate>();
//                //设置查询条件
//                if (StringUtils.hasText(epfPort.getProjectName())) {
//                    predicates.add(criteriaBuilder.equal(root.get("projectName"), epfPort.getProjectName()));
//                }
//
//
//                if (predicates.isEmpty()) {
//                    return null;
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//        };
//    }
}
