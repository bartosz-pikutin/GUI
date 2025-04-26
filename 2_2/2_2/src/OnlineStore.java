public class OnlineStore extends ShoppingCart{

    public static abstract class Product{

        String name;
        double price;

        public String getName(){
            return Product.this.name;
        };

        public double getPrice(){
            return Product.this.price;
        }
    }

    public static class Book extends Product{
        public Book(String name, double price){
            this.name = name;
            this.price = price;
        }
    }

    public static class Clothing extends Product{
        public Clothing(String name, double price){
            this.name = name;
            this.price = price;
        }
    }

    public static class Electronics extends Product{
        public Electronics(String name, double price){
            this.name = name;
            this.price = price;
        }
    }


    public static void main(String[] args) {
        OnlineStore store = new OnlineStore();

//TODO: implement Book class
        Product book = new Book("The Catcher in the Rye", 10.99);
//TODO: implement Clothing class
        Product shirt = new Clothing("Blue Shirt", 25.99);
//TODO: implement Electronic class
        Product phone = new Electronics("iPhone 12", 999.99);

        store.addProduct(book);
        store.addProduct(shirt);
        store.addProduct(phone);

        System.out.println("Total price: " + store.getTotalPrice());

        store.removeProduct(shirt);

        System.out.println("Total price: " + store.getTotalPrice());
    }
	
//TODO: implement missing methods from abstract class

}
