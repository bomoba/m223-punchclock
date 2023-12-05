package ch.zli.m223.controller;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.B_Details;
import ch.zli.m223.service.BookingService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingController {

    @Inject
    private BookingService bookingService;

    @GET
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GET
    @Path("/{bookingID}")
    public Booking getBookingById(@PathParam("bookingID") Long bookingID) {
        return bookingService.getBookingById(bookingID);
    }

    @POST
    public Booking createBooking(Booking booking) {
        return bookingService.createBooking(booking);
    }

    @PUT
    @Path("/{bookingID}")
    public Booking updateBooking(@PathParam("bookingID") Long bookingID, Booking booking) {
        return bookingService.updateBooking(bookingID, booking);
    }

    @DELETE
    @Path("/{bookingID}")
    public void deleteBooking(@PathParam("bookingID") Long bookingID) {
        bookingService.deleteBooking(bookingID);
    }

    @POST
    @Path("/{bookingId}/details")
    public Booking addBookingDetail(@PathParam("bookingId") Long bookingId, B_Details detail) {
        return bookingService.addDetailToBooking(bookingId, detail);
    }

    @DELETE
    @Path("/{bookingId}/details/{detailId}")
    public void removeBookingDetail(@PathParam("bookingId") Long bookingId, @PathParam("detailId") Long detailId) {
        bookingService.removeDetailFromBooking(bookingId, detailId);
    }

}
