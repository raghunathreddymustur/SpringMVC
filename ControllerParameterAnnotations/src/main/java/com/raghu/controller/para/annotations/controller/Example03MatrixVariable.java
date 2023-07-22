package com.raghu.controller.para.annotations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class Example03MatrixVariable {
    // curl http://localhost:8080/example03A/employees/id=1
    @GetMapping("/example03A/employees/{id}")
    @ResponseBody
    public String example03A(@MatrixVariable("id") int id) {
        return String.format("Received request id = [%d]\n", id);
    }

    // curl "http://localhost:8080/example03B/employees/id=1;name=John"
    @GetMapping("/example03B/employees/{employee}")
    @ResponseBody
    public String example03B(@MatrixVariable("id") int id, @MatrixVariable("name") String name) {
        return String.format("Received request id = [%d], name = [%s]\n", id, name);
    }

    // curl "http://localhost:8080/example03C/employees/id=1;name=John;department=HR/city/code=NYC;location=US"
    @GetMapping("/example03C/employees/{employee}/city/{city}")
    @ResponseBody
    public String example03C(@MatrixVariable(pathVar = "employee", name = "department") String department, @MatrixVariable(pathVar = "city", name = "location") String location) {
        return String.format("Received request department = [%s], cityMatrixVariables = [%s]\n", department, location);
    }

    // curl "http://localhost:8080/example03D/employees/id=1;name=John;city=NYC"
    @GetMapping("/example03D/employees/{employee}")
    @ResponseBody
    public String example03D(@MatrixVariable Map<String, String> matrixVariables) {
        return String.format("Received request matrixVariables = [%s]\n", matrixVariables);
    }

    // curl "http://localhost:8080/example03E/employees/id=1;name=John;department=HR/city/code=NYC;location=US"
    @GetMapping("/example03E/employees/{employee}/city/{city}")
    @ResponseBody
    public String example03E(@MatrixVariable Map<String, String> matrixVariables) {
        return String.format("Received request matrixVariables = [%s]\n", matrixVariables);
    }

    // curl "http://localhost:8080/example03F/employees/id=1;name=John;department=HR/city/code=NYC;location=US"
    @GetMapping("/example03F/employees/{employee}/city/{city}")
    @ResponseBody
    public String example03F(@MatrixVariable(pathVar = "employee") Map<String, String> employeeMatrixVariables, @MatrixVariable(pathVar = "city") Map<String, String> cityMatrixVariables) {
        return String.format("Received request employeeMatrixVariables = [%s], cityMatrixVariables = [%s]\n", employeeMatrixVariables, cityMatrixVariables);
    }
}
