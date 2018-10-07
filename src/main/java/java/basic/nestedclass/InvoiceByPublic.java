package java.basic.nestedclass;

import java.util.ArrayList;

public class InvoiceByPublic {

    public static class Item {
        String description;
        int quantity;
        double unitPrice;

        public Item(String description , int quantity,double unitPrice){
            this.description = description;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }
        double price(){
            return quantity * unitPrice;
        }
    }

    private ArrayList<Item> items = new ArrayList<>();

    public void add(Item newItem){
        items.add(newItem);
    }
}
