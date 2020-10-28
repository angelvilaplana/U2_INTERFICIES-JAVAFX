package orihuel.vilaplana.angel.controls.formulari;

import java.time.LocalDate;

public class FormulariData {

    private String name;

    private String surname;

    private String commentary;

    private String sex;

    private String city;

    private String operatingSystem;

    private int computerHours;

    private LocalDate formDate;

    public FormulariData(String name, String surname, String commentary, String sex, String city, String operatingSystem, int computerHours, LocalDate formDate) {
        this.name = name;
        this.surname = surname;
        this.commentary = commentary;
        this.sex = sex;
        this.city = city;
        this.operatingSystem = operatingSystem;
        this.computerHours = computerHours;
        this.formDate = formDate;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCommentary() {
        return commentary;
    }

    public String getSex() {
        return sex;
    }

    public String getCity() {
        return city;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public int getComputerHours() {
        return computerHours;
    }

    public LocalDate getFormDate() {
        return formDate;
    }

}
