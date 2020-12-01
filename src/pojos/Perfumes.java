package pojos;

public class Perfumes  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String price;
     private String flavor;
     private String volume;

    public Perfumes () {
    }

    public Perfumes(String name, String price, String flavor, String volume) {
       this.name = name;
       this.price = price;
       this.flavor = flavor;
       this.volume = volume;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return this.price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    public String getFlavor() {
        return this.flavor;
    }
    
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
    public String getVolume() {
        return this.volume;
    }
    
    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Название: " + name + ", Цена: " + price + ", Аромат: " + flavor + ", Объем: " + volume;
    }


}