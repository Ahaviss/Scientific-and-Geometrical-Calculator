package com.ahaviss.generators;

import com.ahaviss.utils.ProjectUtils;

public class GenerateAllData {
    public static void main(String[] args) {
        int data = ProjectUtils.getValidInt("How many test cases would you like to generate per topic?", true);
        SciCalcDataGenerator.main(new String[]{"-generateAllData", String.valueOf(data)});
        ThreeDDataGeneratorVolume.main(new String[]{"-generateAllData", String.valueOf(data)});
        TwoDDataGenerator.main(new String[]{"-generateAllData", String.valueOf(data)});
        ThreeDDataGeneratorSA.main(new String[]{"-generateAllData", String.valueOf(data)});
        System.out.println("Generation complete!");
    }
}
