package com.raghu.controller.para.annotations.controller;

import com.spring.professional.exam.tutorial.module05.question10.ds.Country;
import com.spring.professional.exam.tutorial.module05.question10.ds.Person;
import com.spring.professional.exam.tutorial.module05.question10.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
public class Example07RequestPart {

    // curl -F 'person={ "firstName": "John", "lastName": "Doe" };type=application/json' -F 'country={ "country": "United States" };type=application/json' http://localhost:8080/example07A
    @PostMapping("/example07A")
    @ResponseBody
    public String example07A(@RequestPart("person") String person, @RequestPart("country") String country) {
        return String.format(
                "Received person = [%s], country = [%s]\n",
                person, country
        );
    }

    // curl -F 'person={ "firstName": "John", "lastName": "Doe" };type=application/json' -F 'country={ "country": "United States" };type=application/json' http://localhost:8080/example07B
    // curl -F 'person={ "firstName": "John", "lastName": "Doe" };type=application/json' http://localhost:8080/example07B
    @PostMapping("/example07B")
    @ResponseBody
    public String example07B(@RequestPart("person") Person person, @RequestPart(value = "country", required = false) Country country) {
        return String.format(
                "Received person = [%s], country = [%s]\n",
                person, country
        );
    }

    // curl -F 'person={ "firstName": "John", "lastName": "Doe" };type=application/json' -F 'country={ "country": "United States" };type=application/json' http://localhost:8080/example07C
    // curl -F 'person={ "firstName": "John", "lastName": "Doe" };type=application/json' http://localhost:8080/example07C
    @PostMapping("/example07C")
    @ResponseBody
    public String example07C(@RequestPart("person") Person person, @RequestPart("country") Optional<Country> country) {
        return String.format(
                "Received person = [%s], country = [%s]\n",
                person, country.orElse(new Country())
        );
    }

    // curl -F 'person={ "firstName": "John", "lastName": "Doe" };type=application/json' http://localhost:8080/example07D
    // curl -F 'person={ "firstName": "John1", "lastName": "Doe" };type=application/json' http://localhost:8080/example07D
    // curl -F 'person={ "firstName": "John1", "lastName": "Doe2" };type=application/json' http://localhost:8080/example07D
    @PostMapping("/example07D")
    @ResponseBody
    public String example07D(@RequestPart("person") @Valid Person person, BindingResult bindingResult) {
        return String.format(
                "Received person = [%s], error counts = [%d]\n",
                person, bindingResult.getErrorCount()
        );
    }

    // Visit http://localhost:8080/example07E/gallery
    @GetMapping("/example07E/gallery")
    public String example07E(Model model) {
        model.addAttribute("imageNames", inMemoryStorage.getNames());

        return "gallery";
    }

    // Visit http://localhost:8080/example07E/gallery
    @GetMapping(value = "/example07E/gallery/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] example07E(@PathVariable String imageName) {
        return inMemoryStorage.getByName(imageName);
    }

    @PostMapping("/example07E/gallery")
    public String example07EUpload(@RequestPart("file") MultipartFile file) throws IOException {
        inMemoryStorage.store(file.getOriginalFilename(), file.getBytes());

        return "redirect:/example07E/gallery";
    }

    @Autowired
    private InMemoryStorage inMemoryStorage;
}
