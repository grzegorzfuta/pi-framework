package pi.framework.core.bean;

import java.util.Objects;

import static pi.framework.core.bean.BeanScope.SINGLETON;


public class BeanDefinitionInfo {
    private Class clazz;
    private Object instance;
    private String name;
    private BeanScope scope = SINGLETON;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public BeanScope getScope() {
        return scope;
    }

    public void setScope(BeanScope scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BeanDefinitionInfo that = (BeanDefinitionInfo) o;
        return Objects.equals(clazz, that.clazz) &&
                Objects.equals(instance, that.instance) &&
                Objects.equals(name, that.name) &&
                scope == that.scope;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz, instance, name, scope);
    }
}
