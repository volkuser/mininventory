package com.example.mininventory.controllers;

import com.example.mininventory.services.CommissionService;
import com.example.mininventory.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.StreamSupport;

@Controller
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ALSO_USER')")
public class CommissionListOfEmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CommissionService commissionService;

    @GetMapping("/employee/more/{id}/list")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "employee_commission_list";
    }

    private void getAndLoad(Model model, String id){
        model.addAttribute("actualId", id);
        // personal
        model.addAttribute("personalCommissions",
                employeeService.getById(Long.parseLong(id)).getCommissions().stream().toList());
        // all
        model.addAttribute("commissions", StreamSupport.stream(commissionService.getAll().spliterator(),
                false).toList());
    }

    @PostMapping("/employee/more/{id}/list")
    public String add(@PathVariable("id") String id,
                      @RequestParam(value = "commission") String commissionIdAsString, Model model){
        employeeService.saveCommissionForHim(employeeService.getById(Long.parseLong(id)),
                commissionService.getById(Long.parseLong(commissionIdAsString)));

        getAndLoad(model, id);
        return "employee_commission_list";
    }

    @PostMapping("/commission/more/{id}/list/{relationId}/delete")
    public String delete(@PathVariable("id") String id, @PathVariable("relationId") String relationId,
                         Model model){
        try {
            employeeService.deleteCommissionForHim(employeeService.getById(Long.parseLong(id)),
                    commissionService.getById(Long.parseLong(relationId)));
        } catch (Exception ignored) { }
        getAndLoad(model, id);
        return "employee_commission_list";
    }
}
