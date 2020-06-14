/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bae.ee.model;

import com.bae.ee.entity.Admin;
import com.bae.ee.entity.Freelancer;
import com.bae.ee.entity.Provider;
import com.bae.ee.hash.Hash;
import com.bae.ee.jpaejb.AdminFacade;
import com.bae.ee.jpaejb.FreelancerFacade;
import com.bae.ee.jpaejb.ProviderFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author bAe
 */
@Named(value = "adminManagedBean")
@RequestScoped
public class adminManagedBean {
    private int freelancerid;
    private String username;
    private String password;
    private String freelancername;
    private String skill;
    private String message;
    private float accountbalance;
    
    // access admin database services 
    @EJB
    private AdminFacade adminEJB;
    // access freelancer database services 
    @EJB
    private FreelancerFacade freelancerEJB;
    // access provider database services 
    @EJB
    private ProviderFacade providerEJB;
    

    /**
     * Creates a new instance of adminManagedBean
     */
    public adminManagedBean() {
    }
    /**
     * Add freelancer
     * @return navigate to stay on page
     */
    public String addFreelancer(){
        Freelancer f = new Freelancer();
        f.setUsername(username);
        // Access Hash method
        Hash hash = new Hash();
        // hash password to store
        String passwordHash = hash.encryptThisString(password);
        // store password
        f.setPassword(passwordHash);
        f.setFreelancername(freelancername);
        f.setSkill(skill);
        f.setMessage(message);
        // new freelancer starts with zero balance
        f.setAccountbalance(0.0f);
        // create freelancer
        freelancerEJB.create(f);
        // return to admin page
        //return "admin";
        return "/admin.xhtml?faces-redirect=true";
    }
    
    /**
     * Cancel freelancer addition
     * @return navigate to stay on page
     */
    public String cancelFreelancerAddition(){
        username="";
        password="";
        freelancername="";
        skill="";
        message="";
        // return to admin page
        return "admin";
    }
    
    /**
     * Delete freelancer
     * @param freelancer
     * @return navigation to stay on page
     * consider overloading delete method
     */
    public String deleteFreelancer(Freelancer freelancer){
        freelancerEJB.remove(freelancer);
        return null;
    }
    
    /**
     * Delete provider
     * @param provider
     * @return navigation to stay on admin page
     */
    public String deleteProvider(Provider provider){
        providerEJB.remove(provider);
        return null;
    }

    // Getters & Setters
    public int getFreelancerid() {
        return freelancerid;
    }

    public void setFreelancerid(int freelancerid) {
        this.freelancerid = freelancerid;
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

    public String getFreelancername() {
        return freelancername;
    }

    public void setFreelancername(String freelancername) {
        this.freelancername = freelancername;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getAccountbalance() {
        return accountbalance;
    }

    public void setAccountbalance(float accountbalance) {
        this.accountbalance = accountbalance;
    }
    
}
