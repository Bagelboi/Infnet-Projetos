package org.dlpk.tp3;

import org.dlpk.tp3.model.Client;
import org.dlpk.tp3.model.DiscountPolicy;
import org.dlpk.tp3.model.Item;
import org.dlpk.tp3.model.Order;
import org.dlpk.tp3.service.EmailService;
import org.dlpk.tp3.service.InvoiceService;
import org.dlpk.tp3.value.Preco;

public class App {
    public static void main(String[] args){
        Order order = new Order( new Client("joao@email.com", "João") );
        order.getProducts().add( new Order.ProductOrder(new Item("Notebook", new Preco(3500)), 1) );
        order.getProducts().add( new Order.ProductOrder(new Item("Mouse", new Preco(80.0)), 2) );
        order.printInvoice(new InvoiceService(new DiscountPolicy()));
        order.sendEmail(new EmailService());
    }
}
