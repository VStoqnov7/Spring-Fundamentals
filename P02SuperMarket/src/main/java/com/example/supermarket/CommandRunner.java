package com.example.supermarket;

import com.example.supermarket.models.dto.ProductViewDTO;
import com.example.supermarket.models.dto.SellerViewDTO;
import com.example.supermarket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandRunner implements CommandLineRunner {
    private final Scanner scanner;
    private final CategoryService categoryService;
    private final TownService townService;
    private final ShopService shopService;
    private final SellerService sellerService;
    private final ProductService productService;

    @Autowired
    public CommandRunner(Scanner scanner, CategoryService categoryService, TownService townService, ShopService shopService, SellerService sellerService, ProductService productService) {
        this.scanner = scanner;
        this.categoryService = categoryService;
        this.townService = townService;
        this.shopService = shopService;
        this.sellerService = sellerService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hi");
        while (true) {
            System.out.println("Choose option from:" +
                    "\n1 - for Add Category" +
                    "\n2 - for Add Town" +
                    "\n3 - for Add Shop" +
                    "\n4 - for Add Seller" +
                    "\n5 - for Add Product" +
                    "\n6 - for Set seller new manager" +
                    "\n7 - for Distributing product in shops" +
                    "\n8 - for Showing all sellers in Shop" +
                    "\n9 - for Showing all products in Shop");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    this.addCategory();
                    break;
                case "2":
                    this.addTown();
                    break;
                case "3":
                    this.addShop();
                    break;
                case "4":
                    this.addSeller();
                    break;
                case "5":
                    this.addProduct();
                    break;
                case "6":
                    this.setManager();
                    break;
                case "7":
                    this.setProductDistribution();
                    break;
                case "8":
                    this.showAllSellersFromShop();
                    break;
                case "9":
                    this.showAllProductsFromShop();
                    break;
            }
            System.out.println("==================================");
        }

    }

    private void showAllProductsFromShop() {
        System.out.println("Enter shop name:");
        String shopName = scanner.nextLine();
        boolean isValid = this.shopService.checkShop(shopName);
        if (!isValid){
            System.out.println("Invalid shop");
            return;
        }
        List<ProductViewDTO> productViewModels = this.shopService
                .findAllProductsFromShop(shopName);

        productViewModels.forEach(s-> System.out.printf("%s - %.2f$%n",s.getName(),s.getPrice()));
    }

    private void showAllSellersFromShop() {
        System.out.println("Enter shop name:");
        String shopName = scanner.nextLine();
        boolean isValid = this.shopService.checkShop(shopName);
        if (!isValid){
            System.out.println("Invalid shop");
            return;
        }
        List<SellerViewDTO> sellers = this.shopService.findAllSellersFromShop(shopName);

        sellers.forEach(s -> System.out.printf("%s %s%n",s.getFirstName(),s.getLastName()));
    }

    private void setProductDistribution() {
        System.out.println("Enter product name:");
        String productName = scanner.nextLine();
        boolean isValid = this.productService.checkProduct(productName);
        if (!isValid){
            System.out.println("Invalid product");
            return;
        }
        System.out.println("Enter product distribution in Shops names in format [shopName1 shopName2 ...]:");
        String[] shops = scanner.nextLine().split("\\s+");
        this.productService.addProductDistribution(productName,shops);
    }

    private void setManager() {
        System.out.println("Enter seller first and last names:");
        String[] seller = scanner.nextLine().split("\\s+");
        System.out.println("Enter manager first and last names:");
        String[] manager = scanner.nextLine().split("\\s+");
        boolean isValidSeller = this.sellerService.checkSeller(seller);
        boolean isValidManager = this.sellerService.checkSeller(manager);
        if (isValidSeller && isValidManager){
            this.sellerService.addManager(seller,manager);
        }else {
            System.out.println(isValidSeller ? "Invalid manager." : "Invalid seller.");
            System.out.println("Error! Cannot add manager");
        }
    }

    private void addProduct() {
        System.out.println("Enter product details in format: name price bestBefore(dd-MM-yyyy) category");
        String[] details = scanner.nextLine().split("\\s+");
        try {
            this.productService.addProduct(details);
        } catch (Exception e) {
            System.out.println("Error! Cannot add product");
        }
    }

    private void addSeller() {
        System.out.println("Enter seller details in format: firstName lastName age salary shopName");
        String[] details = scanner.nextLine().split("\\s+");
        try {
            this.sellerService.addSeller(details);
        } catch (Exception e) {
            System.out.println("Error! Cannot add seller");
        }
    }

    private void addShop() {
        System.out.println("Enter shop details in format: name address town");
        String[] details = scanner.nextLine().split("\\s+");
        try {
            this.shopService.addShop(details);
        } catch (Exception e) {
            System.out.println("Error! Cannot add shop!");
        }

    }

    private void addTown() {
        System.out.println("Enter town name:");
        String townName = scanner.nextLine();

        try {
            this.townService.addTown(townName);
        } catch (Exception e) {
            System.out.println("Error! Cannot add town!");
        }
    }

    private void addCategory() {
        System.out.println("Enter category name:");
        String categoryName = scanner.nextLine();

        try {
            this.categoryService.addCategory(categoryName);
        } catch (Exception e) {
            System.out.println("Error! Cannot add category!");
        }
    }
}
