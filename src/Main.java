import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static File saveFile = new File("basket.bin");

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);

        String[] products = new String[]{"Хлеб", "Яблоки", "Молоко"};
        int[] prices = new int[]{15, 54, 110};

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }

        Basket basket = null;
        if (saveFile.exists()){
            basket = Basket.loadFromBinFile(saveFile);
        } else {
            basket = new Basket(products , prices);
        }

        while (true) {
            System.out.println("Выбирите товар и количевто или введите 'end'");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }

            String[] parts = input.split(" ");

            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);

            basket.addToCart(productNumber, productCount);
            basket.saveBin(saveFile);

        }
        basket.printCart();

    }
}

