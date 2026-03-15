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

    private void printInvoiceField(String prefix, String value) {
        System.out.printf("%s: %s%n", prefix, value);
    }

    public void printInvoice(Order order) {
        double total = 0;
        System.out.println("Cliente: " + order.getClient().getClientName());
        for (Order.ProductOrder product : order.getProducts()) {
            printInvoiceField(":", getInvoiceEntryFromOrder(product));
            total += product.getTotalPrice();
        }
        Preco preco_total = new Preco(total);
        Preco desconto =  discountPolicy.calculateDiscount( preco_total, order.getDiscountRate() );
        System.out.println("Subtotal: " + preco_total.toString());
        printInvoiceField("Subtotal", preco_total.toString());
        printInvoiceField("Desconto", desconto.toString());
        printInvoiceField( "Total final", preco_total.sub(desconto).toString() );
    }
}
