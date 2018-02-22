package app

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import java.time.OffsetDateTime

class MainVerticle : AbstractVerticle() {
    private companion object {
        private val LOG = LoggerFactory.getLogger(MainVerticle::class.java)
    }

    override fun start(startFuture: Future<Void>) {
        val router = createRouter()

        vertx.createHttpServer()
                .requestHandler { router.accept(it) }
                .listen(config().getInteger("http.port", 8080)) { result ->
                    if (result.succeeded()) {
                        startFuture.complete()
                    } else {
                        startFuture.fail(result.cause())
                    }

                }
    }

    private fun createRouter() = Router.router(vertx).apply {
        get("/hello").handler(helloHandler)
        route().handler(notFoundHandler)
    }

    private val helloHandler = Handler<RoutingContext> { context ->
        LOG.debug("Received request from=${context.request().remoteAddress()}")
        val currentTime = OffsetDateTime.now()
        val userAgent = context.request().getHeader("User-Agent")
        context.response().end("Hello $userAgent! My current time is: $currentTime")
    }

    private val notFoundHandler = Handler<RoutingContext> { context ->
        val notFoundResponse = """
            <html lang="en">
                <head>
                    <style>
                        h2 {
                            text-align:center;
                        }

                        .italic {
                            font-style: italic;
                            margin-top: 2rem;
                        }
                    </style>
                </head>
                <body>
                    <div>
                      <h2>404 | Well, That about Wraps It Up for This Page</h2>
                      <blockquote>
                        <p>’I refuse to prove that I exist,’ says Page, ’for proof denies faith, and without faith I am nothing.’</p>
                        <p>’But,’ says Man, ’The Babel fish is a dead giveaway, isn’t it? It could not have evolved by chance. It proves you exist, and so therefore, by your own arguments, you don’t. QED.’</p>
                        <p>’Oh dear,’ says Page, ’I hadn’t thought of that,’ and promptly disappears in a puff of logic.</p>
                        <p class="italic">– Thanks to <a>https://www.elastic.co</a> for this story</p>
                      </blockquote>
                    </div>
                </body>
            </html
        """.trimIndent()

        context
                .response()
                .putHeader("Content-Type", "text/html; charset=utf-8")
                .setStatusCode(404)
                .end(notFoundResponse)
    }
}