package org.bcci.worldcupt20.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.bcci.worldcupt20.dto.Classroom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffDao {

    @PersistenceContext
    private EntityManager entityManager;
    List<Classroom> staffList;


    public List<Classroom> getAllStaff() {
        if (staffList == null) {
            staffList = entityManager.createQuery("SELECT s FROM Staff s", Classroom.class).getResultList();
        }
        return staffList;
    }

    @Transactional
    public boolean addStaff(Classroom staff) {

        try {
            entityManager.persist(staff);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    @Transactional
    public boolean updateStaff(Classroom staff) {
        try {
            entityManager.merge(staff);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    @Transactional
    public boolean deleteStaff(int staffId) {
        try {
            Classroom staff = entityManager.find(Classroom.class, staffId);
            entityManager.remove(staff);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
