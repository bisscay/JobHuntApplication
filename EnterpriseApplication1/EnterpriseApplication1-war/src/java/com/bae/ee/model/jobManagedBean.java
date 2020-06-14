/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bae.ee.model;

import com.bae.ee.entity.Job;
import com.bae.ee.jpaejb.JobFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author bAe
 */
@Named(value = "jobManagedBean")
@RequestScoped
public class jobManagedBean {
    //
    private int jobid;
    private int providerid;
    private String title;
    private float token;
    private String keyword;
    private String description;
    
    @EJB
    private JobFacade jobEJB;
    
    private Job job;
    
    /**
     * List all jobs
     * @return list of Job entries
     */
    public List<Job> jobList() {
        return jobEJB.findAll();
    }

    public String getTitle() {
        return title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getToken() {
        return token;
    }

    public void setToken(float token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public int getProviderid() {
        return providerid;
    }

    public void setProviderid(int providerid) {
        this.providerid = providerid;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * Creates a new instance of jobManagedBean
     */
    public jobManagedBean() {
    }
    
}
