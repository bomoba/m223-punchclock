package ch.zli.m223.service;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Member;
import ch.zli.m223.model.Question;
import ch.zli.m223.model.Role;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Singleton
@Startup
public class TestDataService {

    @Inject
    private EntityManager entityManager;

    @PostConstruct
    @Transactional
    public Role createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        entityManager.persist(role);
        return role;
    }

    public void init() {
        Role memberRole = createRole("Member");
        Role adminRole = createRole("Admin");

        Member admin = createMember("Guilherme", "Lopes", "gl@myspace.ch", "admin123", adminRole);
        Member member = createMember("Larissa", "Gushue", "l.gushue@gmail.com", "password123", memberRole);

        Booking booking = createBooking(LocalDateTime.now(), "Pending", member);

        Question question = createQuestion("Wie kann ich eine Buchung stornieren?", member);
    }

    private Member createMember(String firstName, String lastName, String email, String password, Role role) {
        Member member = new Member();
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setEmail(email);
        member.setPassword(password);
        member.setRole(role);
        entityManager.persist(member);
        return member;
    }

    private Booking createBooking(LocalDateTime date, String status, Member member) {
        Booking booking = new Booking();
        booking.setDate(date);
        booking.setStatus(status);
        booking.setMember(member);
        entityManager.persist(booking);
        return booking;
    }

    private Question createQuestion(String questionText, Member member) {
        Question question = new Question();
        question.setQuestion(questionText);
        question.setMember(member);
        entityManager.persist(question);
        return question;
    }
}
