package pi.framework.app.repository;

import pi.framework.app.dto.PersonDTO;
import pi.framework.app.persistence.PersonsDatabaseMock;
import pi.framework.core.annotation.PowerInject;
import pi.framework.core.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {
    @PowerInject
    private PersonsDatabaseMock personsDatabase;

    public List<PersonDTO> getPersonsByName(String name) {
        return personsDatabase.getDataStream()
                .filter(person -> person.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public Optional<PersonDTO> getById(Integer id) {
        return personsDatabase.getDataStream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    public PersonDTO getByIdx(int idx) {
        return personsDatabase.get(idx);
    }

    public int countAllPersons() {
        return personsDatabase.size();
    }
}
