/*
 * Copyright [2026] [Ahaviss]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
