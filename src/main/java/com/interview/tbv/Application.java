package com.interview.tbv;

import com.interview.tbv.enums.Role;
import com.interview.tbv.model.Employee;
import com.interview.tbv.model.User;
import com.interview.tbv.service.EmployeeService;
import com.interview.tbv.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.interview.tbv.utility.Constants.ConsoleColor.ANSI_GREEN;
import static com.interview.tbv.utility.Constants.ConsoleColor.ANSI_RESET;
import static java.util.Arrays.asList;

/**
 * Create by Jm on 2021-01-18
 */
@SpringBootApplication
@Slf4j
public class Application {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    private final Environment env;

    public Application(Environment env) {
        this.env = env;
    }

    @PostConstruct
    private void init() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains("dev")) {
            //init user
            User user = userService.findUserToAuthenticate("userdev");
            if (Objects.isNull(user)) {
                user = User.builder()
                        .userName("userdev")
                        .name("dev")
                        .lastName("dev")
                        .email("dev@dev.com")
                        .password("Admin123456")
                        .build();
                user.addRole(Role.ADMIN);
                userService.saveUser(user);
            }
            log.info("{}dev account: {} / {} {}", ANSI_GREEN, user.getEmail(), user.getPassword(), ANSI_RESET);

            //init employee
            Employee employee = Employee.builder()
                    .id("EMP-1")
                    .name("James")
                    .isDirector(true)
                    .build();

            Employee employee2 = Employee.builder()
                    .id("EMP-2")
                    .name("Fiona")
                    .teamIds(Arrays.asList("T-1", "T-2"))
                    .isDirector(false)
                    .build();

            List<Employee> employees = asList(employee, employee2);

            //save data to db
            employeeService.saveAll(employees);
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application TBV Interview '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }
}
