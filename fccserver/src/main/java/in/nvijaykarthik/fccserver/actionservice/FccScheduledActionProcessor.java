package in.nvijaykarthik.fccserver.actionservice;

import java.time.ZonedDateTime;

import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.nvijaykarthik.fccserver.actionservice.domain.FccScheduled;

public class FccScheduledActionProcessor implements FCCActionProcessor {

    private CronParser parser;

    public FccScheduledActionProcessor() {
        CronDefinition cd = CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);
        this.parser = new CronParser(cd);
    }

    // {"cron":"0 * 19-20 ? * *","status":"Y"}
    @Override
    public boolean processAction(String json) {
        ObjectMapper mapper= new ObjectMapper();
        boolean activeFlag=false;
        try {
            FccScheduled scheduled = mapper.readValue(json, FccScheduled.class);
            System.out.println(scheduled);
            ZonedDateTime now = ZonedDateTime.now();
            ExecutionTime executionTime = ExecutionTime.forCron(parser.parse(scheduled.getCron()));
            boolean flag = executionTime.isMatch(now);
            if(flag){
            if(scheduled.getStatus().equalsIgnoreCase("Y")){
                activeFlag=true;
            }else if(scheduled.getStatus().equalsIgnoreCase("N")){
                activeFlag=false;
            }
        }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return activeFlag;
    }

}
