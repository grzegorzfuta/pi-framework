package pi.framework.core.context;

import pi.framework.core.bean.BeanDefinitionInfo;
import pi.framework.core.scanner.ClassScanner;
import pi.framework.core.scanner.ScannerFactory;
import pi.framework.core.stereotype.Component;
import pi.framework.core.stereotype.Repository;
import pi.framework.core.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableCollection;
import static java.util.logging.Level.SEVERE;
import static pi.framework.core.scanner.ClassScanner.Scanner.ANNOTATION_SCANNER;

public class PiApplicationContext implements ApplicationContext {

    private static final Logger LOGGER = Logger.getLogger(PiApplicationContext.class.getName());

    private final Collection<Class> VALID_ANNOTATION_CLASSES = unmodifiableCollection(asList(
            Component.class, Repository.class, Service.class));

    private final Set<BeanDefinitionInfo> beanDefinitions = new HashSet<>();

    public PiApplicationContext(Class clazz) {
        Package pckg = clazz.getPackage();

        ClassScanner classScanner = ScannerFactory.getScanner(ANNOTATION_SCANNER, pckg.getName());
        Set<Class<?>> piComponents = classScanner.getAllBeanClasses();
        instantiateComponents(piComponents);
    }

    private void instantiateComponents(Set<Class<?>> piComponents) {
        piComponents.forEach(componentClass -> {
            String className = componentClass.getName();
            try {
                Class newClass = Class.forName(className);
                BeanDefinitionInfo beanDefinition = createBeanInstance(newClass);
                if (beanDefinition.getName() != null) {
                    beanDefinitions.add(beanDefinition);
                }
            } catch (ClassNotFoundException exception) {
                LOGGER.log(SEVERE, "No class found: " + className, exception);
            }
        });
    }

    private BeanDefinitionInfo createBeanInstance(Class aClass) {
        BeanDefinitionInfo beanDefinition = new BeanDefinitionInfo();
        try {
            Object instance = aClass.newInstance();
            beanDefinition.setClazz(aClass);
            beanDefinition.setInstance(instance);
            beanDefinition.setName(aClass.getName());
        } catch (ReflectiveOperationException exception) {
            LOGGER.log(SEVERE, "Error while instantiating class: " + aClass.getName(), exception);
        }
        return beanDefinition;
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        // collection `beanDefinitions` is filled
        throw new UnsupportedOperationException("To be implemented in the next step. The next one.");
    }

    @Override
    public <T> T getBean(String componentName) {
        throw new UnsupportedOperationException("To be implemented in the next step.  The next one.");
    }

}
