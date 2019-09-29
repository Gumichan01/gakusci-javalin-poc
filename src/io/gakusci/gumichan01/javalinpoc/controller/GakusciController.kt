package io.gakusci.gumichan01.javalinpoc.controller

import io.javalin.http.Context
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GakusciController {

    private val logger: Logger = LoggerFactory.getLogger(GakusciController::class.java)

    fun home(context: Context) {
        context.result("""<p>Hello Gakusci POC client</p>
            <p>/!\ This webapp is a Proof of Concept /!\</p>
            <strong>DO NOT USE IT IN PRODUCTION</strong>""")
    }

    fun search(context: Context) {
        val resultString: String? = context.queryParam("q")
        logger.info("query: $resultString")
        resultString?.let { context.json(it) }
    }
}