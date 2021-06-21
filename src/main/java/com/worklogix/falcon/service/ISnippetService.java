package com.worklogix.falcon.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

public interface ISnippetService {

    public void createSnippet(String snippetName, String snippetDescription, String language, String category, String framework, String code) throws IOException;

    public int updateSnippet(String id, String code);

    public String getSnippets();

    public String getSnippet(String id);

}
