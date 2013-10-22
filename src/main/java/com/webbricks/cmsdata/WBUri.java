package com.webbricks.cmsdata;
import com.webbricks.datautility.*;

import java.io.Serializable;
import java.util.Date;

public class WBUri implements Serializable {
	public final static int RESOURCE_TYPE_TEXT = 1;
	public final static int RESOURCE_TYPE_FILE = 2;
	public final static int RESOURCE_TYPE_URL_CONTROLLER = 3;
	

	@AdminFieldKey
	private Long key;

	@AdminFieldStore
	private Integer version;
	
	@AdminFieldStore
	private Integer enabled;

	@AdminFieldStore
	private String uri;
	
	@AdminFieldStore
	private Date lastModified;
	
	@AdminFieldStore
	private String httpOperation;
	
	@AdminFieldStore
	private String controllerClass;
	
	@AdminFieldStore
	private Integer resourceType;
	
	@AdminFieldStore
	private String resourceExternalKey;
			
	@AdminFieldStore
	private String externalKey;
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getHttpOperation() {
		return httpOperation;
	}

	public void setHttpOperation(String httpOperation) {
		this.httpOperation = httpOperation;
	}

	public String getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(String controllerClass) {
		this.controllerClass = controllerClass;
	}
	
	public String getExternalKey() {
		return externalKey;
	}

	public void setExternalKey(String externalKey) {
		this.externalKey = externalKey;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceExternalKey() {
		return resourceExternalKey;
	}

	public void setResourceExternalKey(String resourceExternalKey) {
		this.resourceExternalKey = resourceExternalKey;
	}
	
	
}
