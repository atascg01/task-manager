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

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
class TaskResourceTest {

    private static final TaskDAO DAO = mock(TaskDAO.class);
    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new TaskResource(DAO))
            .build();
    private Task task;

    @BeforeEach
    void setup() {
        task = new Task();
        task.setId(1L);
    }

    @AfterEach
    void tearDown() {
        reset(DAO);
    }

    @Test
    void getTaskList() {

        when(DAO.findAll()).thenReturn(List.of(new Task(), new Task()));
        List<Task> taskList = EXT.target("/api/v1/tasks/all").request().get(List.class);

        Assertions.assertEquals(taskList.size(), 2);
        verify(DAO).findAll();
    }


}