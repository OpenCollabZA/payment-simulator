# payment-simulator
Implementations of payment gateway simulators

# Running from IDE
You can run the simulator from an IDE either by using the SpringBoot integration if the IDE supports it.
Or buy running the main method of the spring boot application.

## Configuration
While developing you might want to change configuration such as the port on which the simulator is running.
To load an external file you can add a spring "program parameter" to load an additional file. E.g.
`--spring.config.additional-location=/home/user/payment-simulator.yml`

```yaml
server:
  port: 8081
```

# Building docker

