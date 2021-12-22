package org.nvijaykarthik.fccserver.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Features {

    @Id
    @Column
    String featureName;

    @Column
    String featureDescription;

    @Column
    String createdBy;

    @Column
    LocalDateTime createdDate;

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

    public String getFeatureName() {
        return this.featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureDescription() {
        return this.featureDescription;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }

    @Override
    public String toString() {
        return "{" + " featureName='" + getFeatureName() + "'" + ", featureDescription='" + getFeatureDescription()
                + "'" + ", createdBy='" + getCreatedBy() + "'" + ", createdDate='" + getCreatedDate() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Features)) {
            return false;
        }
        Features featuresEntity = (Features) o;
        return Objects.equals(featureName, featuresEntity.featureName)
                && Objects.equals(featureDescription, featuresEntity.featureDescription)
                && Objects.equals(createdBy, featuresEntity.createdBy)
                && Objects.equals(createdDate, featuresEntity.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(featureName, featureDescription, createdBy, createdDate);
    }

}
