package br.com.casellisoftware.weddingapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ConfirmationStatusType {

    CONFIRMED(0, "Confirmado"),
    WAITING(1, "Em aguardo"),
    NO_ANSWER(2, "Sem resposta"),
    UNCONFIRMED(3, "Não confirmado");

    private Integer code;
    private String description;


    public static ConfirmationStatusType toEnum(Integer code){
        if (code == null) {
            return null;
        }

        for (ConfirmationStatusType type :  ConfirmationStatusType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid id. Id: "+code);
    }
}
