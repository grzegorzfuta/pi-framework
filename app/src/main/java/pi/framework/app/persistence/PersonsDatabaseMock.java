package pi.framework.app.persistence;

import pi.framework.app.dto.PersonDTO;
import pi.framework.core.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.logging.Level.SEVERE;

@Component
public class PersonsDatabaseMock implements ReadableStorage<PersonDTO> {

    private static final Logger LOGGER = Logger.getLogger(PersonsDatabaseMock.class.getName());

    private final List<PersonDTO> data = new ArrayList<>();

    public PersonsDatabaseMock() {
        URL resource = getClass().getClassLoader().getResource("data/persons.csv");
        if (resource != null) {
            File dataFile = new File(resource.getFile());
            try {
                List<String> linesFromFile = Files.readAllLines(Paths.get(dataFile.getParent(), dataFile.getName()));
                data.addAll(convertToDTO(linesFromFile));
            } catch (IOException ioException) {
                LOGGER.log(SEVERE, "Error reading database mock file", ioException);
            }
        }
    }

    private List<PersonDTO> convertToDTO(List<String> linesFromFile) {
        return linesFromFile.stream()
                .map(line -> {
                    String[] tokens = line.split(",");
                    return new PersonDTO(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3]);
                })
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO get(int id) {
        return data.get(id);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public Stream<PersonDTO> getDataStream() {
        return data.stream();
    }
}
