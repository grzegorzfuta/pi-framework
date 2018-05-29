package pi.framework.core.scanner;

public class ScannerFactory {

    private ScannerFactory() {
    }

    public static ClassScanner getScanner(ClassScanner.Scanner scanner, String packageName) {
        ClassScanner classScanner;
        switch (scanner) {
            case ANNOTATION_SCANNER:
                classScanner = new AnnotationScanner(packageName);
                break;
            default:
                classScanner = new NotImplementedScanner(packageName);
        }
        classScanner.scan();
        return classScanner;
    }
}
