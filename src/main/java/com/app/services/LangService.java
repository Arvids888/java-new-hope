package com.app.services;

import com.app.dao.LangDao;
import com.app.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
}
