
package com.dewen.project.repository;

import com.dewen.project.domain.CommonFileSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * <p>Title:</p>
 * <p>Description: CommonFileSystem Repository  文件系统表</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 * @author shenjl
 * @version 1.0
 * @date 2019-07-01
 */
@Repository
public interface CommonFileSystemRepository extends JpaRepository<CommonFileSystem, Integer>, JpaSpecificationExecutor<CommonFileSystem> {


}