package org.example.migration;

import jakarta.persistence.EntityManager;
import org.example.utils.JPAUtil;

public class Migrate {
    public static void main(String[] args){
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
