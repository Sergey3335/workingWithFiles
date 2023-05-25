import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
    private String[] products;
    private int[] prices;
    private int[] quantity;

    public Basket() {
    }

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.quantity = new int[products.length];
    }

    public void addToCart(int productNum, int amount) {
        quantity[productNum] += amount;
    }

    public void printCart() {
        int sumPrice = 0;
        System.out.println("Ваша корзина");
        for (int i = 0; i < products.length; i++) {
            if (quantity[i] > 0) {
                int currentPrice = prices[i] * quantity[i];
                sumPrice += currentPrice;
                System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт " + "в корзине  " + quantity[i] + " шт" + currentPrice + " руб");
            }
        }
        System.out.println("Стоимость покупки " + sumPrice + " руб");
    }

    public void saveJSON (File file){
        try(PrintWriter writer = new PrintWriter(file)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(this);
            writer.print(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromJSONFile (File file) {
        Basket basket = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine())!=null){
                builder.append(line);
            }
            Gson gson = new Gson();
            basket = gson.fromJson(builder.toString(), Basket.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }

}
