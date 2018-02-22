package app

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.logging.LoggerFactory

fun main(args: Array<String>) {
    val LOG = LoggerFactory.getLogger("My-Vertx-App")

    val vertx = Vertx.vertx()

    val opts = DeploymentOptions()
    vertx.deployVerticle(MainVerticle(), opts, { deploy ->
        if (deploy.succeeded()) {
            LOG.info("MainVerticle has been deployed successfully.")
        } else {
            LOG.warn("MainVerticle could not be deployed, due to ${deploy.cause()}")
        }

    })
}