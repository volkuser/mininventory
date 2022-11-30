package com.example.mininventory.controllers;

import com.example.mininventory.models.Commission;
import com.example.mininventory.services.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Controller
public class CommissionController {
    @Autowired
    private CommissionService commissionService;

    @GetMapping("/commission")
    public String show(Model model) {
        loadList(model);
        return "commission_control";
    }

    private void loadList(Model model){
        List<Commission> commissions = StreamSupport.stream(commissionService.getAll().spliterator(),
                false).toList();
        model.addAttribute("commissions", commissions);
    }

    @GetMapping("/commission/exact")
    public String exactSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Commission> commissions = commissionService.getResultExactSearch(query);

            model.addAttribute("commissions", commissions);

            return "commission_control";
        } else return "redirect:/commission";
    }

    @GetMapping("/commission/imprecise")
    public String impreciseSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Commission> commissions = commissionService.getResultImpreciseSearch(query);

            model.addAttribute("commissions", commissions);

            return "commission_control";
        } else return "redirect:/commission";
    }

    @PostMapping("/commission")
    public String add(@Valid @ModelAttribute(value = "selectedCommission") Commission commission,
                      BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else commissionService.save(commission);

        loadList(model);
        return "commission_control";
    }
}
