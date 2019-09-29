package io.gakusci.gumichan01.javalinpoc.controller

import io.gakusci.gumichan01.javalinpoc.domain.model.DocumentEntry
import io.gakusci.gumichan01.javalinpoc.domain.service.IService
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
        val halService: IService = object : IService {
            override fun searchForResourceName(query: String): List<DocumentEntry> {
                return listOf(DocumentEntry("hello","world"), DocumentEntry("baka","nani"))
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