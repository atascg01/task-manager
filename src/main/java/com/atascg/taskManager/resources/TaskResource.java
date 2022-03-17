package com.atascg.taskManager.resources;

import com.atascg.taskManager.dao.TaskDAO;
import com.atascg.taskManager.domain.Task;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
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

    @PUT
    @Path("/update/{id}")
    @UnitOfWork
    public void updateTask(@PathParam("id") long id, Task task) {
        taskDAO.updateById(id, task);
    }

    @DELETE
    @Path("/delete/{id}")
    @UnitOfWork
    public void deleteTaskById(@PathParam("id") long id) {
        taskDAO.deleteById(id);
    }

    @POST
    @Path("/create/{text}")
    @UnitOfWork
    public Task createTask(@PathParam("text") String text) {
        Task task = new Task();
        task.setText(text);
        task.setCompleted(false);
        taskDAO.save(task);
        return task;
    }
}