package com.app.controllers;

import com.app.model.Language;
import com.app.services.LangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LangController {
    @Autowired
    private LangService langService;

    @GetMapping("/translationExample")
    public String getTranslationExamplePage(Model model, HttpSession session) {
        if (session.getAttribute("lang") == null) {
            session.setAttribute("lang", 1);
        }
        long langId = Long.parseLong(session.getAttribute("lang").toString());

        model.addAttribute("translations", langService.getTranslations(langId, "homePage"));

        return "translation_example";
    }

    @GetMapping("/setLang/{langId}")
    @ResponseBody
    public void setLang(@PathVariable(value = "langId") long langId, HttpSession session) {
        session.setAttribute("lang", langId);
    }

    @GetMapping("/language")
    public String getLanguage(Model model) {
        model.addAttribute("languageData", new Language());

        return "language";
    }

    @PostMapping("/language")
    public String languageRegister(@ModelAttribute Language language, Model model) {
        langService.storeLanguage(language);
        return "redirect:/language";
    }
}
