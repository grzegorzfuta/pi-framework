package pi.framework.app.service;

import pi.framework.app.dto.PersonDTO;
import pi.framework.app.repository.PersonRepository;
import pi.framework.core.annotation.PowerInject;
import pi.framework.core.stereotype.Service;

@Service
public class ImportantBusinessService {

    @PowerInject
    private PersonRepository personRepository;

    public PersonDTO fetchImportantPerson(String id) {
        throw new UnsupportedOperationException("fetching IMPORTANT PersonDTO is not implemented yet");
    }

    public PersonDTO fetchRandomPerson(String id) {
        throw new UnsupportedOperationException("fetching RANDOM PersonDTO is not implemented yet");
    }
}
