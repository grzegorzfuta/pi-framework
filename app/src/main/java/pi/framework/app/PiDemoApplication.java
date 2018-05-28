package pi.framework.app;

import pi.framework.app.dto.PersonDTO;
import pi.framework.app.service.ImportantBusinessService;
import pi.framework.core.context.ApplicationContext;
import pi.framework.core.context.PiApplicationContext;

import java.util.logging.Logger;

public class PiDemoApplication {

    private static Logger LOGGER = Logger.getLogger(PiDemoApplication.class.getName());

    private PiDemoApplication(){}

    public static void main(String[] args) {
        LOGGER.info("PI framework demo application...");

        ApplicationContext applicationContext = new PiApplicationContext(PiDemoApplication.class);

        ImportantBusinessService someImportantService = applicationContext.getBean(ImportantBusinessService.class);

        PersonDTO krzychoDTO = someImportantService.fetchImportantPerson("Krzycho");

        LOGGER.info(krzychoDTO.toString());
    }

}
