package org.nvijaykarthik.fccclient.actionservice.domain;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FccPeriod {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm",timezone = "Asia/Kolkata")
    private Date startdatetime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm",timezone = "Asia/Kolkata")
    private Date enddatetime;
    private String status;


    public Date getStartdatetime() {
        return this.startdatetime;
    }

    public void setStartdatetime(Date startdatetime) {
        this.startdatetime = startdatetime;
    }

    public Date getEnddatetime() {
        return this.enddatetime;
    }

    public void setEnddatetime(Date enddatetime) {
        this.enddatetime = enddatetime;
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
            " startdatetime='" + getStartdatetime() + "'" +
            ", enddatetime='" + getEnddatetime() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FccPeriod)) {
            return false;
        }
        FccPeriod fccPeriod = (FccPeriod) o;
        return Objects.equals(startdatetime, fccPeriod.startdatetime) && Objects.equals(enddatetime, fccPeriod.enddatetime) && Objects.equals(status, fccPeriod.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startdatetime, enddatetime, status);
    }
    
}
