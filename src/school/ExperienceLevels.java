package school;

import application.DataTool;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ExperienceLevels {

    private Map<String, Integer> levels;

    public ExperienceLevels(Map<String, Integer> levels){
        this.levels = levels;
    }

    public ExperienceLevels(){
        this.levels = new HashMap<>();
        setLevels();
    }

    public void setLevels(Map<String, Integer> levels){
        this.levels = levels;
    }

    public void setLevels(){
        SchoolDAO dao = new SchoolDAO();
        this.levels = dao.loadExperienceLevels();
    }

    public Map<String, Integer> getLevels(){
        setLevels();  // update levels
        return levels;
    }

    public String toString(){
        Map<String,Integer> sorted = DataTool.sortMapByValue(levels); // sort map - result LinkedHashMap

        StringBuilder sb = new StringBuilder();
        sb.append("\n  Experience levels:\n\n\tlevel: required experience\n");

        int lineMultiplier = 30;
        String line = "\t" + new String(new char[lineMultiplier]).replace("\0", "-") + "\n";
        sb.append(line);
        for (Entry<String, Integer> entry : sorted.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            sb.append(String.format("\t- %s: %s\n", key, value));
        }
        return sb.toString();
    }


//    public boolean modifyLevel(String level, int value){
//        if(levels.containsKey(level)) {
//            levels.put(level, value);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean addLevel(String level, int value){
//        if(! levels.containsKey(level)) {
//            levels.put(level, value);
//            return true;
//        }
//        return false;
//    }
//
//    public int removeLevel(String levelName){
//        return levels.remove(levelName);
//    }
//
//    public int size(){
//        return levels.size();
//    }
//
//    public int getValue(String level){
//        return levels.get(level);
//    }
}
