/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bae.ee.model;

import com.bae.ee.entity.Freelancer;
import com.bae.ee.entity.Job;
import com.bae.ee.jpaejb.FreelancerFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author bAe
 */
@Named(value = "freelancerManagedBean")
@RequestScoped
public class freelancerManagedBean {
    //
    Freelancer freelancer;
    
    // access login session
    @Inject  
    loginManagedBean lMB;
    
    // access job managed bean
    @Inject
    jobManagedBean jMB;
    
    private String name;
    private Float currentBalance;
    private String username;
    private String password;
    private String keyword;
    private int uniqueJobID;
    private List<Job>keyList = new ArrayList();
    private Job uniqueJob;
    
    // Access freelancer database services
    @EJB
    private FreelancerFacade freelancerEJB;
    //Utilize job database 

    /**
     * Creates a new instance of freelancerManagedBean
     */
    public freelancerManagedBean() {
    }
    
    /**
     * List all freelancers
     * @return 
     */
    public List<Freelancer> freelancerList(){
        return freelancerEJB.findAll();
    }
    
    /**
     * Get profile
     * @return navigation back to freelancer page
     */
    public String getProfile(){
        // access id of login profile
        // find freelancer from database
        // create variable to acess display values
        Freelancer currentProfile = freelancerEJB.find(lMB.getId());
        // access display requirements
        name = currentProfile.getFreelancername();
        currentBalance = currentProfile.getAccountbalance();
        return "freelancer";
    }
    
    /**
     * Navigate to profile update page
     */
    public String editProfile(){
        // navigate to login page 
        return "updateFreelancer";
    }
    
    /**
     * Update profile one save
     * @return navigation back to freelancer page
     */
    public String save(){
        // access id of login profile
        // find freelancer from database
        // create variable to hold form entries
        Freelancer editProfile = freelancerEJB.find(lMB.getId());
        // accept form entries
        editProfile.setFreelancername(lMB.getName());
        editProfile.setSkill(lMB.getSkill());
        editProfile.setMessage(lMB.getMessage());
        // update entries
        freelancerEJB.edit(editProfile);
        return "freelancer";
    }
    
    /**
     * Available job list
     * @return list of jobs
     */
    public List<Job> availableJob() {
        // Variable to hold sorted list
        List<Job> availableJobs = new ArrayList();
        // get all jobs
        List<Job> jobs = jMB.jobList();
        // create list iterator
        ListIterator lit = jobs.listIterator();
        // variable to hold each job
        Job j = new Job();
        // search for jobs with open values in status cloumn
        while(lit.hasNext()) {
            // get current object
            j = (Job)lit.next();
            // check condition
            if("open".equals(j.getState())){
                // add value to list
                availableJobs.add(j);
            }
        }
        return availableJobs;
    }
    
    /**
     * Search by keyword
     * @return list of jobs with matching keyword
     */
    public List<Job> keywordSearch() {
        // get all jobs
        List<Job> jobs = jMB.jobList();
        // create list iterator
        ListIterator lit = jobs.listIterator();
        // variable to hold each job
        Job j = new Job();
        // search for jobs with specified key & open state
        while(lit.hasNext()) {
            // get current object
            j = (Job)lit.next();
            // check condition
            if(keyword.equals(j.getKeyword()) && "open".equals(j.getState())){
                // update keyList
                keyList.add(j);
            }
        }
        return null; // try navigating to current page (freelancer)
    }
    
    /**
     * Search by unique ID
     * @return 
     */
    public Job uniqueIDSearch() {
        // get all jobs
        List<Job> jobs = jMB.jobList();
        // create list iterator
        ListIterator lit = jobs.listIterator();
        //
        Job j = new Job();
        // search for jobs with desired id value
        while(lit.hasNext()) {
            // get current object
            j = (Job)lit.next();
            // check condition keyword.equals(j.getKeyword())
            //if("open".equals(j.getState())){
            if(uniqueJobID == j.getJobid()){
                // hold found job
                uniqueJob = j;
            }
        }
        return null;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Job> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<Job> keyList) {
        this.keyList = keyList;
    }

    public int getUniqueJobID() {
        return uniqueJobID;
    }

    public void setUniqueJobID(int uniqueJobID) {
        this.uniqueJobID = uniqueJobID;
    }

    public Job getUniqueJob() {
        return uniqueJob;
    }

    public void setUniqueJob(Job uniqueJob) {
        this.uniqueJob = uniqueJob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Float currentBalance) {
        this.currentBalance = currentBalance;
    }
    
}
