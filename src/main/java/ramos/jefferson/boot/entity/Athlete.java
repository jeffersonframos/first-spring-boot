package ramos.jefferson.boot.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Athlete implements Serializable {
    
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(generator = "athlete_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "athlete_id_seq", sequenceName = "athlete_id_seq")
    private long id;
    
    @Column(name = "firstName", nullable = false)
    private String firstName;
    
    @Column(name = "lastName", nullable = false)
    private String lastName;
    
    @Column(name = "nickname")
    private String nickname;

    public Athlete() {
    }

    public Athlete(long id, String firstName, String lastName, String nickname) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Athlete other = (Athlete) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
