package buildWeek.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;

@Entity
@Table(name = "user_badges")
public class UserBadge {

    //attributi
    @Id
    @GeneratedValue
    private int badge_id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "activation_date")
    private LocalDate activationDate;

    //costruttore di default e costruttore con parametri
    public UserBadge() {
    }

    public UserBadge(String name, String surname, LocalDate birthDate, LocalDate activationDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.activationDate = activationDate;
    }


    // getters and setters tranne setId (generato automaticamente)
    public int getBadge_id() {
        return badge_id;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }


    @Override
    public String toString() {
        return "UserBadge{" +
                "badge_id=" + badge_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth_date=" + birthDate +
                ", activation_date=" + activationDate +
                '}';
    }
}
