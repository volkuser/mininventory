package com.example.mininventory.controllers;

import com.example.mininventory.models.Commission;
import com.example.mininventory.services.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class CommissionItemController {
    @Autowired
    private CommissionService commissionService;

    @GetMapping("/commission/more/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "commission_item_control";
    }

    private void getAndLoad(Model model, String id){
        Commission commission = commissionService.getById(Long.parseLong(id));
        model.addAttribute("selectedCommission", commission);
    }

    @PostMapping("/commission/more/{id}")
    public String update(@Valid @ModelAttribute("selectedCommission") Commission commission,
                         BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else commissionService.save(commission);

        getAndLoad(model, commission.getId().toString());
        return "commission_item_control";
    }

    @PostMapping("/commission/more/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model){
        try{
            commissionService.deleteById(Long.parseLong(id));
            return "redirect:/commission";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/commission/more/{id}";
        }
    }
}
