package org.dlpk.tp3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Getter
    String clientEmail;
    @Getter
    String clientName;

    public void setClientEmail(String clientEmail) {
        //https://pt.stackoverflow.com/questions/1386/express%C3%A3o-regular-para-valida%C3%A7%C3%A3o-de-e-mail
        if (clientEmail.matches("/[a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.([a-z]+)?/i"))
            this.clientEmail = clientEmail;
        else
            throw new IllegalArgumentException();
    }


    public void setClientName(String clientName) {
        if (clientEmail.matches("/\\d+/i"))
            throw new IllegalArgumentException();
        else
            this.clientName = clientName;
    }
}
