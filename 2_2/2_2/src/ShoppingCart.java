import java.util.ArrayList;

public abstract class ShoppingCart{

    int cartCount = 0;
    double totalPrice;
    ArrayList<OnlineStore.Product> cart = new ArrayList<OnlineStore.Product>();
    public void addProduct(OnlineStore.Product product){
        cart.add(product);
        cartCount++;
    }
    public void removeProduct(OnlineStore.Product product){
        cart.remove(product);
        cartCount--;
    }

    public double getTotalPrice(){
        totalPrice =0;
        for(OnlineStore.Product s: cart){
         totalPrice += s.getPrice();
        }
        return totalPrice;
    }
}
