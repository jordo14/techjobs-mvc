package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.OverridesAttribute;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<HashMap<String, String>> results;

        if (searchType.equals("all")) {
            if (searchTerm != null) {
                results = JobData.findByValue(searchTerm);
            } else {
                results = JobData.findAll();
            }

        } else {
            results = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        Integer jobSize = results.size();


        model.addAttribute("jobSize", jobSize);
        model.addAttribute("results", results);
        model.addAttribute("columns", ListController.columnChoices);

        return "search";
    }
}
