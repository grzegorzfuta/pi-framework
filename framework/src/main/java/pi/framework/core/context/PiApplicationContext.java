package pi.framework.core.context;

import pi.framework.core.bean.BeanDefinitionInfo;
import pi.framework.core.scanner.ClassScanner;
import pi.framework.core.scanner.ScannerFactory;

import java.util.HashSet;
import java.util.Set;

import static pi.framework.core.scanner.ClassScanner.Scanner.ANNOTATION_SCANNER;

public class PiApplicationContext implements ApplicationContext {

    protected Set<BeanDefinitionInfo> beanDefinitions = new HashSet<>();

    public PiApplicationContext(Class clazz) {
        Package pckg = clazz.getPackage();

        ClassScanner classScanner = ScannerFactory.getScanner(ANNOTATION_SCANNER, pckg.getName());
        Set<Class<?>> beanClasses = classScanner.getAllBeanClasses();
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        throw new UnsupportedOperationException("To be implemented in the next step");
    }

    @Override
    public <T> T getBean(String componentName) {
        throw new UnsupportedOperationException("To be implemented in the next step");
    }

}
