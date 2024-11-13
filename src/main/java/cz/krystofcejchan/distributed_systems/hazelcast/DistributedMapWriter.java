package cz.krystofcejchan.distributed_systems.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class DistributedMapWriter {
    public static void main(String[] args) {
        Random r = new Random();
        // Vytvoření nebo připojení k Hazelcast instanci s výchozí konfigurací
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        // Získání distribuované mapy s názvem "sharedMap"
        IMap<String, Integer> map = hazelcastInstance.getMap("sharedMap");

        // Vložení dat do mapy
        map.put("Ahoj", r.nextInt());
        map.put("Světe", r.nextInt());

        // Výpis velikosti mapy
        System.out.println("Velikost mapy: " + map.size());

        // Udržení aplikace spuštěné pro demonstraci sdílení dat
        System.out.println("Aplikace běží ::: exit pro ukončení | libovolný vstup bude přidán do mapy");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true) {
            try {
                line = in.readLine();
                if (line == null || line.equalsIgnoreCase("exit")) break;
                map.putIfAbsent(line, r.nextInt());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        hazelcastInstance.shutdown();
    }
}

