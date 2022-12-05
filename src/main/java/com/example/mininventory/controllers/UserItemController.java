/*
package com.example.mininventory.controllers;

import com.example.mininventory.models.Employee;
import com.example.mininventory.models.User;
import com.example.mininventory.services.EmployeeService;
import com.example.mininventory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Controller
public class UserItemController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/user/more/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "user_item_control";
    }

    private void getAndLoad(Model model, String id){
        List<Employee> employees = StreamSupport.stream(employeeService.getAll().spliterator(),
                false).toList();
        model.addAttribute("employees", employees);

        User user = userService.getById(Long.parseLong(id));
    }

    @PostMapping("/user/more/{id}")
    public String update(@Valid @ModelAttribute(value = "selectedUser") User user,
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

        getAndLoad(model, user.getId().toString());
        return "user_item_control";
    }

    @PostMapping("/user/more/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            userService.deleteById(Long.parseLong(id));
            return "redirect:/user";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/user/more/{id}";
        }
    }
}
*/
