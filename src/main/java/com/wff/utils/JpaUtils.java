package com.wff.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
    private static EntityManagerFactory factory;
    static{
        factory= Persistence.createEntityManagerFactory("myjpa");
    }
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
