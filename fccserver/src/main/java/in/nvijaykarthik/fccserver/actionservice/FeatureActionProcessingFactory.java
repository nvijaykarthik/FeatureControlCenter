package in.nvijaykarthik.fccserver.actionservice;

import java.util.HashMap;
import java.util.Map;

import in.nvijaykarthik.fccserver.entity.FeatureActivationConfig;

public class FeatureActionProcessingFactory {

    private Map<String,FCCActionProcessor> actionMap= new HashMap<>();

    public FeatureActionProcessingFactory(){
        actionMap.put("always",new FccAlwaysActionProcessor());
        actionMap.put("period",new FccPeriodActionProcessor());
        actionMap.put("scheduled",new FccScheduledActionProcessor());
        actionMap.put("ond-traffic",new FccOnDemandTrafficActionProcessor());
        actionMap.put("ond-priority",new FccOnDemandPriorityActionProcessor());
    }

    public boolean process(FeatureActivationConfig config) {
        boolean activeFlag=false;
        FCCActionProcessor processor=actionMap.get(config.getAction());
        activeFlag=processor.processAction(config.getConfiguration());
        return activeFlag;
    }
    
}
