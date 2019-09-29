package io.gakusci.gumichan01.javalinpoc

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun main() {
    val logger: Logger = LoggerFactory.getLogger("GakusciJavalinApplicationKt")
    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start(8081)
    app.routes {
        get("/") { ctx ->
            ctx.result("""<p>Hello Gakusci POC client</p>
            <p>/!\ This webapp is a Proof of Concept /!\</p>
            <strong>DO NOT USE IT IN PRODUCTION</strong>""")
        }

        get("/search/") { ctx ->
            val resultString: String? = ctx.queryParam("q")
            logger.info("query: $resultString")
            resultString?.let { ctx.json(it) }
        }
    }
}


