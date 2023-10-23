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

    @Column(name="birt_date")
    private Date birth_date;
    @Column(name="activation_date")
    private Date activation_date;

    //costruttore di default e costruttore con parametri
    public UserBadge(){}

    public UserBadge( String name, String surname, Date birth_date, Date activation_date) {
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.activation_date = activation_date;
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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getActivation_date() {
        return activation_date;
    }

    public void setActivation_date(Date activation_date) {
        this.activation_date = activation_date;
    }


    @Override
    public String toString() {
        return "UserBadge{" +
                "badge_id=" + badge_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth_date=" + birth_date +
                ", activation_date=" + activation_date +
                '}';
    }
}
