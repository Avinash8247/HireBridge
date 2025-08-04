package com.microservices.jobms.job;
import com.microservices.jobms.job.dto.jobDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class jobController {
    private JobService jobService;

    public jobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<jobDto>> findAll(){
        return ResponseEntity.ok(jobService.findAll());

    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createjob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("job added seccesfully",HttpStatus.CREATED);
    }

    @GetMapping("jobs/{id}")
    public ResponseEntity<jobDto> getbyid(@PathVariable Long id){
        jobDto jobDto =jobService.findbyid(id);
        if(jobDto !=null){
            return new ResponseEntity<>(jobDto, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("jobs/{id}")
    public ResponseEntity<String> deletebyid(@PathVariable Long id){
       boolean delete= jobService.deletebyid(id);
       if(delete)
          return ResponseEntity.ok("Deleted successfully");
       return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }
    @PutMapping("jobs/{id}")

    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatejob){
        boolean updated=jobService.update(id,updatejob);
        if(updated){
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);

    }

}
