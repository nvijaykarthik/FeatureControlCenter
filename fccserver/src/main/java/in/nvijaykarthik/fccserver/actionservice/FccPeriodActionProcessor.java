package in.nvijaykarthik.fccserver.actionservice;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.nvijaykarthik.fccserver.actionservice.domain.FccPeriod;

public class FccPeriodActionProcessor implements FCCActionProcessor {

    @Override
    public boolean processAction(String json) {
        ObjectMapper mapper= new ObjectMapper();
        boolean activeFlag=false;
        try {
            //{"startdatetime":"yyyy-MM-dd HH:mm:ss","enddatetime":"yyyy-MM-dd HH:mm:ss","status":"Y"}
            FccPeriod period=mapper.readValue(json, FccPeriod.class);
            System.out.println(period);
            Date now=new Date();
            if (period.getStartdatetime().before(now) && period.getEnddatetime().after(now)){
                if(period.getStatus().equalsIgnoreCase("Y")){
                    activeFlag=true;
                }else if(period.getStatus().equalsIgnoreCase("N")){
                    activeFlag=false;
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return activeFlag;
    }
    
}
