package ch.zli.m223.controller;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.service.ApplicationUserService;

@Path("/members")
@Tag(name = "Members", description = "Handling of members")
@RolesAllowed({ "Member", "Admin" })
public class ApplicationUserController {

    @Inject
    ApplicationUserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all members.", description = "Returns a list of all members.")
    public List<ApplicationUser> index() {
        return userService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new member.", description = "Creates a new member and returns the added member.")
    @PermitAll
    public ApplicationUser create(ApplicationUser member) {
        return userService.createUser(member);
    }

    @Path("/{memberId}")
    @DELETE
    @Operation(summary = "Delete a member.", description = "Deletes a member by ID.")
    public void delete(@PathParam("memberId") Long memberId) {
        userService.deleteUser(memberId);
    }

    @Path("/{memberId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a member.", description = "Updates a member by ID.")
    public ApplicationUser update(@PathParam("memberId") Long memberId, ApplicationUser member) {
        return userService.updateUser(memberId, member);
    }
}
