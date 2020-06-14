/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bae.ee.model;

import com.bae.ee.entity.Freelancer;
import com.bae.ee.entity.Job;
import com.bae.ee.entity.Jobentry;
import com.bae.ee.entity.Provider;
import com.bae.ee.hash.Hash;
import com.bae.ee.jpaejb.FreelancerFacade;
import com.bae.ee.jpaejb.JobFacade;
import com.bae.ee.jpaejb.JobentryFacade;
import com.bae.ee.jpaejb.ProviderFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

/**
 *
 * @author bAe
 */
@Named(value = "providerManagedBean")
@SessionScoped
public class providerManagedBean implements Serializable {
    // inject the login session parameters for entry maps
    //@ManagedProperty(value = "#{loginManagedBean}")
    //private loginManagedBean loginMBean;
    @Inject
    loginManagedBean lMB;
    /* Not needed 
    // Use a jMB to relay provider entries
    @ManagedProperty(value = "#{jobManagedBean}")
    private jobManagedBean jobManagedBean;
    */
    @Inject
    jobManagedBean jMB;
    
    // access job database services 
    @EJB
    private JobFacade jobEJB;
    // access provider database services 
    @EJB
    private ProviderFacade providerEJB;
    // access jobentry database services
    @EJB
    private JobentryFacade jobentryEJB;
    // access freelancer database services
    @EJB
    private FreelancerFacade freelancerEJB;
    // access job table entity
    //private Job job;
    // access user entries
    private String title="";
    private String description="";
    private String keyword="";
    private Float token=0.0f;
    private String state;
    //
    private String username="";
    private String password="";
    //
    private int ChangeStateJobID;
    private Job ChangeStateJob;
    //
    private String newState;
    /**
     * Creates a new instance of providerManagedBean
     */
    public providerManagedBean() {
    }
    
    /**
     * Add a new job
     * @return String path to return from addJob page to provider page 
     */
    public String addJob() {
        // job model to hold a new job
        Job job = new Job();
        // hold title
        job.setTitle(title);
        // hold description
        job.setDescription(description);
        // hold keyword
        job.setKeyword(keyword);
        // hold job token value
        job.setToken(token);
        // an added job is set to 'open' by default
        job.setState("open");
        // a user's ID will map this job to a provider
        // access user ID from the login session bean
        job.setProviderid(lMB.getProvider()); // many-to-one relationship
        // no freelancer assigned when job is created
        job.setFreelancerid(null);
        // create job
        jobEJB.create(job);
        // return to provider page
        return "provider";
    }
    
    /**
     * Cancel job addition
     * @return navigate to provider page
     */
    public String cancelJobAddition(){
        title="";
        description="";
        keyword="";
        token=0.0f;
        // return to admin page
        return "provider";
    }
    
    /**
     * Remove an open job description
     * @param job to be deleted
     * @return navigation staying on provider page
     */
    public String delete(Job job) { 
        if("open".equals(job.getState())) {
            // access database and remove identified job entry
            jobEJB.remove(job);
        } else{
            // prompt closed/complete jobs cannot be deleted
            System.out.println("Can Only Delete Open Jobs");
            // navigate to unable to delete page (Make a diaglog box that prompts user)
        }
        // avoid page navigation (Return type can be avoided?)
        return null;
    }
    
    /**
     * List all jobs for a particular provider
     * @return list of Job entries
     */
    public List<Job> providerJobList() {
        // hold result list
        List<Job>pJobList = new ArrayList();
        int providerID = lMB.getId();//this.providerEJB.find(lMB.getId());
        // create iterator for list of all provider jobs
        ListIterator lit = jMB.jobList().listIterator();
        // access each job
        while(lit.hasNext()) {
            // hold current object in list
            Job j = (Job)lit.next();
            // validate if provider made job
            if(j.getProviderid().getProviderid() == providerID) {
                // add job to new list
                pJobList.add(j);
            }
        }
        return pJobList;
    }
    
    /**
     * List all providers
     * @return 
     */
    public List<Provider> providerList(){
        return providerEJB.findAll();
    }
    
    
    /**
     * Add provider (Put this in admin bean)
     * @return navigation to admin page
     */
    public String addProvider(){
        Provider p = new Provider();
        p.setUsername(username);
        // Access Hash method
        Hash hash = new Hash();
        // hash password to store
        String passwordHash = hash.encryptThisString(password);
        // store password
        p.setPassword(passwordHash);
        // create provider
        providerEJB.create(p);
        // return to admin page
        cancelProviderAddition();
        return "admin";
    }
    /**
     * Change State I
     * @param job - Job who's state is to be changed
     * @return navigate to change state page
     */
    public String getChangeState(Job job){
        //if("open".equals(state))
        ChangeStateJobID = job.getJobid();
        // hold token for job
        float tokenUpdate = job.getToken();
        // update freelancer balance
        // map jobid to freelancer id
        // access jobentry database list
        List <Jobentry> entryList = jobentryEJB.findAll();
        // create list iterator
        ListIterator lit = entryList.listIterator();
        // create contrainer for entries
        Jobentry entry = new Jobentry();
        // loop through and find freelancer with jobID
        while(lit.hasNext()) {
            // hold entry
            entry = (Jobentry)lit.next();
            // find freelancer with job
            if(entry.getJobid() == job.getJobid()) {
                // get corresponding freelancer ID
                int freelancerID = entry.getFreelancerid();
                // access freelancer database services and find freelancer obj
                Freelancer freelancer = freelancerEJB.find(freelancerID);
                // add token to freelancer balance
                tokenUpdate += freelancer.getAccountbalance();
                // update balance of freelancer to new addition
                freelancer.setAccountbalance(tokenUpdate);
                // access update service
                freelancerEJB.edit(freelancer);
            }
        }
            //ChangeStateJob.s;//job.setState(state);
        return "changeState";
    }
    /**
     * Change State II
     * @return navigate back to change state page
     */
    public String changeState(){
        // find job with id
        Job job = jobEJB.find(ChangeStateJobID);
        // change state
        job.setState(newState);
        // update table
        jobEJB.edit(job);
        // update freelancer balance
        // map jobid to freelancer id
        
        // consider using a list to pick from
        //if("open".equals(state))
            //ChangeStateJob.s;//job.setState(state);
        return "provider";
    }
    /**
     * Cancel provider addition
     * @return navigate to stay on page
     */
    public String cancelProviderAddition(){
        username="";
        password="";
        // return to admin page
        return "admin";
    }
    
    
    // Getters & Setters
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Float getToken() {
        return token;
    }

    public void setToken(Float token) {
        this.token = token;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public int getChangeStateJobID() {
        return ChangeStateJobID;
    }

    public void setChangeStateJobID(int ChangeStateJobID) {
        this.ChangeStateJobID = ChangeStateJobID;
    }

    public String getNewState() {
        return newState;
    }

    public void setNewState(String newState) {
        this.newState = newState;
    }
    
}
