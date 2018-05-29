package pi.framework.core.bean;

import java.util.Objects;

import static pi.framework.core.bean.BeanScope.SINGLETON;


public class BeanDefinitionInfo {
    private String name;
    private String className;
    private BeanScope scope = SINGLETON;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public BeanScope getScope() {
        return scope;
    }

    public void setScope(BeanScope scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) return false;
        BeanDefinitionInfo that = (BeanDefinitionInfo) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(className, that.className) &&
                scope == that.scope;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, className, scope);
    }
}
