package org.dlpk;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    public static class Address {
        private final String endereco;

        public Address(String endereco) {
            this.endereco = endereco;
        }
    }

    private String name;
    private String email;
    private List<Address> addresses = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.matches(".*[0-9].*"))
            throw new IllegalArgumentException("Nome com numeros!");
        else
            this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.matches(".*@.*"))
            this.email = email;
        else
            throw new IllegalArgumentException("Email invalido");
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public void addAddress(Address address) {
        if (addresses.stream().noneMatch( address1 -> address.endereco.equals(address1.endereco) ))
            addresses.add(address);
    }

    public void removeAddress(Address address) {
        addresses.removeIf( address1 -> address.endereco.equals(address1.endereco) );
    }
}