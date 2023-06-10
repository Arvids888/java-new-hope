package com.app.services;

import com.app.dao.CatalogDao;
import com.app.model.CatalogItem;
import com.app.model.Category;
import com.app.model.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private CatalogDao catalogDao;

    public List<Category> getCategories() {
        return catalogDao.getCategories();
    }

    public void storeCategory(Category category) {
        catalogDao.storeCategory(category);
    }

    public List<Subcategory> getSubcategories() {
        return catalogDao.getSubcategories();
    }

    public List<Subcategory> getSubcategories(long id) {
        return catalogDao.getSubcategories(id);
    }

    public void storeSubcategory(Subcategory subcategory) {
        catalogDao.storeSubcategory(subcategory);
    }

    public List<CatalogItem> getItems() {
        return catalogDao.getItems();
    }

    public List<CatalogItem> getItemsById(long id) {
        return catalogDao.getItemsById(id);
    }

    public void storeItem(CatalogItem item) {
        String newPrice = item.getPrice().toString().replace(',', '.');
        item.setPrice(new BigDecimal(newPrice));
        catalogDao.storeItem(item);
    }
}
