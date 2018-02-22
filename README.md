Regularly, I'm more of a Scala guy for backend stuff in case the JVM is involved. However, since Kotlin becomes more and more popular, esp. due to its very good bidirectional interop with Java, it seems to be a good idea to at least play a bit with it ... and see if things are roughly as good as with Scala (or maybe better?).

Tasks:
- `./gradlew run` for running on `localhost:8080`
- `./gradlew shadowJar` building a 'fat jar' (will end up in `build/libs`). Can be started using `java -jar` afterwards.
- `./gradlew build` creates a build and distribution; the latter can be found in `tar` and `zip` format in `build/distributions` afterwards. Both contain a `lib` folder with the required jars and a `bin` folder with the corresponding start script(s).