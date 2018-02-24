import java.util.ArrayList;
import java.util.List;


public class Accessory {
    String name;
    String id;
    String image;
    int    price;
    
    public Accessory(){
    }
    public Accessory(String name,String id,String image,int price){
        this.name = name;
        this.id = id;
        this.image = image;
        this.price = price;
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

    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return this.price ;
    }
}
