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
public class Sport implements Serializable {
    
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(generator = "sport_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "sport_id_seq", sequenceName = "sport_id_seq")
    private long id;
    
    @Column(name = "name", nullable = false)
    private String name;

    public Sport() {
    }

    public Sport(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Sport other = (Sport) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
