package pi.framework.core.context;

import pi.framework.core.NoBeanDefinition;
import pi.framework.core.annotation.PowerInject;
import pi.framework.core.bean.BeanDefinitionInfo;
import pi.framework.core.scanner.ClassScanner;
import pi.framework.core.scanner.ScannerFactory;
import pi.framework.core.stereotype.Component;
import pi.framework.core.stereotype.Repository;
import pi.framework.core.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableCollection;
import static pi.framework.core.scanner.ClassScanner.Scanner.ANNOTATION_SCANNER;

public class PiApplicationContext implements ApplicationContext {

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
                exception.printStackTrace();
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
            exception.printStackTrace();
        }
        return beanDefinition;
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        if (clazz != null) {
            BeanDefinitionInfo beanDefinitionInfo = beanDefinitions.stream()
                    .filter(beanDefinition -> beanDefinition.getClazz().equals(clazz))
                    .findFirst()
                    .orElseThrow(() -> new NoBeanDefinition("No bean definition found for: " + clazz.getName()));

            if (beanDefinitionInfo.isEveryComponentInjected()) {
                return (T) beanDefinitionInfo.getInstance();
            } else {
                T bean = powerInjectComponents((T) beanDefinitionInfo.getInstance());
                beanDefinitionInfo.setEveryComponentInjected(true);
                return bean;
            }
        }
        throw new NoBeanDefinition("Null class passed to getBean()");
    }

    @Override
    public <T> T getBean(String componentName) {
        throw new UnsupportedOperationException("To be implemented someday...");
    }

    private Class getBeanType(Class clazz) {
        Set<Annotation> classAnnotations = Stream.of(clazz.getAnnotations()).collect(Collectors.toSet());

        Optional<Class> c = VALID_ANNOTATION_CLASSES.stream()
                .filter(validAnnotation ->
                        classAnnotations.stream().anyMatch(
                                a -> a.annotationType().getName().equals(validAnnotation.getName())
                        )
                ).findFirst();

        return c.orElse(Component.class);
    }


    private <T> T powerInjectComponents(T beanInstance) {
        Set<Field> annotatedFields = getPowerInjectedFields(beanInstance);

        annotatedFields.forEach(field -> {
            Object injectedBeanInstance = getBean(field.getType());
            try {
                field.setAccessible(true);
                field.set(beanInstance, injectedBeanInstance);
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return beanInstance;
    }

    private Set<Field> getPowerInjectedFields(Object object) {
        List<Field> fields = asList(object.getClass().getDeclaredFields());

        return fields.stream()
                .filter(field -> field.getAnnotationsByType(PowerInject.class).length > 0)
                .collect(Collectors.toSet());
    }

}
