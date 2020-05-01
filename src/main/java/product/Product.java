package product;

public class Product {
    private String title;
    private int prodid;
    private int cost;

    public Product(String title, int prodid, int cost){
        this.title = title;
        this.prodid = prodid;
        this.cost = cost;
    }

    public int getProdid(){
        return this.prodid;
    }

    public String getTitle(){
        return this.title;
    }

    public int getCost(){
        return this.cost;
    }
}
