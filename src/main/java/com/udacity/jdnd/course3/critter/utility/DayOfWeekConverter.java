package com.udacity.jdnd.course3.critter.utility;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class DayOfWeekConverter implements AttributeConverter<Set<DayOfWeek>, String> {
    @Override
    public String convertToDatabaseColumn(Set<DayOfWeek> daySet) {
        if (daySet == null || daySet.isEmpty())
            return "";

        Set<String> asStrings = daySet
                .stream()
                .map(DayOfWeek::name)
                .collect(Collectors.toSet());

        return String.join(", ", asStrings);
    }

    @Override
    public Set<DayOfWeek> convertToEntityAttribute(String fromDatabaseColumn) {
        if (fromDatabaseColumn == null || fromDatabaseColumn.isEmpty())
            return null;

        Set<String> asStrings = Arrays.stream(fromDatabaseColumn.split("\\s*,\\s*")).collect(Collectors.toSet());
        Set<DayOfWeek> asEnum = new HashSet<>();
        for (String code : asStrings)
            asEnum.add(DayOfWeek.valueOf(code.trim()));

        return asEnum;
    }
}
