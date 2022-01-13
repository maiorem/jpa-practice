package com.maiorem;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.beans.PersistenceDelegate;

public class JpaMain {
    public static void main(String[] args) {
         EntityManagerFactory enf = Persistence.createEntityManagerFactory("hello");
    }
}
