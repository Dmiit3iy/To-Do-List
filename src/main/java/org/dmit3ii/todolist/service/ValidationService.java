package org.dmit3ii.todolist.service;

import lombok.AllArgsConstructor;
import org.dmit3ii.todolist.client.ApiClient;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public final class ValidationService {
    private ApiClient apiClient;

    public boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public boolean isHoliday(LocalDate date, String countryCode) {
        return apiClient.getAllHolidays(date.getYear(), countryCode).stream().anyMatch(x -> x.getDate().equals(date));
    }

    public boolean isWeekendOrHoliday(LocalDate date, String countryCode) {
        return (isWeekend(date) || isHoliday(date, countryCode));

    }

    public LocalDate getNextAvailableDate(LocalDate date, String countryCode) {
        // Получаем следующий доступный день
        LocalDate nextDate = date.plusDays(1);
        while (isWeekendOrHoliday(nextDate, countryCode)) {
            nextDate = nextDate.plusDays(1);
        }
        return nextDate;
    }

    public void validateDay(LocalDate date, String countryCode) {
        if (isWeekendOrHoliday(date, countryCode) ){
            LocalDate nextAvailableDate = getNextAvailableDate(date, countryCode);
            throw new IllegalArgumentException("Выберите другой день — ближайший доступный " + nextAvailableDate);
        }
    }

}