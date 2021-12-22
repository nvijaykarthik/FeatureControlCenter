package org.nvijaykarthik.fccclient.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class FeatureActivationConfig {

	Long id;
	String featureName;
	String action; // Always, Period,Scheduled,OND-Traffic,OND-Priority
	String configuration; // Rule configuration like Startdatetime,endatetime , cron schdule, Hits on
							// traffic JSON format for each Action
	String createdBy;
	LocalDateTime createdDate;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeatureName() {
		return this.featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getConfiguration() {
		return this.configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", featureName='" + getFeatureName() + "'" + ", action='" + getAction()
				+ "'" + ", configuration='" + getConfiguration() + "'" + ", createdBy='" + getCreatedBy() + "'"
				+ ", createdDate='" + getCreatedDate() + "'" + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof FeatureActivationConfig)) {
			return false;
		}
		FeatureActivationConfig featureActivationConfig = (FeatureActivationConfig) o;
		return Objects.equals(id, featureActivationConfig.id)
				&& Objects.equals(featureName, featureActivationConfig.featureName)
				&& Objects.equals(action, featureActivationConfig.action)
				&& Objects.equals(configuration, featureActivationConfig.configuration)
				&& Objects.equals(createdBy, featureActivationConfig.createdBy)
				&& Objects.equals(createdDate, featureActivationConfig.createdDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, featureName, action, configuration, createdBy, createdDate);
	}

}
