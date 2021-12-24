package br.com.casellisoftware.weddingapi.dto;


import br.com.casellisoftware.weddingapi.entities.Guest;
import br.com.casellisoftware.weddingapi.enums.ConfirmationStatusType;
import br.com.casellisoftware.weddingapi.enums.MaritalStatusType;
import br.com.casellisoftware.weddingapi.util.ModelMapperUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//TODO - create validations using spring validations

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestDto implements Serializable {
    private static final long serialVersionUID = -6160570595803441366L;

    private Long id;
    private String name;
    private String email;
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

    public static GuestDto toDto(Guest guest){
        return ModelMapperUtil.map(guest, GuestDto.class);
    }

    public  Guest toEntity(){
        return ModelMapperUtil.map(this,Guest.class);
    }
}
