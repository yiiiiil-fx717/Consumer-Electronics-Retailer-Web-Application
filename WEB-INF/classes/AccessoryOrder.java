public class AccessoryOrder {
  private Accessory accessory;
  private int numAccessories;

  public AccessoryOrder(Accessory accessory) {
    setAccessory(accessory);
    setNumAccessories(1);
  }
  public AccessoryOrder(Accessory accessory,int num) {
    setAccessory(accessory);
    setNumAccessories(num);
  }

  public Accessory getAccessory() {
    return(accessory);
  }

  protected void setAccessory(Accessory accessory) {
    this.accessory = accessory;
  }
  
  public int getNumAccessories() {
    return(numAccessories);
  }

  public void setNumAccessories(int n) {
    this.numAccessories = n;
  }

  public void incrementNumAccessories() {
    setNumAccessories(getNumAccessories() + 1);
  }

  public void incrementNumAccessories(int i) {
    setNumAccessories(getNumAccessories() + i);
  }

  public void cancelOrder() {
    setNumAccessories(0);
  }
}
