package pi.framework.app.repository;

import pi.framework.app.dto.PersonDTO;

import java.util.Collection;

public class PersonRepository {
    public Collection<PersonDTO> getPersonByName(String name) {
        throw new UnsupportedOperationException("Getting persons is not implemented");
    }

    public PersonDTO getByName(String name) {
        throw new UnsupportedOperationException("Getting person is not implemented");
    }
}
