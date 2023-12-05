package ch.zli.m223.controller;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.service.ApplicationUserService;

@Path("/members")
@Tag(name = "Members", description = "Handling of members")
public class ApplicationUserController {

    @Inject
    private ApplicationUserService userService;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response registerUser(ApplicationUser user) {
        ApplicationUser createdUser = userService.createUser(user);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public List<ApplicationUser> getAllMembers() {
        return userService.findAll();
    }

    @DELETE
    @Path("/{userId}")
    @RolesAllowed("Admin")
    public Response deleteMember(@PathParam("userId") Long userId) {
        userService.deleteUser(userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public ApplicationUser updateMember(@PathParam("userId") Long userId, ApplicationUser member) {
        return userService.updateUser(userId, member);
    }

}
