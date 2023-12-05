package ch.zli.m223.controller;

import ch.zli.m223.model.Question;
import ch.zli.m223.service.QuestionService;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/questions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionController {

    @Inject
    private QuestionService questionService;

    @GET
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @POST
    @RolesAllowed("Admin")
    public Question createQuestion(Question question) {
        return questionService.createQuestion(question);
    }

    @PUT
    @Path("/{questionID}")
    @RolesAllowed("Admin")
    public Question updateQuestion(@PathParam("questionID") Long questionID, Question question) {
        return questionService.updateQuestion(questionID, question);
    }

    @DELETE
    @Path("/{questionID}")
    @RolesAllowed("Admin")
    public void deleteQuestion(@PathParam("questionID") Long questionID) {
        questionService.deleteQuestion(questionID);
    }
}
