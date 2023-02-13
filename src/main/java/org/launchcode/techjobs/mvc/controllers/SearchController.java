package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
//    The displaySearchResults method should take in a Model parameter. ✅
//    The method should also take in two other parameters, specifying the type of search and the search term. ✅
//    If the user enters “all” in the search box, or if they leave the box empty, call the findAll() method from JobData.
//    Otherwise, send the search information to findByColumnAndValue. ✅
//    In either case, store the results in a jobs ArrayList. ✅
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        ArrayList<Job> jobs;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);

        return "search";
    }
}
