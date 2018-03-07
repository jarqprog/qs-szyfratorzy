package model;

import tools.DataTool;

import java.util.Map;
import java.util.Map.Entry;

public class ExperienceLevels extends PassiveModel {

    private Map<String, Integer> levels;

    ExperienceLevels() {
        this.levels = getUpdatedLevels();
        setLevels();
    }

    public void setLevels(Map<String, Integer> levels){
        this.levels = levels;
        saveModel();
    }

    public void setLevels(){
        this.levels = ModelDaoFactory.getByType(ExperienceLevelsDAO.class).load();
    }

    public void clearLevels(){
        this.levels.clear();
        saveModel();
    }

    public Map<String, Integer> getLevels(){
        return levels;
    }

    public Map<String, Integer> getUpdatedLevels(){
        setLevels();  // update levels
        if(! levels.containsValue(0)){
            levels.put("basic", 0);  // exp levels always should have level with value 0
            saveModel();
        }
        return levels;
    }

    public void addLevel(String levelName, Integer levelValue) {
        if(! levels.containsValue(levelValue)) {
            this.levels.put(levelName, levelValue);
            saveModel();
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
        saveModel();
    }

    public String toString(){
        setLevels();
        Map<String,Integer> sorted = DataTool.sortMapByValue(levels); // sort map - result LinkedHashMap

        StringBuilder sb = new StringBuilder();
        sb.append("\n\t\tExperience levels:\n\n\t\tlevel: required experience\n\t\t");
        int lineMultiplier = 45;
        String sign = "-";
        String line = DataTool.getMultipliedString(sign, lineMultiplier) + "\n";
        sb.append(line);
        for (Entry<String, Integer> entry : sorted.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            sb.append(String.format("\t\t\t- %s: %s\n", key, value));
        }
        return sb.toString();
    }

    @Override
    public void saveModel(){
        if(! levels.containsValue(0)){
            levels.put("basic", 0);  // exp levels always should have level with value 0
        }
        super.saveModel();
    }
}
