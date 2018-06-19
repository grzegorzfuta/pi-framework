package pi.framework.app;

import pi.framework.app.dto.PersonDTO;
import pi.framework.app.service.ImportantBusinessService;
import pi.framework.core.context.ApplicationContext;
import pi.framework.core.context.PiApplicationContext;

import java.util.logging.Logger;

import java.util.List;

public class PiDemoApplication {

    private static Logger LOGGER = Logger.getLogger(PiDemoApplication.class.getName());

    private PiDemoApplication(){}

    public static void main(String[] args) {
        LOGGER.info("PI framework demo application...");

        ApplicationContext applicationContext = new PiApplicationContext(PiDemoApplication.class);

        ImportantBusinessService someImportantService = applicationContext.getBean(ImportantBusinessService.class);

        PersonDTO randomImportantPerson = someImportantService.fetchRandomImportantPerson();
        LOGGER.info("\nRANDOM IMPORTANT PERSON" + randomImportantPerson.toString());

        PersonDTO personByID = someImportantService.fetchByID(4);
        LOGGER.info("\nOSOBA PO NUMERZE ID " + personByID.toString());

        PersonDTO johnDoe = someImportantService.fetchByID(4);
        LOGGER.info("NO-NAME " + johnDoe.toString());

        List<PersonDTO> marlins = someImportantService.fetchByName("Marlin");
        LOGGER.info("MARLINS");
        marlins.forEach(personDTO -> LOGGER.info(personDTO.toString()));

        LOGGER.info("Winter demo application... finished");
    }

}
