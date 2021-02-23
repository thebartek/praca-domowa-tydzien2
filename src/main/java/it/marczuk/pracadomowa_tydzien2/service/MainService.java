package it.marczuk.pracadomowa_tydzien2.service;

import it.marczuk.pracadomowa_tydzien2.entity.Product;
import it.marczuk.pracadomowa_tydzien2.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class MainService {
    private ShopRepository repository;
    Random generator = new Random();

    @Value("${product.count}")
    private int productCount;

    @Value("${product.tax}")
    private int tax;

    @Value("${product.discount}")
    private int discount;

    @Autowired
    public MainService(ShopRepository repository) {
        this.repository = repository;
    }

    public void addRandomProduct(){
        for(int i=0; i<productCount; i++){
            repository.addProduct(new Product("Product " + i, BigDecimal.valueOf(generator.nextInt(250)+50)));
        }
    }

    public BigDecimal getTotalPrice() {
        List<Product> cart = repository.getCart();

        BigDecimal count = new BigDecimal(0);
        for(Product product: cart){
            count = count.add(product.getPrice());
        }
        return count;
    }

    public BigDecimal getTotalPriceWithTax() {
        BigDecimal totalPrice = getTotalPrice();
        BigDecimal pTax = BigDecimal.valueOf(tax * 0.01);
        BigDecimal toalPriceWithTax = totalPrice.add(totalPrice.multiply(pTax));

        return  toalPriceWithTax;
    }

    public BigDecimal getTotalPriceWithTaxAndDiscount() {
        BigDecimal totalPrice = getTotalPriceWithTax();
        BigDecimal pDiscount = BigDecimal.valueOf(discount);
        BigDecimal totalPriceWithTaxAndDiscount = totalPrice.subtract(pDiscount);

        return  totalPriceWithTaxAndDiscount;
    }
}
