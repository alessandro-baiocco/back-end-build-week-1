package buildWeek.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_badges")
public class UserBadge {

    //attributi
    @Id
    @GeneratedValue
    private int badge_id;

    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;

    @Column(name="birth_date")
    private Date birthDate;
    @Column(name="activation_date")
    private Date activationDate;

    //costruttore di default e costruttore con parametri
    public UserBadge(){}

    public UserBadge( String name, String surname, Date birthDate, Date activationDate) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
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
