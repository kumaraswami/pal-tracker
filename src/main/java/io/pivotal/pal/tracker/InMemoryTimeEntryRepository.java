package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long, TimeEntry> db = new HashMap();
    private AtomicLong sequence = new AtomicLong();

    @Override
    public TimeEntry create(TimeEntry timeEntry) throws Exception {

        return addOrUpdate(sequence.incrementAndGet(), timeEntry);

    }

    @Override
    public TimeEntry find(Long id) throws Exception {

        return db.get(id);

    }

    @Override
    public List<TimeEntry> list() throws Exception {

        return new ArrayList<TimeEntry>(db.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) throws Exception {

        if(db.containsKey(id))
            return addOrUpdate(id, timeEntry);

        return null;

    }

    @Override
    public void delete(Long id) throws Exception {
           db.remove(id);
    }

     private TimeEntry addOrUpdate(Long id, TimeEntry timeEntry) {
        TimeEntry createOrUpdate= new TimeEntry(id,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
        db.put(id, createOrUpdate);
        return createOrUpdate;
     }

}
