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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bAe
 */
@Entity
@Table(name = "JOBENTRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobentry.findAll", query = "SELECT j FROM Jobentry j")
    , @NamedQuery(name = "Jobentry.findById", query = "SELECT j FROM Jobentry j WHERE j.id = :id")
    , @NamedQuery(name = "Jobentry.findByJobid", query = "SELECT j FROM Jobentry j WHERE j.jobid = :jobid")
    , @NamedQuery(name = "Jobentry.findByFreelancerid", query = "SELECT j FROM Jobentry j WHERE j.freelancerid = :freelancerid")})
public class Jobentry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "JOBID")
    private Integer jobid;
    @Column(name = "FREELANCERID")
    private Integer freelancerid;

    public Jobentry() {
    }

    public Jobentry(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public Integer getFreelancerid() {
        return freelancerid;
    }

    public void setFreelancerid(Integer freelancerid) {
        this.freelancerid = freelancerid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobentry)) {
            return false;
        }
        Jobentry other = (Jobentry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bae.ee.entity.Jobentry[ id=" + id + " ]";
    }
    
}
