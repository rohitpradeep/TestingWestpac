package common;

public interface ScenarioContext {
    void putContext(String key, Object obj);
    Object getContext(String key);

}
