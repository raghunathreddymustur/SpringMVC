package com.raghu.controller.parameters.controller;

import com.spring.professional.exam.tutorial.module05.question09.dao.AddressDao;
import com.spring.professional.exam.tutorial.module05.question09.ds.Address;
import com.spring.professional.exam.tutorial.module05.question09.ds.Country;
import com.spring.professional.exam.tutorial.module05.question09.ds.Person;
import com.spring.professional.exam.tutorial.module05.question09.ds.ShortAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

// SessionStatus + class-level @SessionAttributes with multi step form processing
@Controller
@SessionAttributes({"person", "shortAddress", "country"})
public class Example16SessionAttributes {

    @Autowired
    private AddressDao addressDao;

    // Visit http://localhost:8080/actionP in your browser
    @GetMapping("/actionP")
    public String actionP(Model model) {
        model.addAttribute("addresses", addressDao.findAll());

        return "addresses-list";
    }

    // This is a first step in multi step address submission
    @GetMapping("/actionP/address/step1")
    public String actionPStep1(@ModelAttribute("person") Person person) {
        return "address-form-step1";
    }

    // This is a post submission method for first step of multi step address submission
    @PostMapping("/actionP/address/step1")
    public String actionPStep1(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "address-form-step1";
        else
            return "redirect:/actionP/address/step2";
    }

    // This is a second step in multi step address submission
    @GetMapping("/actionP/address/step2")
    public String actionPStep2(@ModelAttribute("shortAddress") ShortAddress shortAddress) {
        return "address-form-step2";
    }

    // This is a post submission method for second step of multi step address submission
    @PostMapping("/actionP/address/step2")
    public String actionPStep2(@ModelAttribute("shortAddress") @Valid ShortAddress shortAddress, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "address-form-step2";
        else
            return "redirect:/actionP/address/step3";
    }

    // This is a third step in multi step address submission
    @GetMapping("/actionP/address/step3")
    public String actionPStep3(@ModelAttribute("country") Country country) {
        return "address-form-step3";
    }

    // This is a post submission method for third step of multi step address submission
    @PostMapping("/actionP/address/step3")
    public String actionPStep2(@ModelAttribute("country") @Valid Country country, BindingResult bindingResult, @ModelAttribute("shortAddress") ShortAddress shortAddress, @ModelAttribute("person") Person person, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors())
            return "address-form-step3";
        else {
            Address address = new Address(person, shortAddress, country);
            addressDao.save(address);
            sessionStatus.setComplete();

            return "redirect:/actionP";
        }
    }

    @ModelAttribute("person")
    public Person person() {
        return new Person();
    }

    @ModelAttribute("shortAddress")
    public ShortAddress shortAddress() {
        return new ShortAddress();
    }

    @ModelAttribute("country")
    public Country country() {
        return new Country();
    }
}
