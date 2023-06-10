package com.app.dao;

import com.app.model.CatalogItem;
import com.app.model.Category;
import com.app.model.Subcategory;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CatalogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getCategories() {
        RowMapper<Category> rowMapper = (rs, rowNumber) -> mapCategory(rs);
        return jdbcTemplate.query("SELECT * FROM categories", rowMapper);
    }

    private Category mapCategory(ResultSet rs) throws SQLException {
        Category category = new Category();

        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));

        return category;
    }

    public void storeCategory(Category category) {
        jdbcTemplate.update("INSERT INTO categories (name) VALUES (?)", category.getName());
    }

    public List<Subcategory> getSubcategories() {
        RowMapper<Subcategory> rowMapper = (rs, rowNumber) -> mapSubcategory(rs);
        return jdbcTemplate.query("SELECT s.id AS s_id, s.name AS s_name, c.id AS c_id, c.name AS c_name " +
                "FROM subcategories s " +
                "INNER JOIN categories c ON s.category_id = c.id", rowMapper);
    }

    public List<Subcategory> getSubcategories(long categoryId) {
        RowMapper<Subcategory> rowMapper = (rs, rowNumber) -> mapSubcategory(rs);
        return jdbcTemplate.query("SELECT s.id AS s_id, s.name AS s_name, c.id AS c_id, c.name AS c_name " +
                "FROM subcategories s " +
                "INNER JOIN categories c ON s.category_id = c.id " +
                "WHERE s.category_id = ?", rowMapper, categoryId);
    }

    private Subcategory mapSubcategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("c_id"));
        category.setName(rs.getString("c_name"));

        Subcategory subcategory = new Subcategory();
        subcategory.setCategory(category);
        subcategory.setId(rs.getLong("s_id"));
        subcategory.setName(rs.getString("s_name"));

        return subcategory;
    }

    public void storeSubcategory(Subcategory subcategory) {
        jdbcTemplate.update("INSERT INTO subcategories (name, category_id) VALUES (?, ?)",
                subcategory.getName(), subcategory.getCategory().getId());
    }

    public List<CatalogItem> getItems() {
        RowMapper<CatalogItem> rowMapper = ((rs, rowNumber) -> mapItem(rs));
        return jdbcTemplate.query("SELECT c.id AS item_id, c.name AS item_name, c.description, c.price, " +
                "s.id AS sub_id, s.name AS sub_name, t.id AS cat_id, t.name AS cat_name " +
                "FROM catalog c " +
                "INNER JOIN subcategories s ON c.subcategory_id = s.id " +
                "INNER JOIN categories t ON s.category_id = t.id", rowMapper);
    }

    public List<CatalogItem> getItemsById(long id) {
        RowMapper<CatalogItem> rowMapper = ((rs, rowNumber) -> mapItem(rs));
        return jdbcTemplate.query("SELECT c.id AS item_id, c.name AS item_name, c.description, c.price, " +
                "s.id AS sub_id, s.name AS sub_name, t.id AS cat_id, t.name AS cat_name " +
                "FROM catalog c " +
                "INNER JOIN subcategories s ON c.subcategory_id = s.id " +
                "INNER JOIN categories t ON s.category_id = t.id " +
                "WHERE c.subcategory_id = ?", rowMapper, id);
    }

    private CatalogItem mapItem(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("cat_id"));
        category.setName(rs.getString("cat_name"));

        Subcategory subcategory = new Subcategory();
        subcategory.setCategory(category);
        subcategory.setId(rs.getLong("sub_id"));
        subcategory.setName(rs.getString("sub_name"));

        CatalogItem item = new CatalogItem();
        item.setSubcategory(subcategory);
        item.setId(rs.getLong("item_id"));
        item.setName(rs.getString("item_name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(new BigDecimal(rs.getString("price")));
//        item.setBrandId(rs.getLong("brand_id"));
//        item.setImage(rs.getString("image"));

        return item;

    }

    public void storeItem(CatalogItem item) {
        jdbcTemplate.update("INSERT INTO catalog (subcategory_id, name, description, price) VALUES (?, ?, ?, ?)",
                item.getSubcategory().getId(), item.getName(), item.getDescription(), item.getPrice());
    }
}
