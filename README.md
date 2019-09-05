# nanithing-back

A backend for the Name-Animal-Thing game we used to play in school, implemented with enterprise technologies.

## Launching

Development launch:

```shell
mvn process-classes quarkus:dev
```

Note that it requires the `process-classes` goal, not just `compile`. The reason is that at least the `nanithing-jaxrs-interfaces`	
project needs to be indexed by Jandex, in order to activate the JAX-RS resources that implement those interfaces, and the Jandex goal
runs in the `process-classes` phase, which is right after `compile` [by default](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html).
