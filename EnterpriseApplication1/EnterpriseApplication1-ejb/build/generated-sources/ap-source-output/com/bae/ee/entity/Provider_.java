package com.bae.ee.entity;

import com.bae.ee.entity.Job;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-14T15:42:07")
@StaticMetamodel(Provider.class)
public class Provider_ { 

    public static volatile CollectionAttribute<Provider, Job> jobCollection;
    public static volatile SingularAttribute<Provider, String> password;
    public static volatile SingularAttribute<Provider, Integer> providerid;
    public static volatile SingularAttribute<Provider, String> username;

}