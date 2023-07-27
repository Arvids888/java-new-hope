package com.app.dao;

import com.app.model.Language;
import com.app.model.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class LangDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public HashMap<String, String> getTranslations(long landId, String page) {
        return jdbcTemplate.query("SELECT key, text FROM translations " +
        "WHERE lang_id = ? and page = ? or lang_id = ? and page = 'all'",
                (ResultSet rs) -> {
                    HashMap<String, String> result = new HashMap<>();

                    while (rs.next()) {
                        result.put(rs.getString("key"), rs.getString("text"));
                    }

                    return result;
                }, landId, page, landId);
    }

    public List<Language> getLanguage() {
        RowMapper<Language> rowMapper = (rs, rowNumber) -> mapLanguage(rs);

        return jdbcTemplate.query("SELECT * FROM langs", rowMapper);
    }

    private Language mapLanguage(ResultSet rs) throws SQLException {
        Language language = new Language();
        language.setId(rs.getLong("id"));
        language.setName(rs.getString("name"));
        language.setLabel(rs.getString("label"));

        return language;

    }

    public void storeLanguages(Language language) {
        jdbcTemplate.update("INSERT INTO langs (name, label) VALUES (?, ?)",
                language.getName(), language.getLabel());

    }

    private List<Translation> getTranslation() {
        RowMapper<Translation> rowMapper = (rs, rowNumber) -> mapTranslation(rs);

        return jdbcTemplate.query("SELECT t.id AS t_id, t.page AS t_page, t.text AS t_text, t.key AS t_key," +
                "l.id AS l_id, l.name AS l_name, l.label AS l_label" +
                "FROM translations t " +
                "INNER JOIN language u ON t.lang_id " +
                "INNER JOIN translations tr ON "rowMapper);
    }

    private Translation mapTranslation(ResultSet rs) throws SQLException {
        Language language = new Language();

        language.setId(rs.getLong("id"));
        language.setName(rs.getString("name"));
        language.setLabel(rs.getString("label"));

        Translation translation = new Translation();

        translation.setLanguage(language);
        translation.setId(rs.getLong("id"));
        translation.setPage(rs.getString("page"));
        translation.setText(rs.getString("text"));
        translation.setKey(rs.getString("key"));

        return translation;
    }

    public void storeTranslation(Translation translation) {
        jdbcTemplate.update("INSERT INTO translations (lang_id, page, text, key) VALUES (?, ?, ?, ?)",
        translation.getLanguage().getId(), translation.getPage(), translation.getText(), translation.getKey());
    }
}
