package ch.zli.m223.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingID;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_membersID", referencedColumnName = "memberID")
    private Member member;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<B_Details> details = new HashSet<>();

    public Booking() {
    }

    public Long getBookingID() {
        return bookingID;
    }

    public void setBookingID(Long bookingID) {
        this.bookingID = bookingID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set<B_Details> getDetails() {
        return details;
    }

    public void setDetails(Set<B_Details> details) {
        this.details = details;
    }

    public void addDetail(B_Details detail) {
        details.add(detail);
        detail.setBooking(this);
    }

    public void removeDetail(B_Details detail) {
        details.remove(detail);
        detail.setBooking(null);
    }
}
