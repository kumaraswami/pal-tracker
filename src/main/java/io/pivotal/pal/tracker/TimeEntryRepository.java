package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeEntryRepository {

    public TimeEntry create(TimeEntry timeEntry) throws Exception;
    public TimeEntry find(Long id) throws Exception;
    public List<TimeEntry> list() throws Exception;
    public TimeEntry update(Long id, TimeEntry timeEntry) throws Exception;
    public void delete(Long id) throws Exception;

}
