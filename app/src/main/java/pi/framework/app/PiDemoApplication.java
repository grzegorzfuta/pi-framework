package pi.framework.app;

import pi.framework.app.dto.PersonDTO;
import pi.framework.app.service.ImportantBusinessService;
import pi.framework.core.context.ApplicationContext;
import pi.framework.core.context.PiApplicationContext;

public class PiDemoApplication {

    private PiDemoApplication(){}

    public static void main(String[] args) {
        System.out.println("PI framework demo application...");

        ApplicationContext applicationContext = new PiApplicationContext(PiDemoApplication.class);

        ImportantBusinessService someImportantService = applicationContext.getBean(ImportantBusinessService.class);

        PersonDTO krzychoDTO = someImportantService.fetchImportantPerson("Krzycho");

        System.out.println(krzychoDTO);
    }

}
