package com.click.firstjob.Job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
//specialise version of controller annotation, controller+Responsebody ,Restful representation
// dispatcher servlet is heart of springboot
// by default error page
// ResponseEntity which can manage the http response status(wraps the http response)
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getById(@PathVariable("id") Long id) {
        Job job = jobService.getById(id);
        if (job != null) return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        boolean deleted = jobService.deleteById(id);
    if(deleted)
        return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable("id") Long id,@RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id,updatedJob);
        if(updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

        /* GET /Jobs : Get all jobs
     GET /jobs/{id} : Get a specific job by ID
     POST /jobs : Create a new job (reque body should contain the job details)
     DELETE /jobs/{id} : Delete a specific job by ID
     PUT /jobs/{id} : Update a specific job by ID ( request body should contain the update job body)

     Example API URLS:
     GET {base_url}/jobs     // // localhost:9090/jobs
     GET {base-url}/jobs/1   // localhost:9090/jobs/1
    */
}
