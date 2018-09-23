package ramos.jefferson.boot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class AthleteDTO implements Serializable {
    
    @JsonProperty("id")
    private long id;
    
    @JsonProperty("firstName")
    private String firstName;
    
    @JsonProperty("lastName")
    private String lastName;
    
    @JsonProperty("nickname")
    private String nickname;
    
    @JsonProperty("sports")
    private List<Long> sports;

    @Autowired
    public AthleteDTO() {
    }

    public AthleteDTO(long id, String firstName, String lastName, String nickname) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    }

    public AthleteDTO(long id, String firstName, String lastName, String nickname, List<Long> sports) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.sports = sports;
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

    public List<Long> getSports() {
        return sports;
    }

    public void setSports(List<Long> sports) {
        this.sports = sports;
    }

}
