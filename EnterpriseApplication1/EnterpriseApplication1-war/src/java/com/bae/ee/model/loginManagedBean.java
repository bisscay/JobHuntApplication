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
import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author bAe
 */

@Named(value = "loginManagedBean")
@SessionScoped
public class loginManagedBean implements Serializable {

    /**
     * Creates a new instance of loginManagedBean
     */
    public loginManagedBean() {
    }
    // EJB's no longer needed
    @EJB
    AdminFacade adminEJB;

    @EJB
    ProviderFacade providerEJB;

    @EJB
    FreelancerFacade freelancerEJB;

    // used to validate password
    private Admin admin;
    private Provider provider;
    private Freelancer freelancer;
    // retrieve input text parameters 
    // & persist through a session
    private String username;
    private String password;
    private String role;
    private String name;
    private String skill;
    private String message;
    // Try and utilize username to generate ID
    private int id;
    

    // No longer utilized
    @ManagedProperty(value = "#{providerManagedBean}")
    private providerManagedBean pMB;

    /**
     * Logout method
     *
     * @return String navigation to login page
     */
//    public String logout() {
//        id = 0;
//        username = "";
//        password = "";
//        role = "";
//        admin = null;
//        provider = null;
//        freelancer = null;
//        return "index";
//    }
    public String logout() {
        // kill session once logged out
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        id = 0;
        username = "";
        password = "";
        role = "";
        admin = null;
        provider = null;
        freelancer = null;
        return "index";
    }

    // Getters & Setters
    public AdminFacade getAdminEJB() {
        return adminEJB;
    }

    public void setAdminEJB(AdminFacade adminEJB) {
        this.adminEJB = adminEJB;
    }

    public ProviderFacade getProviderEJB() {
        return providerEJB;
    }

    public void setProviderEJB(ProviderFacade providerEJB) {
        this.providerEJB = providerEJB;
    }

    public FreelancerFacade getFreelancerEJB() {
        return freelancerEJB;
    }

    public void setFreelancerEJB(FreelancerFacade freelancerEJB) {
        this.freelancerEJB = freelancerEJB;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // will persist the user's ID through login session
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    /**
     * Validate user based on role
     *
     * @return Navigation string based on role and validity
     */
    public String login() {
        // Stay on login page unless user is valid
        String nextPage = "index";
        // role determines next page
        if ("provider".equals(this.role)) {
            // get list of all providers
            List<Provider> pList = providerEJB.findAll();
            // create iterator
            ListIterator lit = pList.listIterator();
            // iterate through each provider in list
            while (lit.hasNext()) {
                // hold current provider in list
                Provider p = (Provider) lit.next();
                // compare provider username to the entered username
                if (username.equalsIgnoreCase(p.getUsername())) {
                    // if true update id in login bean found for provider
                    id = p.getProviderid();
                }
            }
            // Get provider by id
            provider = this.providerEJB.find(id);
            // find hash of password
            
            // verify provider is valid
            // Access Hash method
            Hash hash = new Hash();
            // hash password to compare
            String passwordHash = hash.encryptThisString(password);
            // consider null exceptions
            if (provider == null) {
                System.out.println("Username is null");
            } else if (passwordHash.equals(provider.getPassword())) {
                // key to navigate to next page
                nextPage = "provider";
            } else {
                // navigate to invalid credential page 
                // or stay on login and prompt a dialog stating invalid user credentials
                System.out.println("Invalid User Credentials");
            }
        } else if ("freelancer".equals(this.role)) {
            // get list of all freelancers
            List<Freelancer> fList = freelancerEJB.findAll();
            // create iterator
            ListIterator lit = fList.listIterator();
            // iterate through each freelancer in list
            while (lit.hasNext()) {
                // hold current freelancer in list
                Freelancer f = (Freelancer) lit.next();
                // compare freelancer username to the entered username
                if (username.equalsIgnoreCase(f.getUsername())) {
                    // if true update id in login bean found for freelancer
                    id = f.getFreelancerid();
                }
            }
            // Get freelancer by id
            freelancer = this.freelancerEJB.find(id);
            // verify freelancer is valid
            // Access Hash method
            Hash hash = new Hash();
            // hash password to compare
            String passwordHash = hash.encryptThisString(password);
            // consider null exceptions
            if (freelancer == null) {
                System.out.println("Username is null");
            } else if (passwordHash.equals(freelancer.getPassword())) {
                nextPage = "freelancer";
            } else {
                // navigate to invalid credential page 
                // or stay on login and prompt a dialog stating invalid user credentials
                System.out.println("Invalid User Credentials");
            }
        } else if ("admin".equals(this.role)) {
            // get list of all administrators
            List<Admin> aList = adminEJB.findAll();
            // create iterator
            ListIterator lit = aList.listIterator();
            // iterate through each admin in list
            while (lit.hasNext()) {
                // hold current admin in list
                Admin a = (Admin) lit.next();
                // compare admin username to the entered username
                // consider null exceptions
                if (username == null) {
                    System.out.println("Username is null");
                } else if ((username.equalsIgnoreCase(a.getUsername()))
                        && !(username == null)) {
                    // if true update id in login bean to found for admin
                    id = a.getAdminid();

                } else {
                    System.out.println("User not registered\nTry Signing Up.");
                }
            }
            // Get freelancer by id
            admin = this.adminEJB.find(id);
            // verfiy administrator is valid
            // Access Hash method
            Hash hash = new Hash();
            // hash password to compare
            String passwordHash = hash.encryptThisString(password);
            // consider null exceptions
            if (admin == null) {
                System.out.println("Username is null");
            } else if (passwordHash.equals(admin.getPassword())) {
                nextPage = "admin";
            } else {
                // navigate to invalid credential page 
                // or stay on login and prompt a dialog stating invalid user credentials
                System.out.println("Invalid User Credentials");
            }
        } else {
            // Stay on page
            nextPage = "index";
            System.out.println("User not registered\nTry Signing Up.");
        }
        return nextPage;
    }

  
}
