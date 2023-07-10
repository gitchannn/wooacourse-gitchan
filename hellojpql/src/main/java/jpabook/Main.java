package jpabook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        final EntityManager em = emf.createEntityManager();

        final EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("gitchan");
            member.setAge(23);
            em.persist(member);

            Member singleResult = em.createQuery(
                            "select m from Member m where m.username = :username",
                            Member.class
                    ).setParameter("username", "gitchan")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
