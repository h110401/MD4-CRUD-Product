package rikkei.academy.service.product;

import rikkei.academy.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceIMPL implements IProductService {

    private static final Map<Integer, Product> products = new HashMap<>();
    private static int lastId = 0;

    static {
        lastId++;
        products.put(1, new Product(1, "N1", 10, "D1", "M1"));
        lastId++;
        products.put(2, new Product(2, "N2", 20, "D2", "M2"));
        lastId++;
        products.put(3, new Product(3, "N3", 30, "D3", "M3"));
        lastId++;
        products.put(4, new Product(4, "N4", 40, "D4", "M4"));
        lastId++;
        products.put(5, new Product(5, "N5", 50, "D5", "M5"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        lastId++;
        product.setId(lastId);
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public List<Product> search(String search) {
        List<Product> productList = new ArrayList<>();
        for (Product p : findAll()) {
            if (p.getName().toLowerCase().contains(search.trim().toLowerCase())) {
                productList.add(p);
            }
        }
        return productList;
    }
}
