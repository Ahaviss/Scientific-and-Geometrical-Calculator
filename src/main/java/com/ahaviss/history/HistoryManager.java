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
package com.ahaviss.history;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private static final List<History> history = new ArrayList<>();
    private static BigDecimal prev;
    public static void setPrev(BigDecimal prev){HistoryManager.prev = prev;}
    public static BigDecimal getPrev(){return HistoryManager.prev;}
    public static void addHistory(History history) {HistoryManager.history.add(history);}
    public static void printHistory () {
        System.out.println("History:");
        System.out.println("-------------");
        List<History> history = HistoryManager.getHistory();
        int size = history.size();
        for (int i = 0; i < size; i++) {
            System.out.printf("%d. ", i+1);
            history.get(i).printHistory();
        }
        System.out.println("-------------");
    }
    public static List<History> getHistory() {return history;}
    public static void clearHistory() {history.clear();}
}
