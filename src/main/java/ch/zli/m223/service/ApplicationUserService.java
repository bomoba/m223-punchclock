package ch.zli.m223.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import ch.zli.m223.model.ApplicationUser;
import io.smallrye.jwt.algorithm.SignatureAlgorithm;

@ApplicationScoped
public class ApplicationUserService {

    private static final long EXPIRATION_TIME = 864000000; // 10 Tage in Millisekunden
    private static final String SECRET = "MeinSuperSuperJWTGeheimnis"; 

    @Inject
    EntityManager entityManager;

    @Transactional
    public ApplicationUser createUser(ApplicationUser member) {
        entityManager.persist(member);
        return member;
    }

    @Transactional
    public void deleteUser(Long memberId) {
        ApplicationUser user = entityManager.find(ApplicationUser.class, memberId);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Transactional
    public ApplicationUser updateUser(Long memberId, ApplicationUser user) {
        ApplicationUser existingUser = entityManager.find(ApplicationUser.class, memberId);
        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword()); // Passwort sollte gehasht sein
            existingUser.setFirstName(user.getFirstName());
            entityManager.merge(existingUser);
            return existingUser;
        } else {
            throw new IllegalArgumentException("Something is wrong: " + memberId);
        }
    }

    public List<ApplicationUser> findAll() {
        return entityManager.createQuery("SELECT m FROM ApplicationUser m", ApplicationUser.class).getResultList();
    }

    public Optional<ApplicationUser> findEmail(String email) {
        return Optional.ofNullable(entityManager
                .createNamedQuery("ApplicationUser.findEmail", ApplicationUser.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null));
    }

    @Transactional
    public String authenticate(String email, String password) {
        ApplicationUser user = findEmail(email)
                .filter(u -> u.getPassword().equals(password)) // Das Passwort sollte gehasht und geprüft werden
                .orElse(null);

        if (user != null) {
            return Jwts.builder()
                    .setSubject(email)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
        } else {
            throw new SecurityException("Invalid credentials");
        }
    }
}
