package ramos.jefferson.boot.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import ramos.jefferson.boot.entity.key.AthleteSportPK;

@Entity
@Table
public class AthleteSport implements Serializable {
    
    @EmbeddedId
    private AthleteSportPK athleteSportPK;

    public AthleteSport() {
    }

    public AthleteSport(AthleteSportPK athleteSportPK) {
        this.athleteSportPK = athleteSportPK;
    }

    public AthleteSportPK getAthleteSportPK() {
        return athleteSportPK;
    }

    public void setAthleteSportPK(AthleteSportPK athleteSportPK) {
        this.athleteSportPK = athleteSportPK;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.athleteSportPK);
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
        final AthleteSport other = (AthleteSport) obj;
        if (!Objects.equals(this.athleteSportPK, other.athleteSportPK)) {
            return false;
        }
        return true;
    }

}
