package br.com.casellisoftware.weddingapi.enums;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum MaritalStatusType {

    MARRIED(0, "Casado"),
    SINGLE(1, "Solteiro"),
    DATING(2, "Namorando");

    private Integer code;
    private String description;


    public static MaritalStatusType toEnum(Integer code){
        if (code == null) {
            return null;
        }

        for (MaritalStatusType type :  MaritalStatusType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid id. Id: "+code);
    }
}
