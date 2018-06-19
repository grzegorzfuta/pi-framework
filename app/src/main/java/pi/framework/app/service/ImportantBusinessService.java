package pi.framework.app.service;

import pi.framework.app.dto.PersonDTO;
import pi.framework.app.repository.PersonRepository;
import pi.framework.core.annotation.PowerInject;
import pi.framework.core.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ImportantBusinessService {

    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_NAME = "Thomas";
    private static final String DEFAULT_SURNAME = "Anderson";
    private static final String DEFAULT_INFO = "He is The One";
    private static final PersonDTO DEFAULT_PERSON = new PersonDTO(DEFAULT_ID, DEFAULT_NAME, DEFAULT_SURNAME, DEFAULT_INFO);

    @PowerInject
    private PersonRepository personRepository;

    private final Random random;

    public ImportantBusinessService() {
        random = new Random(System.currentTimeMillis());
    }

    public PersonDTO fetchRandomImportantPerson() {
        int randomIndex = random.nextInt(personRepository.countAllPersons());
        return personRepository.getByIdx(randomIndex);
    }

    public PersonDTO fetchByID(Integer id) {
        return personRepository.getById(id).orElse(DEFAULT_PERSON);
    }

    public List<PersonDTO> fetchByName(String name) {
        return personRepository.getPersonsByName(name);
    }
}
