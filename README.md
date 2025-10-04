# meme-generator

This project uses Quarkus, the Supersonic Subatomic Java Framework.
It is based on [Markus Eisele's meme generator](https://www.the-main-thread.com/p/quarkus-java-meme-generator-api), but
adds a command-line interface.

## Creating a native executable

On MacOS, AWT is not supported in native applications.
Instead, use a container for building and running the application.

```shell
podman run -it --rm --entrypoint /bin/bash -v `pwd`:/project:z -v ~/.m2:/maven:z quay.io/quarkus/ubi9-quarkus-mandrel-builder-image:jdk-21
```

```shell
./mvnw package -Pnative -Dmaven.repo.local=/maven/repository/
```

Execute your native executable with: `./target/meme-generator-1.0.0-SNAPSHOT-runner "say hello world"`
Another good invocation is `./target/meme-generator-1.0.0-SNAPSHOT-runner "i had cake" cat`

You can then run `open meme.jpg` on the local machine to see the output.
Keep it open in the IDE and it will update when new memes are generated.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev -Dquarkus.args='meme text'
```

Also for picocli applications the dev mode is supported. When running dev mode, the picocli application is executed and
on press of the Enter key, is restarted.

## Image formats

Note that any new images you add need to *not* have alpha enabled (they should be 24-bit colour, not 32-bit colour).

## Related Guides

- Picocli ([guide](https://quarkus.io/guides/picocli)): Develop command line applications with Picocli
- Building native executables <https://quarkus.io/guides/maven-tooling>.

