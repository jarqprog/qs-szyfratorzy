package Model;

import application.DataTool;
import school.SchoolDAO;
import dao.ExperienceLevelsDAO;

import java.util.Map;
import java.util.Map.Entry;

public class ExperienceLevels {

    private Map<String, Integer> levels;

    public ExperienceLevels() {
        this.levels = getUpdatedLevels();
        setLevels();
    }

    public void setLevels(Map<String, Integer> levels){
        this.levels = levels;
        saveObject();
    }

    public void setLevels(){
        ExperienceLevelsDAO dao = new ExperienceLevelsDAO();
        this.levels = dao.load();
    }

    public void clearLevels(){
        this.levels.clear();
        saveObject();
    }

    public Map<String, Integer> getLevels(){
        return levels;
    }

    public Map<String, Integer> getUpdatedLevels(){
        setLevels();  // update levels
        if(! levels.containsValue(0)){
            levels.put("basic", 0);  // exp levels always should have level with value 0
            saveObject();
        }
        return levels;
    }

    public void addLevel(String levelName, Integer levelValue) {
        if(! levels.containsValue(levelValue)) {
            this.levels.put(levelName, levelValue);
            saveObject();
        }
    }

    public int getValue(String levelName){
        return this.levels.get(levelName);
    }

    public boolean containsGivenLevel(String level){
        return levels.containsKey(level);
    }

    public boolean containsGivenValue(Integer value){
        return levels.containsValue(value);
    }

    public void removeLevel(String levelName){
        this.levels.remove (levelName, this.levels.get(levelName));
        saveObject();
    }

    public String toString(){
        setLevels();
        Map<String,Integer> sorted = DataTool.sortMapByValue(levels); // sort map - result LinkedHashMap

        StringBuilder sb = new StringBuilder();
        sb.append("\n  Experience levels:\n\n\tlevel: required experience\n");
        int lineMultiplier = 45;
        String sign = "-";
        String line = DataTool.getMultipliedString(sign, lineMultiplier) + "\n";
        sb.append(line);
        for (Entry<String, Integer> entry : sorted.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            sb.append(String.format("\t- %s: %s\n", key, value));
        }
        return sb.toString();
    }

    private void saveObject(){
        if(! levels.containsValue(0)){
            levels.put("basic", 0);  // exp levels always should have level with value 0
        }
        ExperienceLevelsDAO dao = new ExperienceLevelsDAO();
        dao.save(this);
    }
}
