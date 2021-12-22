package org.nvijaykarthik.fccclient.actionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.nvijaykarthik.fccclient.actionservice.domain.FccAlways;

public class FccAlwaysActionProcessor implements FCCActionProcessor{

    @Override
    public boolean processAction(String json) {
        ObjectMapper mapper= new ObjectMapper();
        boolean activeFlag=false;
        try {
            //{"status":"Y"} or {"status":"N"}
            FccAlways always=mapper.readValue(json, FccAlways.class);
            if(always.getStatus().equalsIgnoreCase("Y")){
                activeFlag=true;
            }else if(always.getStatus().equalsIgnoreCase("N")){
                activeFlag=false;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return activeFlag;
    }
    
}
