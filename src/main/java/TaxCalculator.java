import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class TaxCalculator {

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> inputs = new ArrayList<ArrayList<String>>();
        ArrayList<String> input1 = new ArrayList<String>();
        input1.add("1 book at 124.99");
        input1.add("1 music CD at 149.99");
        input1.add("1 chocolate bar at 40.85");


        ArrayList<String> input2 = new ArrayList<String>();
        input2.add("1 imported box of chocolates at 100.00");
        input2.add("1 imported bottle of perfume at 470.50");


        ArrayList<String> input3 = new ArrayList<String>();
        input3.add("1 imported bottle of perfume at 270.99");
        input3.add("1 bottle of perfume at 180.99");
        input3.add("1 packet of headache pills at 19.75");
        input3.add("1 box of imported chocolates at 210.25");

        inputs.add(input1);
        inputs.add(input2);
        inputs.add(input3);


        ArrayList<ArrayList<String>> outputs = new ArrayList<ArrayList<String>>();
        CalculateTax tax = new CalculateTax();


        int size = inputs.size();
        for( int i=0; i < size; i++ ) {
            ArrayList<String> input = inputs.get(i);
            ArrayList<Product> products =new ArrayList<>();
            ArrayList<String> output =new ArrayList<>();
            for( int j=0; j < input.size(); j++ ) {
                String[] splits = input.get(j).split(" ");
                int count = Integer.parseInt(splits[0]);
                double price = Double.parseDouble(splits[splits.length-1]);
                String intermediate = "";
                for( int k=1; k < splits.length-1; k++) {
                    intermediate +=" " + splits[k];
                }
                //System.out.println("count = "+ count + " " + intermediate +  " price = " + price);

                boolean isImported = false;
                if(intermediate.contains("imported")) {
                    isImported = true;
                }
                ProductType productType;
                if(intermediate.contains("food") || intermediate.contains("chocolate")) {
                    productType = ProductType.FOOD;
                }
                else if(intermediate.contains("book")) {
                    productType = ProductType.BOOK;
                }
                else if(intermediate.contains("medical") || intermediate.contains("pill")) {
                    productType = ProductType.MEDICAL;
                }
                else {
                    productType = ProductType.OTHERS;
                }

                Product p = new Product(isImported,productType,price,count,intermediate);
                String result = tax.getReceipt(p);
                products.add(p);
                output.add(result);
            }
            double totalPrice = tax.calculateTotalPrice(products);
            double totalTax = tax.round(tax.calculateTotalTax(products));
            PrintReceipt(output,totalPrice,totalTax);
        }

    }

    public static void  PrintReceipt(ArrayList<String> result, double totalPrice, double totalTax) {
        for(int i=0; i< result.size(); i++) {
            System.out.println(result.get(i));
        }

        System.out.println("Tax:  " + totalTax);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);
        String str1 = df.format(totalPrice); // 0.91239
        System.out.println("Total:  " + str1);
    }
}
