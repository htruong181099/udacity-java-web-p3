package com.udacity.jdnd.course3.critter.utility;

import com.udacity.jdnd.course3.critter.user.model.EmployeeSkill;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class EmployeeSkillsConverter implements AttributeConverter<Set<EmployeeSkill>, String> {
    @Override
    public String convertToDatabaseColumn(Set<EmployeeSkill> skills) {
        if (skills == null || skills.isEmpty())
            return "";

        Set<String> toString = skills
                .stream()
                .map(EmployeeSkill::name)
                .collect(Collectors.toSet());

        return String.join(", ", toString);
    }

    @Override
    public Set<EmployeeSkill> convertToEntityAttribute(String skills) {
        if (skills == null || skills.isEmpty())
            return null;

        Set<String> asStrings = Arrays.stream(skills.split("\\s*,\\s*"))
                .collect(Collectors.toSet());
        return asStrings.stream().map(e->EmployeeSkill.valueOf(e.trim())).collect(Collectors.toSet());
    }
}
