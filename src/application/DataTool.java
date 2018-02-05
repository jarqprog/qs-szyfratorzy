package application;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Map.Entry;
import java.util.Objects;

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

    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        for (Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getMultipliedString(String string, int multiplier){
        return new String(new char[multiplier]).replace("\0", string);
    }
}
