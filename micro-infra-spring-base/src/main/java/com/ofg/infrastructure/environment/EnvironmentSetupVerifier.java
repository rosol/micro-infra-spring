package com.ofg.infrastructure.environment;

import com.google.common.base.Joiner;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

import java.util.Arrays;
import java.util.List;

/**
 * Spring {@link ApplicationListener} that verifies that you have provided a spring profile upon
 * application execution (via spring.profiles.active system property). If it's not provided the
 * application will close with error.
 *
 * @see ApplicationListener
 */
public class EnvironmentSetupVerifier implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    public EnvironmentSetupVerifier(List<String> allPossibleSpringProfiles) {
        this.allPossibleSpringProfiles = allPossibleSpringProfiles;
    }

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        String[] activeProfiles = event.getEnvironment().getActiveProfiles();
        if (activeProfiles.length == 0 || !allPossibleSpringProfiles.containsAll(Arrays.asList(activeProfiles))) {
            System.out.println("\\n            This app requires an explicit profile\n            Please setup a profile in environment variable 'spring.profiles.active'\n            or pass -Dspring.profiles.active=NAME_OF_PROFILE as a JVM param\n            Possible profiles: " + Joiner.on(",").join(activeProfiles));
            System.exit(1);
        }

        System.out.println("Application is run with these active profiles: " + Joiner.on(",").join(activeProfiles));
    }

    private final List<String> allPossibleSpringProfiles;
}
