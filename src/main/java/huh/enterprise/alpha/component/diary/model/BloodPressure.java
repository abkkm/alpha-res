package huh.enterprise.alpha.component.diary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Blood pressure is measured using two numbers.
 * The first number, called systolic blood pressure, measures the pressure in your blood vessels when your heart beats.
 * The second number, called diastolic blood pressure, measures the pressure in your blood vessels when your heart rests between beats.
 * If the measurement reads 120 systolic and 80 diastolic, you would say, “120 over 80,” or write, “120/80 mmHg.”
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class BloodPressure {
    private String systolic;
    private String diastolic;
}
