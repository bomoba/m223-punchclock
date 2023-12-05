package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.service.ApplicationUserService;

@Path("/member")
@Tag(name = "Member", description = "Handling of member")
@RolesAllowed({ "Member", "Admin" })
public class ApplicationUserController {

    @Inject
    ApplicationUserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all member.", description = "Returns a list of all member.")
    public List<ApplicationUser> index() {
        return userService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new member. Also known as registration.", description = "Creates a new member and returns the newly added member.")
    @PermitAll
    public ApplicationUser create(ApplicationUser member) {
        return userService.createUser(member);
    }

    @Path("/{memberID}")
    @DELETE
    @Operation(summary = "Deletes an member.", description = "Deletes an member by its id.")
    public void delete(@PathParam("memberID") Long memberID) {
        userService.deleteUser(memberID);
    }

    @Path("/{memberID}")
    @PUT
    @Operation(summary = "Updates an member.", description = "Updates an member by its id.")
    public ApplicationUser update(@PathParam("memberID") Long memberID, ApplicationUser member) {
        return userService.updateUser(memberID, member);
    }
}
