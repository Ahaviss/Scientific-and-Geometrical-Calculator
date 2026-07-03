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
    public static List<History> getHistory() {return history;}
    public static void clearHistory() {history.clear();}
}
