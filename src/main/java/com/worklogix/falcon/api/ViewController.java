package com.worklogix.falcon.api;


import com.worklogix.falcon.dao.ViewEngineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ve")
public class ViewController {

    private final ViewEngineDao viewEngine;

    @Autowired
    public ViewController(ViewEngineDao viewEngineDao) {
        this.viewEngine = viewEngineDao;
    }

    @GetMapping
    public String getView(@RequestParam("viewName") String viewName) {
        return viewEngine.getView(viewName);
    }
}
