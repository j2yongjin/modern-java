package modernjava.CompleteableFuture.example;

/**
 * Created by yjlee on 2019-01-27.
 */
public class Quote {

    private final String shopName;
    private final Double price;
    private final Discount.Code discountCode;

    public Quote(String shopName, Double price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote prase(String s){
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price , discountCode);
    }

    public Double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }

    public String getShopName() {
        return shopName;
    }
}
