
package com.dewen.project.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:</p>
 * <p>Description: CommonFileSystem Domain  文件系统表</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 * @author shenjl
 * @version 1.0
 * @date 2019-07-01
 */

@Entity
@Table(name = "COMMON_FILE_SYSTEM")
public class CommonFileSystem implements Serializable {

	/**
	 * 文件系统ID
	 * default value: null
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 10)
	private Integer id;

	/**
	 * 文件名
	 * default value: null
	 */
	@Length(max=255, message = "{commonFileSystem.fileName.length.limit}")
	@Column(name = "FILE_NAME", nullable = true, length = 255)
	private String fileName;

	/**
	 * 上传负责人
	 * default value: null
	 */
	@Length(max=45, message = "{commonFileSystem.uplManager.length.limit}")
	@Column(name = "UPL_MANAGER", nullable = true, length = 45)
	private String uplManager;

	/**
	 * 上传日期
	 * default value: null
	 */
	@Column(name = "UPL_DATE", nullable = true)
	private Date uplDate;

	/**
	 * 上传文件路径
	 * default value: null
	 */
	@Length(max=255, message = "{commonFileSystem.filePath.length.limit}")
	@NotBlank(message = "{commonFileSystem.filePath.not.null}")
	@Column(name = "FILE_PATH", nullable = false, length = 255)
	private String filePath;

	/**
	 * 文件后缀
	 * default value: null
	 */
	@Length(max=45, message = "{commonFileSystem.fileExtends.length.limit}")
	@Column(name = "FILE_EXTENDS", nullable = true, length = 45)
	private String fileExtends;

	/**
	 * 系统类型:1.workflow;2.CMDB
	 * default value: null
	 */
	@Length(max=45, message = "{commonFileSystem.systemType.length.limit}")
	@Column(name = "SYSTEM_TYPE", nullable = true, length = 45)
	private String systemType;

	/**
	 * 后续跟进:1.只存储;2.需要进一步处理
	 * default value: null
	 */
	@Column(name = "TO_BE_FOLLOW_UP", nullable = true, length = 1)
	private Integer toBeFollowUp;

	/**
	 * 模块类型:1.户外;2.户内;3.库存;4.办公;5.信息
	 * default value: null
	 */
	@Column(name = "MODULE_TYPE", nullable = true, length = 11)
	private Integer moduleType;

	/**
	 * 处理状态:1.已处理;2.未处理;3.处理失败;4.丢弃
	 * default value: null
	 */
	@Length(max=45, message = "{commonFileSystem.processStatus.length.limit}")
	@Column(name = "PROCESS_STATUS", nullable = true, length = 45)
	private String processStatus;

	/**
	 * null
	 * default value: null
	 */
	@Column(name = "EMPLOYEE_ID", nullable = true, length = 10)
	private Integer employeeId;

	/**
	 * 访问路径,不包括域名部分
	 * default value: null
	 */
	@Length(max=45, message = "{commonFileSystem.visitUri.length.limit}")
	@Column(name = "VISIT_URI", nullable = true, length = 45)
	private String visitUri;

	/**
	 * null
	 * default value: null
	 */
	@Column(name = "ENABLED", nullable = true, length = 1)
	private Integer enabled;

	/**
	 * null
	 * default value: null
	 */
	@Length(max=200, message = "{commonFileSystem.remark.length.limit}")
	@Column(name = "REMARK", nullable = true, length = 200)
	private String remark;

	/**
	 * null
	 * default value: null
	 */
	@Column(name = "CREATE_STAFF", nullable = true, length = 10)
	private Integer createStaff;

	/**
	 * null
	 * default value: null
	 */
	@Column(name = "CREATE_DATE", nullable = true)
	private Date createDate;

	/**
	 * null
	 * default value: null
	 */
	@Column(name = "MODIFY_DATE", nullable = true)
	private Date modifyDate;

	/**
	 * null
	 * default value: null
	 */
	@Column(name = "MODIFY_STAFF", nullable = true, length = 10)
	private Integer modifyStaff;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getUplManager() {
		return this.uplManager;
	}
	
	public void setUplManager(String uplManager) {
		this.uplManager = uplManager;
	}
	
	public Date getUplDate() {
		return this.uplDate;
	}
	
	public void setUplDate(Date uplDate) {
		this.uplDate = uplDate;
	}
	
	public String getFilePath() {
		return this.filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFileExtends() {
		return this.fileExtends;
	}
	
	public void setFileExtends(String fileExtends) {
		this.fileExtends = fileExtends;
	}
	
	public String getSystemType() {
		return this.systemType;
	}
	
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	
	public Integer getToBeFollowUp() {
		return this.toBeFollowUp;
	}
	
	public void setToBeFollowUp(Integer toBeFollowUp) {
		this.toBeFollowUp = toBeFollowUp;
	}
	
	public Integer getModuleType() {
		return this.moduleType;
	}
	
	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
	}
	
	public String getProcessStatus() {
		return this.processStatus;
	}
	
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	
	public Integer getEmployeeId() {
		return this.employeeId;
	}
	
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getVisitUri() {
		return this.visitUri;
	}
	
	public void setVisitUri(String visitUri) {
		this.visitUri = visitUri;
	}
	
	public Integer getEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getCreateStaff() {
		return this.createStaff;
	}
	
	public void setCreateStaff(Integer createStaff) {
		this.createStaff = createStaff;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getModifyDate() {
		return this.modifyDate;
	}
	
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public Integer getModifyStaff() {
		return this.modifyStaff;
	}
	
	public void setModifyStaff(Integer modifyStaff) {
		this.modifyStaff = modifyStaff;
	}
}