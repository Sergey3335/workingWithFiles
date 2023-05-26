import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static File saveFile = new File("basket.json");

    static String[] products = new String[]{"Хлеб", "Яблоки", "Молоко"};
    static int[] prices = new int[]{15, 54, 110};
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
        ReaderXML settings = new ReaderXML(new File("shop.xml"));
        File loadFile = new File(settings.loadFile);
        File saveFaile = new File(settings.saveFile);
        File logFile = new File(settings.logFile);

        Basket basket = createBasket(loadFile, settings.isLoad, settings.loadFormat);
        if (saveFile.exists()){
            basket = Basket.loadFromJSONFile(saveFile);
        } else {
            basket = new Basket(products , prices);
        }

        ClientLog log = new ClientLog();

        while (true) {
            System.out.println("Выбирите товар и количевто или введите 'end'");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                log.exportAsCSV(new File("log.csv"));
                break;
            }

            String[] parts = input.split(" ");

            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);

            basket.addToCart(productNumber, productCount);
            log.log(productNumber, productCount);
            basket.saveJSON(saveFile);

        }
        basket.printCart();

    }

    private static Basket createBasket(File loadFile, boolean isLoad, String loadFormat) {
        Basket basket;
        if (isLoad && loadFile.exists()){
            basket = switch (loadFormat){
                case "json" -> Basket.loadFromJSONFile(loadFile);
                case "txt" -> Basket.loadFromTxtFile(loadFile);
                default -> new Basket(products, prices);
            };
        }else{

        }
    }
}

