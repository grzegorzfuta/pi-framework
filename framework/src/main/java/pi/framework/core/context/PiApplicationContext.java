package pi.framework.core.context;

public class PiApplicationContext implements ApplicationContext {
    public PiApplicationContext(Class clazz) {

    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return null;
    }

    @Override
    public <T> T getBean(String componentName) {
        return null;
    }

}
