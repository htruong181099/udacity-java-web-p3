package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(
        value = "SELECT DISTINCT employees.*\n" +
                "    FROM employees\n" +
                "    JOIN employee_skills es ON employees.id = es.employee_id\n" +
                "    JOIN employee_availability ea ON employees.id = ea.employee_id\n" +
                "    WHERE es.skill_name IN :skills\n" +
                "    AND ea.availability = :dayOfWeek\n" +
                "    AND employees.id NOT IN ( \n" +
                "    SELECT schedule_employees.employee_id\n" +
                "        FROM schedules\n" +
                "        JOIN schedule_employees ON schedules.id = schedule_employees.schedule_id\n" +
                "        WHERE schedule_date = :date\n" +
                ")",
    nativeQuery = true)
    List<Employee> findBySkillsAndAvailability(List<String> skills, LocalDate date, String dayOfWeek);
    List<Employee> findByDaysAvailableContains(DayOfWeek dayOfWeek);
    List<Employee> findByIdIn(List<Long> idList);
}
