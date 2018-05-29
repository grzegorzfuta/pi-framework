package pi.framework.core.scanner;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface ClassScanner {

    enum Scanner {
        ANNOTATION_SCANNER,
        XML_SCANNER
    }

    String getPackage();
    Set<Class<?>> getAllBeanClasses();
    Set<Class<?>> getAllAnnotatedClasses(Annotation... annotation);
    void scan();

}
