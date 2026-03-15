package org.dlpk.tp3.service;


import lombok.AllArgsConstructor;
import org.dlpk.tp3.model.DiscountPolicy;
import org.dlpk.tp3.model.IDiscountPolicy;
import org.dlpk.tp3.model.Item;
import org.dlpk.tp3.model.Order;
import org.dlpk.tp3.value.Preco;

@AllArgsConstructor
public class InvoiceService {

    private final IDiscountPolicy discountPolicy;

    private String getInvoiceEntryFromOrder(Order.ProductOrder productOrder) {
        return productOrder.getQuantity() + "x " + productOrder.getProduct().getNome() + " - " + productOrder.getProduct().getPreco();
    }

    public void printInvoice(Order order) {
        double total = 0;
        System.out.println("Cliente: " + order.getClient().getClientName());
        for (Order.ProductOrder product : order.getProducts()) {
            System.out.println(getInvoiceEntryFromOrder(product));
            total += product.getTotalPrice();
        }
        Preco preco_total = new Preco(total);
        Preco desconto =  discountPolicy.calculateDiscount( preco_total, order.getDiscountRate() );
        System.out.println("Subtotal: " + preco_total.toString());
        System.out.println("Desconto: " + desconto.toString());
        System.out.println("Total final: " + new Preco( preco_total.getValor() - desconto.getValor() ).toString());
    }
}
