package com.worklogix.falcon.api;

import com.worklogix.falcon.dao.SnippetDao;
import com.worklogix.falcon.service.ISnippetService;
import com.worklogix.falcon.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/snippet")
public class SnippetController implements ISnippetService {

    private final SnippetService snippetService;

    @Autowired
    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @PostMapping
    public void createSnippet(String snippetName, String snippetDescription, String language, String category, String code) throws IOException {
       snippetService.createSnippet(snippetName, snippetDescription, language, category, code);
    }

    @GetMapping
    public String getSnippets(){
        return snippetService.getSnippets();
    }


}
