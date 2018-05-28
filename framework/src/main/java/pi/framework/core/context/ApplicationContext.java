package pi.framework.core.context;

public interface ApplicationContext {
    <T> T getBean(Class<T> clazz);
    <T> T getBean(String componentName);
}
