package ch.zli.m223.model;

import javax.persistence.*;

@Entity
public class B_Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long b_detailsID;

    @Column(nullable = false)
    private String fullDas;

    @Column
    private String halfDay;

    @Column
    private String room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_bookingID", referencedColumnName = "bookingID")
    private Booking booking;

    public B_Details() {
    }

    public Long getB_detailsID() {
        return b_detailsID;
    }

    public void setB_detailsID(Long b_detailsID) {
        this.b_detailsID = b_detailsID;
    }

    public String getFullDas() {
        return fullDas;
    }

    public void setFullDas(String fullDas) {
        this.fullDas = fullDas;
    }

    public String getHalfDay() {
        return halfDay;
    }

    public void setHalfDay(String halfDay) {
        this.halfDay = halfDay;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
