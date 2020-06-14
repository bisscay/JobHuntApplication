/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bae.ee.jpaejb;

import com.bae.ee.entity.Jobentry;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bAe
 */
@Stateless
public class JobentryFacade extends AbstractFacade<Jobentry> {

    @PersistenceContext(unitName = "EnterpriseApplication1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobentryFacade() {
        super(Jobentry.class);
    }
    
}
