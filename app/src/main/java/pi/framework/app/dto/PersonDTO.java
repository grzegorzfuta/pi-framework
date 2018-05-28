package pi.framework.app.dto;

import java.util.Objects;

public class PersonDTO {
    private Integer id;
    private String name;
    private String surname;
    private String additionalInfo;

    public PersonDTO(Integer id, String name, String surname, String additionalInfo) {
        this.id = id;
        this.name = name.trim();
        this.surname = surname.trim();
        this.additionalInfo = additionalInfo.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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