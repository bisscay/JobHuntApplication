/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bae.ee.model;

import com.bae.ee.entity.Freelancer;
import com.bae.ee.entity.Job;
import com.bae.ee.entity.Jobentry;
import com.bae.ee.jpaejb.FreelancerFacade;
import com.bae.ee.jpaejb.JobFacade;
import com.bae.ee.jpaejb.JobentryFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.FileHandler;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;


///added for logging
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 *
 * @author bAe
 */
@Named(value = "jobentryManagedBean")
@SessionScoped
public class jobentryManagedBean implements Serializable{
    // hold the id of a job in view
    // updated each time view job is clicked
    
    //Added for Logging
    Logger logger = Logger.getLogger(jobentryManagedBean.class.getName());
    
    FileHandler fh;
    
    int viewJobID;
    
    // access jobentry database services 
    @EJB
    private JobentryFacade jobentryEJB;
    
    // access freelancer database services
    @EJB
    private FreelancerFacade freelancerEJB;
    
    // access job database
    @EJB
    private JobFacade jobEJB;
    
    // acceess login session id of freelancer
    @Inject
    loginManagedBean lMB;
    
    // hold list of entry ID's for a job
    List<Integer> entryIDList = new ArrayList();
    // hold list of entries for a job
    List<Freelancer> entryList = new ArrayList();

    /**
     * Creates a new instance of jobentryManagedBean
     */
    public jobentryManagedBean() {
    }
    
    /**
     * Add a new request for a job
     * @param job - job request to partake by freelancer
     * @return String path to accept job parameters 
     */
    public String addJobentry(Job job) {
        Jobentry jobentry = new Jobentry();
        // get ID of requested job from selected row in display table
        jobentry.setJobid(job.getJobid());
        // get ID of freelancer from current login session
        jobentry.setFreelancerid(lMB.getId());
        // create job entry
        jobentryEJB.create(jobentry);
        // log entries
        try{
            fh = new FileHandler("/Users/prof.bissallahekele/Desktop/log",true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("Job request placed");
        }catch(SecurityException sc){
            sc.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
        // return to freelancer page
        return "freelancer";
    }
    
    /**
     * Revoke a request - remove entry from job-entry table
     * @param job - job to be removed
     * @return navigation to stay on page
     */
    public String removeJobentry(Job job) {
        int id = job.getJobid();
        // search for jobentry using job id
        // Variable to hold jobentry
        Jobentry jobentry = new Jobentry();
        // access jobentry database
        List<Jobentry>jobList = jobentryEJB.findAll();
        // create list iterator 
        ListIterator lit = jobList.listIterator();
        // hold a jobentry instance
        Jobentry je = new Jobentry();
        // look at each jobentry
        while(lit.hasNext()){
            // place current jobentry in container
            je = (Jobentry)lit.next();
            // begin search for entry
            if(je.getJobid()==id){
                // job entry found
               jobentry = je; 
            }
        }
        
        // if job is open remove job-entry
        if("open".equals(job.getState())) {
            jobentryEJB.remove(jobentry);
        } else{
            // prompt closed/complete jobs cannot be deleted
            System.out.println("Can Only Delete Open Jobs");
        }
        // avoid page navigation (Return type can be avoided?)
        return null;
    }
    
    // Freelancer list by id
    // get all freelancers with id equal to entry list values
    //public List<Freelancer> freelancerList(List<Integer> newList){
        
    //}
    
    // Break method up - too long
    public String viewEntry(Job job){
        // get job id
        int jobID = job.getJobid();
        // access list of jobs in job-entry table
        List<Jobentry>jobList = jobentryEJB.findAll();
        // create list iterator
        ListIterator lit = jobList.listIterator();
        // hold a jobentry instance
        Jobentry je = new Jobentry();
        // find rows with job id
        while(lit.hasNext()) {
            // map entry to instance container
            je = (Jobentry)lit.next();
            // add all freelancer id with job id to list
            if(je.getJobid()==jobID) {
                entryIDList.add(je.getFreelancerid());
            }
        }
        // access list of users in freelancer table
        List<Freelancer>freelancerList = freelancerEJB.findAll();
        // create another list iterator (potential redunancy)
        ListIterator lit2 = freelancerList.listIterator();
        // hold a freelancer instance
        Freelancer f = new Freelancer();
        // look at each freelancer
        while(lit2.hasNext()){
            // place current freelancer in container
            f = (Freelancer)lit2.next();
            //
            for(int entryID : entryIDList){
                // get all freelancers with id equal to entry list values
                if(f.getFreelancerid() == entryID){
                    // add freelancer to list
                    entryList.add(f);
                }
            }
        }
        // hold jobID that view-entry calls (update each time view-entry is clicked)
        viewJobID = job.getJobid();
        return null;//"jobrequest";
    }
    
    /**
     * Change state of job to closed & assign freelancer ID to job detail once a freelancer is accepted
     * @param freelancer
     * @return null but changes state
     */
    public String acceptEntry(Freelancer freelancer){
        // access id of freelancer
        //int freelancerID = freelancer.getFreelancerid();
        // access job database
        // search for job in job table using job id
        Job jobAccepted = jobEJB.find(viewJobID);
        // update freelance ID
        jobAccepted.setFreelancerid(freelancer);
        //jobEJB.edit(jobAccepted);
        // update job state
        jobAccepted.setState("closed");
        // update entry
        jobEJB.edit(jobAccepted); //-------------------------------------------- WoW!! is it this easy?!
        
        
        try{
            fh = new FileHandler("/Users/prof.bissallahekele/Desktop/log",true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("Freelancer accepted");
        }catch(SecurityException sc){
            sc.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
        // map id to jobentry table and get related job
        
        // update state of job
        // update freelancer ID of job
        return null; 
    }

    public List<Freelancer> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Freelancer> entryList) {
        this.entryList = entryList;
    }
    
}
