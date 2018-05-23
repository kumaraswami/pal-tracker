package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    //TimeEntryRepository timeEntryRepository;


    /*public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }*/

    JdbcTimeEntryRepository jdbcTimeEntryRepository;

    public TimeEntryController(JdbcTimeEntryRepository jdbcTimeEntryRepository) {
        this.jdbcTimeEntryRepository = jdbcTimeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) throws Exception{

        return new ResponseEntity<>(jdbcTimeEntryRepository.create(timeEntry), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) throws Exception{

        TimeEntry timeEntry = jdbcTimeEntryRepository.find(id);
        if (timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() throws Exception{

        return new ResponseEntity<>(jdbcTimeEntryRepository.list(), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) throws Exception {


        TimeEntry updatedTimeEntry = jdbcTimeEntryRepository.update(id, timeEntry);
        if (updatedTimeEntry != null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) throws Exception {


        jdbcTimeEntryRepository.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
