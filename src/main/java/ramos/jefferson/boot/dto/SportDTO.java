package ramos.jefferson.boot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;

public class SportDTO implements Serializable {
    
    @JsonProperty("id")
    private long id;
    
    @JsonProperty("name")
    private String name;

    @Autowired
    public SportDTO() {
    }

    public SportDTO(long id, String name) {
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

}
