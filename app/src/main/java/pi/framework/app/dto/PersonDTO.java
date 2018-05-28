package pi.framework.app.dto;

import java.util.Objects;

public class PersonDTO {
    private String name;
    private String surname;
    private String id;
    private String additionalInfo;

    public PersonDTO(String id, String name, String surname, String additionalInfo) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.additionalInfo = additionalInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(name, personDTO.name) &&
                Objects.equals(surname, personDTO.surname) &&
                Objects.equals(id, personDTO.id) &&
                Objects.equals(additionalInfo, personDTO.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, id, additionalInfo);
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id='" + id + '\'' +
                ",name='" + name + '\'' +
                ", surname='" + surname + '\'' +

                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}