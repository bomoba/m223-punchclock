package ch.zli.m223.service;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.B_Details;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BookingService {

    @Inject
    private EntityManager entityManager;

    @Transactional
    public Booking createBooking(Booking booking) {
        entityManager.persist(booking);
        return booking;
    }

    @Transactional
    public Booking updateBooking(Long bookingId, Booking booking) {
        Booking existingBooking = entityManager.find(Booking.class, bookingId);
        if (existingBooking != null) {
            existingBooking.setDate(booking.getDate());
            existingBooking.setStatus(booking.getStatus());
            existingBooking.setMember(booking.getMember());
            existingBooking.setDetails(booking.getDetails());
            entityManager.merge(existingBooking);
        } else {
            throw new IllegalStateException("Booking not found with ID: " + bookingId);
        }
        return existingBooking;
    }

    @Transactional
    public void deleteBooking(Long bookingId) {
        Booking booking = entityManager.find(Booking.class, bookingId);
        if (booking != null) {
            entityManager.remove(booking);
        } else {
            throw new IllegalStateException("Booking not found with ID: " + bookingId);
        }
    }

    public List<Booking> getAllBookings() {
        return entityManager.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
    }

    public Booking getBookingById(Long bookingId) {
        Booking booking = entityManager.find(Booking.class, bookingId);
        if (booking == null) {
            throw new IllegalStateException("Booking not found with ID: " + bookingId);
        }
        return booking;
    }

    @Transactional
    public Booking addDetailToBooking(Long bookingId, B_Details detail) {
        Booking booking = entityManager.find(Booking.class, bookingId);
        if (booking != null) {
            booking.getDetails().add(detail);
            detail.setBooking(booking);
            entityManager.persist(detail);
        } else {
            throw new IllegalStateException("Booking not found with ID: " + bookingId);
        }
        return booking;
    }

    @Transactional
    public void removeDetailFromBooking(Long bookingId, Long detailId) {
        B_Details detail = entityManager.find(B_Details.class, detailId);
        Booking booking = entityManager.find(Booking.class, bookingId);
        if (booking != null && detail != null && booking.getDetails().contains(detail)) {
            booking.getDetails().remove(detail);
            entityManager.remove(detail);
        } else {
            throw new IllegalStateException("Detail or Booking not found with given IDs.");
        }
    }
}
