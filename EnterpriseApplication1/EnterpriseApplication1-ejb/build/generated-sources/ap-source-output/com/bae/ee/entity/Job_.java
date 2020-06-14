package com.bae.ee.entity;

import com.bae.ee.entity.Freelancer;
import com.bae.ee.entity.Provider;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-14T15:42:07")
@StaticMetamodel(Job.class)
public class Job_ { 

    public static volatile SingularAttribute<Job, Integer> jobid;
    public static volatile SingularAttribute<Job, Freelancer> freelancerid;
    public static volatile SingularAttribute<Job, Provider> providerid;
    public static volatile SingularAttribute<Job, String> description;
    public static volatile SingularAttribute<Job, String> state;
    public static volatile SingularAttribute<Job, String> title;
    public static volatile SingularAttribute<Job, String> keyword;
    public static volatile SingularAttribute<Job, Float> token;

}