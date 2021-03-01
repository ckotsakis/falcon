package com.worklogix.falcon.service;

import java.io.IOException;

public interface ISnippetService {

    public void createSnippet(String snippetName, String snippetDescription, String language, String Category, String code) throws IOException;

}
