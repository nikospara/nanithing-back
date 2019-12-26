# nanithing-back

A backend for the Name-Animal-Thing game we used to play in school, implemented with enterprise technologies.

## The build system

The build system is Maven and is configured by a set of properties and profiles, as follows:

### Build properties

The following properties are local to an environment; they can be specified as `-Dpropname=propvalue` command line arguments,
or placed in a local Maven profile in `~/.m2/settings.xml`.

- `database.nanithing.url`: The JDBC URL of the database
- `database.nanithing.username`: The DB user name
- `database.nanithing.password`: The DB password
- `db.env` (default: `dev`): Needed only by Liquibase to indicate which environment-specific [contexts](https://www.liquibase.org/documentation/contexts.html)
will it activate; e.g. `dev` will activate the `data-dev` context

### Build profiles

- `h2`: Activate the H2 database for the server and Liquibase (currently this is the only DB option)
- `dbupdate`: Exwcute Liquibase to bring the database up-to-date (in the case of embedded H2 it will create it if it doesn't exist; just make sure that the directory exists)
- `test-h2`: This will activate the DAO tests, using an in-memory H2 database

## Building

### Creating/updating the DB

Decide the database to use and make sure Maven picks up the corresponding properties.

Assuming that the properties are defined through a Maven profile, e.g. like the following in `~/.m2/settings.xml`:

```xml
                <profile>
                        <id>nanithing-local-h2</id>
                        <properties>
                                <database.nanithing.url>jdbc:h2:/home/myuser/h2/nanithing</database.nanithing.url>
                                <database.nanithing.username>sa</database.nanithing.username>
                                <database.nanithing.password>sa</database.nanithing.password>
                        </properties>
                </profile>
```

Then make sure that the directory `/home/myuser/h2/nanithing` exists and run:

```shell
mvn process-resources -Ph2,dbupdate,nanithing-local-h2
```

Otherwise, you have to specify the properties by command line:

```shell
mvn process-resources -Ph2,dbupdate -Ddatabase.nanithing.url=... -Ddatabase.nanithing.username=... -Ddatabase.nanithing.password=...
```

### Building the JAR artifacts

```shell
mvn clean package -Ph2,test-h2
```

### Building the native artifacts

**TODO**

### Building the Docker image

**TODO**

## Launching

You have to build first! At least `mvn ... package` is required.

Development launch, assuming the profile `nanithing-local-h2` is defined in `settings.xml` as above:

```shell
mvn process-classes quarkus:dev -Ph2,nanithing-local-h2
```

Note that it requires the `process-classes` goal, not just `compile`. The reason is that at least the `nanithing-jaxrs-interfaces`	
project needs to be indexed by Jandex, in order to activate the JAX-RS resources that implement those interfaces, and the Jandex goal
runs in the `process-classes` phase, which is right after `compile` [by default](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html).


## Swagger

Quarkus can expose its API description through an OpenAPI specification. You can see the API at `your_domain/swagger-ui` and find its responding .yaml in `your_domain/openapi`. 
For reference you can check the following [tutorial](https://quarkus.io/guides/openapi-swaggerui-guide).

## Keycloak 

To install keycloak on your machine, please read the guide [here](https://www.keycloak.org/docs/latest/getting_started/index.html).
To run it locally on your machine proceed with: 
- Linux/Unix
```shell
$ cd bin
$ sudo ./standalone.sh -Djboss.socket.binding.port-offset=100
```

Afterwards, import the realm-export.json under nanithing-web package to your keycloak server.
Whenever you make a REST request, you will be redirected to a login page and asked for a username/password.