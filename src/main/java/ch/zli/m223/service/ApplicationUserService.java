package ch.zli.m223.service;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import ch.zli.m223.model.ApplicationUser;

@ApplicationScoped
public class ApplicationUserService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public ApplicationUser createUser(ApplicationUser member) {
        entityManager.persist(member);
        return member; 
    }

    @Transactional
    public void deleteUser(Long MemberID) {
        ApplicationUser member = entityManager.find(ApplicationUser.class, MemberID);
        if (member != null) {
            entityManager.remove(member);
        }
    }

    @Transactional
    public ApplicationUser updateUser(Long memberID, ApplicationUser member) {
        ApplicationUser existingMember = entityManager.find(ApplicationUser.class, memberID);
        if (existingMember != null) {
            existingMember.setEmail(member.getEmail());
            existingMember.setPassword(member.getPassword());
            existingMember.setFirstName(member.getFirstName());
            entityManager.merge(existingMember);
            return existingMember;
        } else {
            throw new IllegalArgumentException("User not found with ID: " + memberID);
        }
    }

    public List<ApplicationUser> findAll() {
        return entityManager.createQuery("SELECT m FROM ApplicationUser m", ApplicationUser.class).getResultList();
    }

    public Optional<ApplicationUser> findByEmail(String email) {
        return Optional.ofNullable(entityManager
                .createNamedQuery("ApplicationUser.findByEmail", ApplicationUser.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null));
    }
}
