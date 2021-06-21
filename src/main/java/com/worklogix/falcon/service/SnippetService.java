package com.worklogix.falcon.service;

import com.worklogix.falcon.dao.ProjectDao;
import com.worklogix.falcon.dao.SnippetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SnippetService implements ISnippetService {

    private final SnippetDao snippetDao;

    @Autowired
    public SnippetService(SnippetDao snippetDao){
        this.snippetDao = snippetDao;
    }

    @Override
    public void createSnippet(String snippetName, String snippetDescription, String language, String category, String framework, String code) throws IOException {
        snippetDao.createSnippet(snippetName,snippetDescription,language, category, framework, code);
    }

    @Override
    public int updateSnippet(String id, String code) {
        return snippetDao.updateSnippet(id, code);
    }

    public String getSnippets(){
        return snippetDao.getSnippets();
    }

    public String getSnippet(String id){
        return snippetDao.getSnippet(id);
    }


}
