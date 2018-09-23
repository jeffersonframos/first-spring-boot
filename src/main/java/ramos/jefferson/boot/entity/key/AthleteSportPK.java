package ramos.jefferson.boot.entity.key;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import ramos.jefferson.boot.entity.Athlete;
import ramos.jefferson.boot.entity.Sport;

@Embeddable
public class AthleteSportPK implements Serializable {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "athlete", nullable = false, foreignKey = @ForeignKey(name = "fk_athletesport_athlete"))
    private Athlete athlete;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport", nullable = false, foreignKey = @ForeignKey(name = "fk_athletesport_sport"))
    private Sport sport;

    public AthleteSportPK() {
    }

    public AthleteSportPK(Athlete athlete, Sport sport) {
        this.athlete = athlete;
        this.sport = sport;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.athlete);
        hash = 83 * hash + Objects.hashCode(this.sport);
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
        final AthleteSportPK other = (AthleteSportPK) obj;
        if (!Objects.equals(this.athlete, other.athlete)) {
            return false;
        }
        if (!Objects.equals(this.sport, other.sport)) {
            return false;
        }
        return true;
    }

}
