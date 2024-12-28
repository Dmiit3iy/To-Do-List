package org.dmit3ii.todolist.controller;

import lombok.AllArgsConstructor;
import org.dmit3ii.todolist.client.ApiClient;
import org.dmit3ii.todolist.model.Holiday;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@AllArgsConstructor
@RestController
public class TestController {
    private ApiClient apiClient;


    @GetMapping
    public void test1() {
        System.out.println(apiClient.getAllHolidays(2024,"Ru"));
    }
}
