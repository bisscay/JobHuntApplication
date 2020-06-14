/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bae.ee.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bAe
 */
@Entity
@Table(name = "FREELANCER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Freelancer.findAll", query = "SELECT f FROM Freelancer f")
    , @NamedQuery(name = "Freelancer.findByFreelancerid", query = "SELECT f FROM Freelancer f WHERE f.freelancerid = :freelancerid")
    , @NamedQuery(name = "Freelancer.findByUsername", query = "SELECT f FROM Freelancer f WHERE f.username = :username")
    , @NamedQuery(name = "Freelancer.findByPassword", query = "SELECT f FROM Freelancer f WHERE f.password = :password")
    , @NamedQuery(name = "Freelancer.findByFreelancername", query = "SELECT f FROM Freelancer f WHERE f.freelancername = :freelancername")
    , @NamedQuery(name = "Freelancer.findBySkill", query = "SELECT f FROM Freelancer f WHERE f.skill = :skill")
    , @NamedQuery(name = "Freelancer.findByMessage", query = "SELECT f FROM Freelancer f WHERE f.message = :message")
    , @NamedQuery(name = "Freelancer.findByAccountbalance", query = "SELECT f FROM Freelancer f WHERE f.accountbalance = :accountbalance")})
public class Freelancer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FREELANCERID")
    private Integer freelancerid;
    @Size(max = 255)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 255)
    @Column(name = "FREELANCERNAME")
    private String freelancername;
    @Size(max = 255)
    @Column(name = "SKILL")
    private String skill;
    @Size(max = 255)
    @Column(name = "MESSAGE")
    private String message;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ACCOUNTBALANCE")
    private Float accountbalance;
    @OneToMany(mappedBy = "freelancerid")
    private Collection<Job> jobCollection;

    public Freelancer() {
    }
    /**
     * Generic Cstr - Used to test entries into table (can be removed later)
     * @param freelancerid
     * @param username
     * @param password
     * @param freelancername
     * @param skill
     * @param message
     * @param accountbalance 
     */
    public Freelancer(Integer freelancerid, String username, String password, String freelancername, String skill, String message, Float accountbalance) {
        this.freelancerid = freelancerid;
        this.username = username;
        this.password = password;
        this.freelancername = freelancername;
        this.skill = skill;
        this.message = message;
        this.accountbalance = accountbalance;
    }
    
    

    public Freelancer(Integer freelancerid) {
        this.freelancerid = freelancerid;
    }

    public Integer getFreelancerid() {
        return freelancerid;
    }

    public void setFreelancerid(Integer freelancerid) {
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

    public Float getAccountbalance() {
        return accountbalance;
    }

    public void setAccountbalance(Float accountbalance) {
        this.accountbalance = accountbalance;
    }

    @XmlTransient
    public Collection<Job> getJobCollection() {
        return jobCollection;
    }

    public void setJobCollection(Collection<Job> jobCollection) {
        this.jobCollection = jobCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (freelancerid != null ? freelancerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Freelancer)) {
            return false;
        }
        Freelancer other = (Freelancer) object;
        if ((this.freelancerid == null && other.freelancerid != null) || (this.freelancerid != null && !this.freelancerid.equals(other.freelancerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bae.ee.entity.Freelancer[ freelancerid=" + freelancerid + " ]";
    }
    
}
