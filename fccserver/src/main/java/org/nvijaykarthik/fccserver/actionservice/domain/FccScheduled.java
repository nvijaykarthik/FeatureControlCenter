package org.nvijaykarthik.fccserver.actionservice.domain;

import java.util.Objects;

public class FccScheduled {
    String cron;
    String status;

    public String getCron() {
        return this.cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
            " cron='" + getCron() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FccScheduled)) {
            return false;
        }
        FccScheduled fccScheduled = (FccScheduled) o;
        return Objects.equals(cron, fccScheduled.cron) && Objects.equals(status, fccScheduled.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cron, status);
    }


}
