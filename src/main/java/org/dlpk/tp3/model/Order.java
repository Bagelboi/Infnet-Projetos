package org.dlpk.tp3.model;

import lombok.*;
import org.dlpk.tp3.service.EmailService;
import org.dlpk.tp3.service.InvoiceService;
import org.dlpk.tp3.value.Preco;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static
    class ProductOrder {
        Item product;
        Integer quantity;

        public Double getTotalPrice() {
            return this.getProduct().getPreco().getValor() * this.getQuantity();
        }
    }

    @Getter
    Client client;

    @Getter
    List<ProductOrder> products = new ArrayList<>();

    @Getter
    @Setter
    double discountRate = 0.1;



    public void printInvoice(InvoiceService invoiceService) {
        invoiceService.printInvoice(this);
    }

    public void sendEmail(EmailService emailService) {
        emailService.sendEmail(client.getClientEmail(), "Pedido recebido! Obrigado pela compra.");
    }
}