public class ItemOrder {
  private Item item;
  private int numItems;

  public ItemOrder(Item item) {
    setItem(item);
    setNumItems(1);
  }
  public ItemOrder(Item item,int num) {
    setItem(item);
    setNumItems(num);
  }

  public Item getItem() {
    return(item);
  }

  protected void setItem(Item item) {
    this.item = item;
  }
  
  public int getNumItems() {
    return(numItems);
  }

  public void setNumItems(int n) {
    this.numItems = n;
  }

  public void incrementNumItems() {
    setNumItems(getNumItems() + 1);
  }

  public void incrementNumItems(int i) {
    setNumItems(getNumItems() + i);
  }

  public void cancelOrder() {
    setNumItems(0);
  }
}
