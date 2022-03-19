package com.atascg.taskManager.dao;

import com.atascg.taskManager.domain.Task;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class TaskDAO extends AbstractDAO<Task> {

    public TaskDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Task> findAll() {
        return list(namedTypedQuery("task.findAll"));
    }

    public Task findById(long id) {
        return get(id);
    }

    public Task save(Task task) {
        currentSession().save(task);
        return task;
    }

    public Task updateById(long id, Task task) {
        Task databaseTask = findById(id);
        databaseTask.setText(task.getText());
        databaseTask.setCompleted(task.isCompleted());
        return databaseTask;
    }

    public Task deleteById(long id) {
        Task task = findById(id);
        currentSession().delete(task);
        return task;
    }
}
