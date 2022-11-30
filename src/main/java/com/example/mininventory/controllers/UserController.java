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
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/user")
    public String show(Model model) {
        loadListWithSubList(model);
        return "user_control";
    }

    private void loadSubList(Model model){
        List<User> users = StreamSupport.stream(userService.getAll().spliterator(),
                false).toList();
        model.addAttribute("users", users);
    }

    private void loadListWithSubList(Model model){
        List<User> users = StreamSupport.stream(userService.getAll().spliterator(),
                false).toList();
        model.addAttribute("users", users);

        loadSubList(model);
    }

    @GetMapping("/user/exact")
    public String exactSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<User> users = userService.getResultExactSearch(query);

            loadSubList(model);

            model.addAttribute("users", users);

            return "user_control";
        } else return "redirect:/user";
    }

    @GetMapping("/user/imprecise")
    public String impreciseSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<User> users = userService.getResultImpreciseSearch(query);

            loadSubList(model);

            model.addAttribute("users", users);

            return "user_control";
        } else return "redirect:/user";
    }

    @PostMapping("/user")
    public String add(@Valid @ModelAttribute(value = "selectedUser") User user,
                      BindingResult bindingResult, Model model, // specific is down
                      @RequestParam(value = "employee") String employeeAsString){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            try {
                user.setEmployee(employeeService.getById(Long.parseLong(employeeAsString)));
            } catch (Exception ignored) {
                user.setEmployee(userService.getById(user.getId()).getEmployee());
            }
            userService.save(user);
        }

        loadListWithSubList(model);
        return "user_control";
    }
}
