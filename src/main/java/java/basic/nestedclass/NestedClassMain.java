package java.basic.nestedclass;

public class NestedClassMain {

    public static void main(String[] args){

        Invoice invoice = new Invoice();
        invoice.addItem("seed",10,10);

        InvoiceByPublic invoiceByPublic = new InvoiceByPublic();
        InvoiceByPublic.Item item = new InvoiceByPublic.Item("seed",10,10);
        invoiceByPublic.add(item);
    }

}
