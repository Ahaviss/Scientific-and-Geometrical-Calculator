package com.ahaviss.generators;

import com.ahaviss.utils.ProjectUtils;

import java.time.Duration;
import java.time.LocalTime;

public class GenerateAllData {
    public static void main(String[] args) {
        int data = ProjectUtils.getValidInt("How many test cases would you like to generate per topic?", true);
        LocalTime start = LocalTime.now();
        SciCalcDataGenerator.main(new String[]{"-generateAllData", String.valueOf(data)});
        ThreeDDataGeneratorVolume.main(new String[]{"-generateAllData", String.valueOf(data)});
        TwoDDataGenerator.main(new String[]{"-generateAllData", String.valueOf(data)});
        ThreeDDataGeneratorSA.main(new String[]{"-generateAllData", String.valueOf(data)});
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(start, end);
        System.out.printf("Generation complete in %d milliseconds!%n", duration.toMillis());
    }
}
