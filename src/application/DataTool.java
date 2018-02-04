package application;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class DataTool {

    public static Boolean checkIfElementInArray(String[] array, String element) {
        for(String item : array){
            if(item.equals(element)){
                return true;
            }
        }
        return false;
    }

    public static LinkedHashMap<String,Integer> sortMapByValue(Map<String,Integer> map){
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

    }
}
