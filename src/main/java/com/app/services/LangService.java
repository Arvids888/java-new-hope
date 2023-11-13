package com.app.services;

import com.app.dao.LangDao;
import com.app.model.Language;
import com.app.model.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LangService {
    @Autowired
    private LangDao langDao;

    public HashMap<String, String> getTranslations(long langId, String page) {
        return langDao.getTranslations(langId, page);
    }

    public void storeLanguage(Language language) {
        langDao.storeLanguages(language);
    }

    public List<Translation> getTranslation() {
        return langDao.getTranslation();
    }

    public void storeTranslation(Translation translation) {
        langDao.storeTranslation(translation);
    }
}
