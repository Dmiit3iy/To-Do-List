package org.dmit3ii.todolist.service;

import org.dmit3ii.todolist.client.ApiClient;
import org.dmit3ii.todolist.model.Holiday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ValidationServiceTest {
    @Mock
    private ApiClient apiClient;

    @InjectMocks
    private ValidationService validationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getNextAvailableDate() {
        LocalDate date = LocalDate.of(2025, 3, 8); // Суббота
        String countryCode = "RU";
        LocalDate expectedNextAvailableDate = LocalDate.of(2025, 3, 10); // Понедельник
        Holiday holiday = new Holiday();
        holiday.setDate(LocalDate.of(2025, 3, 8)); // Праздник 8 марта
        holiday.setCountryCode("RU");

        when(apiClient.getAllHolidays(2025, countryCode)).thenReturn(List.of(holiday));

        LocalDate result = validationService.getNextAvailableDate(date, countryCode);

        assertEquals(expectedNextAvailableDate, result, "Ближайшая доступная дата должна быть в понедельник 2025-03-10");
    }
}