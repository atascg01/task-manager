package com.atascg.taskManager.resources;

import com.atascg.taskManager.dao.TaskDAO;
import com.atascg.taskManager.domain.Task;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
class TaskResourceTest {

    private static final TaskDAO DAO = mock(TaskDAO.class);
    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new TaskResource(DAO))
            .build();

    private Task task;
    int taskId = 1;

    @BeforeEach
    void setup() {
        task = new Task();
        task.setText("Example task");
        task.setId(taskId);
    }

    @AfterEach
    void tearDown() {
        reset(DAO);
    }

    @Test
    void getTaskList() {
        when(DAO.findAll()).thenReturn(List.of(task, task));
        List<Task> taskList = EXT.target("/api/v1/tasks/all").request().get(List.class);

        Assertions.assertEquals(taskList.size(), 2);
        verify(DAO).findAll();
    }

    @Test
    void deleteTask() {
        when(DAO.deleteById(taskId)).thenReturn(task);
        Task requestTask = EXT.target("/api/v1/tasks/delete/1").request().delete(Task.class);

        Assertions.assertEquals(requestTask, task);
        verify(DAO).deleteById(taskId);
    }

    @Test
    void createTask() {

        Task requestTask = EXT.target("/api/v1/tasks/create/Example task").request().post(Entity.entity(task, MediaType.APPLICATION_JSON), Task.class);

        Assertions.assertEquals(requestTask.getText(), task.getText());
        verify(DAO).save(requestTask);
    }


}