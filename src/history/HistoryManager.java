package history;
import java.util.ArrayList;
public class HistoryManager {
    private static final ArrayList<History> history = new ArrayList<>();
    public static double prev;
    public static void addHistory(History history) {HistoryManager.history.add(history);}
    public static ArrayList<History> getHistory() {return history;}
    public static void clearHistory() {history.clear();}
}
