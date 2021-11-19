package in.nvijaykarthik.fccserver.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FeatureServiceXref {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    @Column
    String featureName;

    @Column
    String serviceName;

    @Column
    String createdBy;

    @Column
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

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
        return "{" +
            " id='" + getId() + "'" +
            ", featureName='" + getFeatureName() + "'" +
            ", serviceName='" + getServiceName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FeatureServiceXref)) {
            return false;
        }
        FeatureServiceXref featureServiceXref = (FeatureServiceXref) o;
        return Objects.equals(id, featureServiceXref.id) && Objects.equals(featureName, featureServiceXref.featureName) && Objects.equals(serviceName, featureServiceXref.serviceName) && Objects.equals(createdBy, featureServiceXref.createdBy) && Objects.equals(createdDate, featureServiceXref.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, featureName, serviceName, createdBy, createdDate);
    }

}
