import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CalculateTax {
    final double baseTax = 0.10;
    final double additionalTax = 0.05;


    public String getReceipt(Product product) {
            double newPrice = product.price + round(calculateTax(product));
            String receipt = "";
            if(newPrice != product.price) {
                product.setPrice( newPrice);
                receipt += product.count + product.info + ": " + newPrice;
            }
            else {
                receipt += product.count + product.info + ": " + product.price;
            }
            return receipt;
    }

    public double calculateTotalTax(ArrayList<Product> products) {
        double before = 0f;
        double after = 0f;
        for(int i=0; i < products.size(); i++) {
            before += products.get(i).price;
            after += products.get(i).priceAfterTax;
        }
        return  after - before;
    }

    public double calculateTotalPrice(ArrayList<Product> products) {
        double sum = 0f;
        for(int i=0; i < products.size(); i++) {
            sum += products.get(i).priceAfterTax;
        }
        return  sum;
    }

    public double calculateTax(Product product) {
        if(product.type == ProductType.OTHERS && product.isImported) {
            return product.price *  ( baseTax +  additionalTax) ;
        }
        else if(product.type != ProductType.OTHERS && product.isImported ) {
            return product.price * additionalTax;
        }
        else if(product.type == ProductType.OTHERS) {
            return product.price *  baseTax;
        }
        else {
            return 0f;
        }
    }

    public double round(double value) {
       return 0.05 *(Math.ceil(value * 100/5));
    }


}
