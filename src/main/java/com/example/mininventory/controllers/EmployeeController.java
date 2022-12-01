package com.example.mininventory.controllers;

import com.example.mininventory.models.Employee;
import com.example.mininventory.models.User;
import com.example.mininventory.services.EmployeeService;
import com.example.mininventory.services.UserService;
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
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;

    @GetMapping("/employee")
    public String show(Model model) {
        loadListWithSubList(model);
        return "employee_control";
    }

    private void loadSubList(Model model){
        List<User> users = StreamSupport.stream(userService.getAll().spliterator(),
                false).toList();
        model.addAttribute("users", users);
    }

    private void loadListWithSubList(Model model){
        List<Employee> employees = StreamSupport.stream(employeeService.getAll().spliterator(),
                false).toList();
        model.addAttribute("employees", employees);

        loadSubList(model);
    }

    @GetMapping("/employee/exact")
    public String exactSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Employee> employees = employeeService.getResultExactSearch(query);

            loadSubList(model);

            model.addAttribute("employees", employees);

            return "employee_control";
        } else return "redirect:/employee";
    }

    @GetMapping("/employee/imprecise")
    public String impreciseSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Employee> employees = employeeService.getResultImpreciseSearch(query);

            loadSubList(model);

            model.addAttribute("employees", employees);

            return "employee_control";
        } else return "redirect:/employee";
    }

    @PostMapping("/employee")
    public String add(@Valid @ModelAttribute(value = "selectedEmployee") Employee employee,
                      BindingResult bindingResult, Model model, // specific is down
                      @RequestParam(value = "user", required = false) String userAsString){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else employeeService.save(employee);

        loadListWithSubList(model);
        return "employee_control";
    }
}
