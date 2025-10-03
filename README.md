# meme-generator

This project uses Quarkus, the Supersonic Subatomic Java Framework.
It is based on [Markus Eisele's meme generator](https://www.the-main-thread.com/p/quarkus-java-meme-generator-api), but adds a command-line interface. 

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

Also for picocli applications the dev mode is supported. When running dev mode, the picocli application is executed and on press of the Enter key, is restarted.

As picocli applications will often require arguments to be passed on the commandline, this is also possible in dev mode via:

```shell script
./mvnw quarkus:dev -Dquarkus.args='Quarky'
```

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Execute your native executable with: `./target/meme-generator-1.0.0-SNAPSHOT-runner`


## Related Guides

- Picocli ([guide](https://quarkus.io/guides/picocli)): Develop command line applications with Picocli
- Building native executables <https://quarkus.io/guides/maven-tooling>.

