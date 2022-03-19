package com.atascg.taskManager;

import com.atascg.taskManager.configuration.TaskManagerConfiguration;
import com.atascg.taskManager.dao.TaskDAO;
import com.atascg.taskManager.domain.Task;
import com.atascg.taskManager.health.TemplateHealth;
import com.atascg.taskManager.resources.TaskResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class TaskManagerApplication extends Application<TaskManagerConfiguration> {
    public static void main(String[] args) throws Exception {
        new TaskManagerApplication().run(args);
    }

    private final HibernateBundle<TaskManagerConfiguration> hibernateBundle = new HibernateBundle<>(Task.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TaskManagerConfiguration taskManagerConfiguration) {
            return taskManagerConfiguration.getDataSourceFactory();
        }
    };

    @Override
    public void run(TaskManagerConfiguration configuration,
                    Environment environment) {
        final TaskDAO taskDAO = new TaskDAO(hibernateBundle.getSessionFactory());
        final TemplateHealth healthCheck = new TemplateHealth();
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(new TaskResource(taskDAO));

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin,Access-Control-Allow-Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    @Override
    public void initialize(Bootstrap<TaskManagerConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor())
        );
        bootstrap.addBundle(hibernateBundle);
    }

}