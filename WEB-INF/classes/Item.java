import java.util.ArrayList;
import java.util.List;


public class Item {
    String name;
    String id;
    String image;
    String color;
    int    price;
    List<String> accessories;
    
    public Item(){
        accessories = new ArrayList<String>();
    }
    public Item(String name,String id,String image,String color,int price,List<String> accessories){
        this.name = name;
        this.id = id;
        this.image = image;
        this.color = color;
        this.price = price;
        this.accessories = accessories;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id ;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return this.image;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return this.color;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return this.price ;
    }

    public void setAccessories(List<String> accessories) {
        this.accessories = accessories;
    }
    public List<String> getAccessories() {
        return accessories;
    }
}
