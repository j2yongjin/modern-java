package yjlee.basic.nestedclass;

import java.util.ArrayList;




public class Invoice {

    private static class Item {
        String description;
        int quantity;
        double unitPrice;

        double price(){
            return quantity * unitPrice;
        }
    }

    private ArrayList<Item> items = new ArrayList<>();

    public void addItem(String description , int quantity,double unitPrice){
        Item newItem = new Item();
        newItem.description = description;
        newItem.quantity = quantity;
        newItem.unitPrice = unitPrice;
        items.add(newItem);
    }
}
