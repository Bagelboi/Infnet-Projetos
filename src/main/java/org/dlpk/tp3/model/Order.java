package org.dlpk.tp3.model;

import lombok.*;
import org.dlpk.tp3.service.EmailService;

import java.util.ArrayList;
import java.util.List;

public class Order {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static
    class ProductOrder {
        Item product;
        Integer quantity;

        public String getInvoiceEntry() {
            return this.getQuantity() + "x " + this.getProduct().getNome() + " - " + this.getProduct().getPreco();
        }

        public Double getTotalPrice() {
            return this.getProduct().getPreco().getValor() * this.getQuantity();
        }
    }

    @Getter
    String clientName;
    @Getter
    String clientEmail;

    @Getter
    List<ProductOrder> products = new ArrayList<>();

    @Getter
    @Setter
    double discountRate = 0.1;

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


    public void printInvoice() {
        double total = 0;
        System.out.println("Cliente: " + clientName);
        for (ProductOrder product : products) {
            System.out.println(product.getInvoiceEntry());
            total += product.getTotalPrice();
        }
        System.out.println("Subtotal: R$" + total);
        System.out.println("Desconto: R$" + (total * discountRate));
        System.out.println("Total final: R$" + (total * (1 - discountRate)));
    }

    public void sendEmail() {
        EmailService.sendEmail(clientEmail, "Pedido recebido! Obrigado pela compra.");
    }
}