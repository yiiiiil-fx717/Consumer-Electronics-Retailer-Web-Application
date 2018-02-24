import java.util.*;

public class ShoppingCart {
    private ArrayList itemsOrdered;

    public ShoppingCart() {
        itemsOrdered = new ArrayList();
    }

    public List getItemsOrdered() {
        return(itemsOrdered);
    }

    public void addItem(Item item, int num) {
        ItemOrder order; 
        for(int i=0; i<itemsOrdered.size(); i++) {
              order = (ItemOrder)itemsOrdered.get(i);
              if (order.getItem().getId().equals(item.getId())) {
                  order.incrementNumItems(num);
                  return;
              }
        }
        itemsOrdered.add(new ItemOrder(item));
    }

    public void removeItem(Item item){
        ItemOrder order;
        for(int i=0; i<itemsOrdered.size(); i++) {
              order = (ItemOrder)itemsOrdered.get(i);
              itemsOrdered.remove(i);
        }
        //itemsOrdered.add(new ItemOrder(item));
    }

    public int getTotalNum(){
        int totalNum = 0;
        ItemOrder order;
        for(int i=0; i<itemsOrdered.size(); i++) {
              order = (ItemOrder)itemsOrdered.get(i);
              totalNum += order.getNumItems();
        }
        return totalNum;
    }

    public int getItemNum(Item item){
        int totalNum = 0;
        ItemOrder order;
        for(int i=0; i<itemsOrdered.size(); i++) {
              order = (ItemOrder)itemsOrdered.get(i);
              if (order.getItem().getId().equals(item.getId())) {
                  return order.getNumItems();
              }
        }
        return 0;
    }

    public int getTotalValue(){
        int totalValue = 0;
        ItemOrder order;
        for(int i=0; i<itemsOrdered.size(); i++) {
              order = (ItemOrder)itemsOrdered.get(i);
              totalValue += order.getNumItems() * order.getItem().getPrice();
        }
        return totalValue;
    }

    public void combineWith(ShoppingCart cart){
        ItemOrder order;
        ArrayList tmpList = new ArrayList();
        for(int i=0; i < cart.getItemsOrdered().size(); i++) {
            order = (ItemOrder)cart.getItemsOrdered().get(i);
            this.addItem(order.getItem(),order.getNumItems());
        }
    }

/*
    public synchronized void setNumOrdered(String itemID,
                                           int numOrdered) {
        ItemOrder order;
        for(int i=0; i<itemsOrdered.size(); i++) {
            order = (ItemOrder)itemsOrdered.get(i);
            if (order.getItemID().equals(itemID)) {
                if (numOrdered <= 0) {
                    itemsOrdered.remove(i);
                } else {
                    order.setNumItems(numOrdered);
                }
                return;
            }
        }
        ItemOrder newOrder =
            new ItemOrder(Catalog.getItem(itemID));
        itemsOrdered.add(newOrder);
    }*/
}
    
