package school;

import application.DataTool;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ExperienceLevels {

    private Map<String, Integer> levels;

    public ExperienceLevels(Map<String, Integer> levels){
        this.levels = levels;
        saveObject();
    }

    public ExperienceLevels(){
        this.levels = new HashMap<>();
        setLevels();
    }

    public void setLevels(Map<String, Integer> levels){
        this.levels = levels;
        saveObject();
    }

    public void setLevels(){
        SchoolDAO dao = new SchoolDAO();
        this.levels = dao.loadExperienceLevels();
    }

    public void clearLevels(){
        this.levels.clear();
        saveObject();
    }

    public Map<String, Integer> getLevels(){
        return levels;
    }

    public void addLevel(String levelName, Integer levelValue){
        this.levels.put(levelName, levelValue);
        saveObject();
    }

    public int getValue(String levelName){
        return this.levels.get(levelName);
    }

    public boolean containsGivenLevel(String level){
        return this.levels.containsKey(level);
    }

    public void removeLevel(String levelName){
        this.levels.remove (levelName, this.levels.get(levelName));
    }

    public Map<String, Integer> getUpdatedLevels(){
        setLevels();  // update levels
        return levels;
    }

    public String toString(){
        setLevels();
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

    private void saveObject(){
        SchoolDAO dao = new SchoolDAO();
        dao.saveExperienceLevels(this);
    }
}
