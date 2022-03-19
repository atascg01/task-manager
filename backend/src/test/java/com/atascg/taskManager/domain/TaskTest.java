package com.atascg.taskManager.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static io.dropwizard.jackson.Jackson.newObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

public class TaskTest {

    private static final ObjectMapper MAPPER = newObjectMapper();

    @Test
    void serializesToJSON() throws Exception {
        final Task task = new Task(1, "Example text", false);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(getClass().getResource("/task.json"), Task.class));

        assertThat(MAPPER.writeValueAsString(task)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Task task = new Task(1, "Example text", false);
        assertThat(MAPPER.readValue(getClass().getResource("/task.json"), Task.class))
                .isEqualTo(task);
    }
}
