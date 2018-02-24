import java.util.*;

public class ShoppingCartAcc {
    private ArrayList accsOrdered;

    public ShoppingCartAcc() {
        accsOrdered = new ArrayList();
    }

    public List getAccsOrdered() {
        return(accsOrdered);
    }

    public void addAccessory(Accessory accessory, int num) {
        AccessoryOrder order;
        for(int i=0; i< accsOrdered.size(); i++) {
              order = (AccessoryOrder)accsOrdered.get(i);
              if (order.getAccessory().getName().equals(accessory.getName())) {
                  order.incrementNumAccessories(num);
                  return;
              }
        }
        accsOrdered.add(new AccessoryOrder(accessory));
    }

    public void removeAccessory(Accessory accessory){
        AccessoryOrder order;
        for(int i=0; i<accsOrdered.size(); i++) {
              order = (AccessoryOrder)accsOrdered.get(i);
              accsOrdered.remove(i);
        }
        //itemsOrdered.add(new ItemOrder(item));
    }

    public int getTotalNum(){
        int totalNum = 0;
        AccessoryOrder order;
        for(int i=0; i<accsOrdered.size(); i++) {
              order = (AccessoryOrder)accsOrdered.get(i);
              totalNum += order.getNumAccessories();
        }
        return totalNum;
    }

    public int getAccessoryNum(Accessory accessory){
        int totalNum = 0;
        AccessoryOrder order;
        for(int i=0; i<accsOrdered.size(); i++) {
              order = (AccessoryOrder)accsOrdered.get(i);
              if (order.getAccessory().getName().equals(accessory.getName())) {
                  return order.getNumAccessories();
              }
        }
        return 0;
    }

    public int getTotalValue(){
        int totalValue = 0;
        AccessoryOrder order;
        for(int i=0; i<accsOrdered.size(); i++) {
              order = (AccessoryOrder)accsOrdered.get(i);
              totalValue += order.getNumAccessories() * order.getAccessory().getPrice();
        }
        return totalValue;
    }

    /*public void combineWith(ShoppingCart cart){
        ItemOrder order;
        ArrayList tmpList = new ArrayList();
        for(int i=0; i < cart.getItemsOrdered().size(); i++) {
            order = (ItemOrder)cart.getItemsOrdered().get(i);
            this.addItem(order.getItem(),order.getNumItems());
        }
    }*

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
    
