package it.marczuk.pracadomowa_tydzien2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("PLUS")
public class ShopServicePlus {
    private MainService mainService;

    @Autowired
    public ShopServicePlus(MainService mainService) {
        this.mainService = mainService;
        mainService.addRandomProduct();
        System.out.println("Total price with tax: " + mainService.getTotalPriceWithTax());
    }
}
