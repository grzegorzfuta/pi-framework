package pi.framework.core.scanner;

import java.lang.annotation.Annotation;
import java.util.Set;

public class NotImplementedScanner implements ClassScanner {

    NotImplementedScanner(String parameter) {
        // noting to do ;/
    }

    @Override
    public String getPackage() {
        throw new UnsupportedOperationException("Method not implemented yet and never will be");
    }

    @Override
    public Set<Class<?>> getAllBeanClasses() {
        throw new UnsupportedOperationException("Method not implemented yet and never will be");
    }

    @Override
    public Set<Class<?>> getAllAnnotatedClasses(Annotation... annotation) {
        throw new UnsupportedOperationException("Method not implemented yet and never will be");
    }

    @Override
    public void scan() {
        throw new UnsupportedOperationException("Method not implemented yet and never will be");
    }
}
