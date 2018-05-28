package pi.framework.app;

import pi.framework.app.dto.PersonDTO;
import pi.framework.app.service.ImportantBusinessService;
import pi.framework.core.context.ApplicationContext;
import pi.framework.core.context.PiApplicationContext;

import java.util.List;

public class PiDemoApplication {

    private PiDemoApplication(){}

    public static void main(String[] args) {
        System.out.println("PI framework demo application...");

        ApplicationContext applicationContext = new PiApplicationContext(PiDemoApplication.class);

        ImportantBusinessService someImportantService = applicationContext.getBean(ImportantBusinessService.class);

        PersonDTO randomImportantPerson = someImportantService.fetchRandomImportantPerson();
        System.out.println("RANDOM IMPORTANT PERSON");
        System.out.println(randomImportantPerson);

        PersonDTO personByID = someImportantService.fetchByID(4);
        System.out.println("OSOBA PO NUMERZE ID");
        System.out.println(personByID);

        PersonDTO johnDoe = someImportantService.fetchByID(4);
        System.out.println("NO-NAME");
        System.out.println(johnDoe);

        List<PersonDTO> marlins = someImportantService.fetchByName("Marlin");
        System.out.println("MARLINS");
        marlins.forEach(System.out::println);

        System.out.println("Winter demo application... finished");
    }

}
