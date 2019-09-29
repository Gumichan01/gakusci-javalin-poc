package io.gakusci.gumichan01.javalinpoc.controller

import io.gakusci.gumichan01.javalinpoc.restapi.GakusciHttpClient
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
        val query: String? = context.queryParam("q")
        // Just a quick and dirty code
        val halService = object : Any() {
            private val halUrl = "https://api.archives-ouvertes.fr/search/?q=%s&wt=json"
            fun searchForResourceName(query: String): String {
                val url = halUrl.format(query)
                return GakusciHttpClient(url).get()
            }
        }

        logger.info("query: $query")
        if (query != null) {
            val documentEntries = halService.searchForResourceName(query)
            context.status(200)
            logger.info("result: \n $documentEntries")
            context.json(documentEntries)
        } else {
            context.status(204)
            context.result("")
        }
    }
}