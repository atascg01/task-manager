package com.atascg.taskManager.resources;

import com.atascg.taskManager.dao.TaskDAO;
import com.atascg.taskManager.domain.Task;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/v1/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
//    private final String template;
//    private final AtomicLong counter;

    private final TaskDAO taskDAO;

    public TaskResource(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GET
    @Path("/all")
    @UnitOfWork
    public List<Task> getTasks() {
        return taskDAO.findAll();
    }

//    @GET
//    @Timed
//    @Path("/retrieve")
//    public Saying sayHello(@QueryParam("name") String name) {
//        System.out.println("asd");
//        final String value = String.format(template, name);
//        return new Saying(counter.incrementAndGet(), value);
//    }
}