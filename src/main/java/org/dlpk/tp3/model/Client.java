package org.dlpk.tp3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Client {
    @Getter
    String clientEmail;
    @Getter
    String clientName;

    public Client(String clientEmail, String clientName) {
        setClientEmail(clientEmail);
        setClientName(clientName);
    }

    public void setClientEmail(String clientEmail) {
        if (clientEmail.endsWith(".com"))
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
