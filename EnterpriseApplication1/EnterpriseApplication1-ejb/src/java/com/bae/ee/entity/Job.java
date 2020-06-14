/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bae.ee.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bAe
 */
@Entity
@Table(name = "JOB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
    , @NamedQuery(name = "Job.findByJobid", query = "SELECT j FROM Job j WHERE j.jobid = :jobid")
    , @NamedQuery(name = "Job.findByTitle", query = "SELECT j FROM Job j WHERE j.title = :title")
    , @NamedQuery(name = "Job.findByKeyword", query = "SELECT j FROM Job j WHERE j.keyword = :keyword")
    , @NamedQuery(name = "Job.findByDescription", query = "SELECT j FROM Job j WHERE j.description = :description")
    , @NamedQuery(name = "Job.findByToken", query = "SELECT j FROM Job j WHERE j.token = :token")
    , @NamedQuery(name = "Job.findByState", query = "SELECT j FROM Job j WHERE j.state = :state")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "JOBID")
    private Integer jobid;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 255)
    @Column(name = "KEYWORD")
    private String keyword;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOKEN")
    private Float token;
    @Size(max = 255)
    @Column(name = "STATE")
    private String state;
    @JoinColumn(name = "FREELANCERID", referencedColumnName = "FREELANCERID")
    @ManyToOne
    private Freelancer freelancerid;
    @JoinColumn(name = "PROVIDERID", referencedColumnName = "PROVIDERID")
    @ManyToOne
    private Provider providerid;

    public Job() {
    }

    public Job(Integer jobid, String title, String keyword, String description, Float token, String state, Freelancer freelancerid, Provider providerid) {
        this.jobid = jobid;
        this.title = title;
        this.keyword = keyword;
        this.description = description;
        this.token = token;
        this.state = state;
        this.freelancerid = freelancerid;
        this.providerid = providerid;
    }

    public Job(Integer jobid) {
        this.jobid = jobid;
    }

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Freelancer getFreelancerid() {
        return freelancerid;
    }

    public void setFreelancerid(Freelancer freelancerid) {
        this.freelancerid = freelancerid;
    }

    public Provider getProviderid() {
        return providerid;
    }

    public void setProviderid(Provider providerid) {
        this.providerid = providerid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobid != null ? jobid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.jobid == null && other.jobid != null) || (this.jobid != null && !this.jobid.equals(other.jobid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bae.ee.entity.Job[ jobid=" + jobid + " ]";
    }
    
}
