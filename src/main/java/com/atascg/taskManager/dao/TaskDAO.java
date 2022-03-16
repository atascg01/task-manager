package com.atascg.taskManager.dao;

import com.atascg.taskManager.domain.Task;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class TaskDAO extends AbstractDAO<Task> {

    public TaskDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Task> findAll() {
        return list(namedTypedQuery("task.findAll"));
    }

    public Optional<Task> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public void save(Task task) {
        currentSession().save(task);
    }

    public void deleteById(long id) {
        Task task = findById(id).orElse(null);
        currentSession().delete(task);
    }
}
