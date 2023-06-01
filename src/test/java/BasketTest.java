import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @Test
    public void testAddToBasket(){
        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int [] prices = {15, 54, 110};
        Basket basket = new Basket(products, prices);

        basket.addToCart(0,2);
        basket.addToCart(1,3);
        basket.addToCart(2,1);

        int[] actual = basket.getQuantity();
        int[] expected = {2,3,1};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testLoadFromTxtFile (){
        Basket basket = Basket.loadFromTxtFile(new File("src/test/resources/test_basket.txt"));

        String[] actualProduct = basket.getProducts();
        String[] expectedProducts = {"Хлеб", "Яблоки", "Молоко"};
        Assertions.assertArrayEquals(expectedProducts, actualProduct);
    }

    @Test
    public void testLoadFromJSON (){
        Basket basket = Basket.loadFromJSONFile(new File("src/test/resources/testBasket.json"));

        String[] actualProduct = basket.getProducts();
        String[] expectedProducts = {"Хлеб", "Яблоки", "Молоко"};
        Assertions.assertArrayEquals(expectedProducts, actualProduct);
    }

}