package pi.framework.core.scanner;

import pi.framework.core.stereotype.Component;
import pi.framework.core.stereotype.Repository;
import pi.framework.core.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

import static java.util.stream.Collectors.toSet;

public class AnnotationScanner implements ClassScanner {

    private static final String CLASS_EXTENSION = ".class";
    private static final String SEPARATOR_DOT = ".";
    private static final String SEPARATOR_SLASH = "/"; // todo use File.separator (?)

    private boolean isScanned = false;
    private String packageName;

    private Set<Class<?>> allClasses;

    AnnotationScanner(String packageName) {
        this.packageName = packageName;
        scan();
    }

    @Override
    public String getPackage() {
        return packageName;

    }

    @Override
    public Set<Class<?>> getAllBeanClasses() {
        return allClasses;
    }

    @Override
    public Set<Class<?>> getAllAnnotatedClasses(Annotation... annotation) {
        return allClasses;
    }

    @Override
    public void scan() {
        // class scanning is working right now
        allClasses = findClassesInPackage(packageName).stream()
                .filter(c -> containsOneOfAnnotations(c.getAnnotations(),
                        Component.class,
                        Service.class,
                        Repository.class))
                .collect(toSet());
    }

    private boolean containsOneOfAnnotations(Annotation[] classesToSearchIn, Class... annotations) {
        return Arrays.stream(classesToSearchIn)
                .anyMatch(c -> Arrays.asList(annotations).contains(c.annotationType()));
    }

    private Set<Class<?>> findClassesInPackage(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        String path = packageName.replace(SEPARATOR_DOT, SEPARATOR_SLASH);
        Enumeration<URL> resources = null;
        try {
            resources = classLoader.getResources(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<File> dirs = Collections.list(resources).stream()
                .map(r -> new File(r.getFile()))
                .collect(toSet());

        return dirs.stream()
                .flatMap(d -> findClassesInDirectory(d, packageName).stream())
                .collect(toSet());
    }

    private Set<Class<?>> findClassesInDirectory(File directory, String packageName) {
        if (!directory.exists()) {
            return Collections.EMPTY_SET;
        }

        Set<Class<?>> classes = new HashSet<>();

        // todo refactor ugly, UGLY .forEach

        Arrays.stream(directory.listFiles())
                .forEach(file -> {
                    if (file.isDirectory()) {
                        classes.addAll(findClassesInDirectory(file, packageName + SEPARATOR_DOT + file.getName()));
                    } else if (file.getName().endsWith(CLASS_EXTENSION)) {
                        try {
                            classes.add(Class.forName(packageName + SEPARATOR_DOT + file.getName().substring(0, file.getName().length() - 6)));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });

        return classes;
    }

}
