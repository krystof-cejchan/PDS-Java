package cz.krystofcejchan.distributed_systems.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DistributedMapReader {
    public static void main(String[] args) {
        // Připojení k existující Hazelcast instanci
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        // Získání distribuované mapy s názvem "sharedMap"
        IMap<String, Integer> map = hazelcastInstance.getMap("sharedMap");
        map.addEntryListener(new DistributedMapListener(), true);

        // Načtení a výpis dat z mapy
        System.out.println(map.entrySet());

        // Výpis velikosti mapy
        System.out.println("Velikost mapy: " + map.size());

        // Udržení aplikace spuštěné
        System.out.println("Aplikace běží ::: exit pro ukončení | libovolný vstup pro vypsání distribuované mapy");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true) {
            try {
                line = in.readLine();
                if (line == null || line.equalsIgnoreCase("exit")) break;
                else System.out.println(map.entrySet());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Uzavření Hazelcast instance
        hazelcastInstance.shutdown();
    }
}
