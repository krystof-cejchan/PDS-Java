package cz.krystofcejchan.distributed_systems.hazelcast;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;

public class DistributedMapListener implements EntryAddedListener<String, Integer>, EntryUpdatedListener<String, Integer> {
    @Override
    public void entryAdded(EntryEvent<String, Integer> event) {
        System.out.println("Přidáno: klíč=" + event.getKey() + ", hodnota=" + event.getValue());
    }

    @Override
    public void entryUpdated(EntryEvent<String, Integer> event) {
        System.out.println("Aktualizováno: klíč=" + event.getKey() + ", hodnota=" + event.getValue());
    }
}


