package history;
import java.util.ArrayList;
public class HistoryManager {
    private static final ArrayList<History> history = new ArrayList<>();
    private static double prev;
    public static void setPrev(double prev){HistoryManager.prev = prev;}
    public static double getPrev(){return HistoryManager.prev;}
    public static void addHistory(History history) {HistoryManager.history.add(history);}
    public static ArrayList<History> getHistory() {return history;}
    public static void clearHistory() {history.clear();}
}
