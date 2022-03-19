package com.atascg.taskManager.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealth extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}