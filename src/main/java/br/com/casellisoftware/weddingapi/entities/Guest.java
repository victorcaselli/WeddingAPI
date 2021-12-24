package br.com.casellisoftware.weddingapi.entities;

import br.com.casellisoftware.weddingapi.enums.ConfirmationStatusType;
import br.com.casellisoftware.weddingapi.enums.MaritalStatusType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Guest implements Serializable {
    private static final long serialVersionUID = 1597514969505009781L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @ElementCollection
    private Set<String> phones = new HashSet<>();
    private Integer maritalStatus;
    private Integer confirmationStatus;


    public Integer getMaritalStatus(){
        return MaritalStatusType.toEnum(maritalStatus).getCode();
    }

    public void setMaritalStatus(Integer maritalStatus){
        this.maritalStatus = MaritalStatusType.toEnum(maritalStatus).getCode();
    }

    public Integer getConfirmationStatus(){
        return ConfirmationStatusType.toEnum(this.confirmationStatus).getCode();
    }

    public void setConfirmationStatus(Integer confirmationStatus){
        this.confirmationStatus = ConfirmationStatusType.toEnum(confirmationStatus).getCode();
    }
}

