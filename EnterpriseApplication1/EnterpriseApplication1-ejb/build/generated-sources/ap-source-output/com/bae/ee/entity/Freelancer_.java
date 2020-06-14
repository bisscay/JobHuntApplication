package com.bae.ee.entity;

import com.bae.ee.entity.Job;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-14T15:42:07")
@StaticMetamodel(Freelancer.class)
public class Freelancer_ { 

    public static volatile CollectionAttribute<Freelancer, Job> jobCollection;
    public static volatile SingularAttribute<Freelancer, String> freelancername;
    public static volatile SingularAttribute<Freelancer, Integer> freelancerid;
    public static volatile SingularAttribute<Freelancer, String> password;
    public static volatile SingularAttribute<Freelancer, Float> accountbalance;
    public static volatile SingularAttribute<Freelancer, String> skill;
    public static volatile SingularAttribute<Freelancer, String> message;
    public static volatile SingularAttribute<Freelancer, String> username;

}