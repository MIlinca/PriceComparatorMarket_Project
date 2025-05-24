package com.project.PriceComparatorMarket.Services;

import com.project.PriceComparatorMarket.Models.Discount;
import com.project.PriceComparatorMarket.Models.Price;
import com.project.PriceComparatorMarket.Models.Product;
import com.project.PriceComparatorMarket.Models.Store;
import com.project.PriceComparatorMarket.Repositories.DiscountRepository;
import com.project.PriceComparatorMarket.Repositories.PriceRepository;
import com.project.PriceComparatorMarket.Repositories.ProductRepository;
import com.project.PriceComparatorMarket.Repositories.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Service
public class CsvService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final PriceRepository priceRepository;
    private final DiscountRepository discountRepository;

    public CsvService(ProductRepository productRepository, StoreRepository storeRepository, PriceRepository priceRepository, DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.priceRepository = priceRepository;
        this.discountRepository = discountRepository;
    }

    @Transactional
    public void importCsv(InputStream csvFile, String storeName, LocalDate date) throws IOException {
        Store store = storeRepository.findByName(storeName);
        if (store == null) {
            store = new Store(storeName);
            store = storeRepository.save(store);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile, StandardCharsets.UTF_8));
        String line;
        boolean firstLine = true;

        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] lines = line.split(",");
            Product product = productRepository.findById(lines[0].trim()).orElse(null);

            if (product == null) {
                product = new Product();
                product.setId(lines[0].trim());
                product.setName(lines[1].trim());
                product.setCategory(lines[2].trim());
                product.setBrand(lines[3].trim());
                product.setPackageQuantity(Double.parseDouble(lines[4].trim()));
                product.setPackageUnit(lines[5].trim());
                productRepository.save(product);
            }
            System.out.println(product.getName());

            Price price = new Price();
            price.setProduct(product);
            price.setPrice(Double.parseDouble(lines[6].trim()));
            price.setCurrency(lines[7].trim());
            price.setStore(store);
            price.setDate(date);
            priceRepository.save(price);
        }
    }

    @Transactional
    public void importCsvDiscount(InputStream csvFile, String storeName) throws IOException {
        Store store = storeRepository.findByName(storeName);
        if (store == null) {
            store = new Store(storeName);
            store = storeRepository.save(store);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile,StandardCharsets.UTF_8));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        boolean firstLine = true;
        String line;

        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                firstLine = false; // skip header
                continue;
            }

            String[] lines = line.split(",");

            Product product = productRepository.findById(lines[0].trim())
                    .orElseThrow(() -> new NoSuchElementException("Product " + lines[1].trim() + " not found"));

            Discount discount = new Discount();
            discount.setProduct(product);
            discount.setStore(store);
            discount.setFromDate(LocalDate.parse(lines[6].trim(), formatter)); // from_date (dd/MM/yyyy)
            discount.setToDate(LocalDate.parse(lines[7].trim(), formatter));   // to_date (dd/MM/yyyy)
            discount.setPercentageOfDiscount(Integer.parseInt(lines[8].trim()));

            discountRepository.save(discount);
        }
    }
}
