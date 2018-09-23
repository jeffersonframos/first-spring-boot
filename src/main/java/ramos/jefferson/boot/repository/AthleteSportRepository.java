package ramos.jefferson.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ramos.jefferson.boot.entity.Athlete;
import ramos.jefferson.boot.entity.AthleteSport;
import ramos.jefferson.boot.entity.Sport;
import ramos.jefferson.boot.entity.key.AthleteSportPK;

public interface AthleteSportRepository extends JpaRepository<AthleteSport, AthleteSportPK> {
    
    @Modifying
    @Query("DELETE FROM AthleteSport a WHERE a.athleteSportPK.athlete = :athlete")
    public void delete(@Param("athlete") Athlete athlete);
    
    @Modifying
    @Query("DELETE FROM AthleteSport a WHERE a.athleteSportPK.sport = :sport")
    public void delete(@Param("sport") Sport sport);

}
