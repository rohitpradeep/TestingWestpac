package common;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContextImp implements ScenarioContext {
    private Map<String,Object> context = new HashMap<>();


    @Override
    public void putContext(String key, Object obj) {
        context.put(key, obj);
    }

    @Override
    public Object getContext(String key) {
        return context.get(key);
    }
}
