import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/*
 * Author: Edwin Torres
 *  email: CoachEd@gmail.com
 *  
 * This example shows different ways to iterate through a HashMap
 */
public class HashmapIterateEx {

    public static void main(String[] args) {

        /** create a HashMap and populate it with some values */
        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("Carmelo","New York Knicks");
        hm.put("Dwight","Houston Rockets");
        hm.put("Kobe","Los Angeles Lakers");
        hm.put("Derrick","Chicago Bulls");
        
        String key, val;
        
        /** for each method */
        for (Map.Entry<String,String> entry : hm.entrySet()) {
            key = entry.getKey();
            val = entry.getValue();
            System.out.println(key + " , " + val);
        }
        System.out.println();
        
        /** iterate over keys */
        Iterator<String> iter1 = hm.keySet().iterator();
        while (iter1.hasNext()) {
            key = iter1.next();
            val = hm.get(key);
            System.out.println(key + " , " + val);
        }        
        System.out.println();        
        
        /** iterate over values */
        Map.Entry<String,String> entry;
        Iterator<Map.Entry<String,String>> iter2 = hm.entrySet().iterator();
        while (iter2.hasNext()) {
            entry = iter2.next();
            key = entry.getKey();
            val = entry.getValue();
            System.out.println(key + " , " + val);
        }        
        System.out.println();           

    }

}
