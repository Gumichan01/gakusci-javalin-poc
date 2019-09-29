package io.gakusci.gumichan01.javalinpoc.restapi

import org.eclipse.jetty.client.HttpClient
import org.eclipse.jetty.client.api.ContentResponse
import org.eclipse.jetty.util.ssl.SslContextFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

/*
    This is a basic HTTP client.

    It will just perform a very simple HTTP GET request
*/
class GakusciHttpClient(private val url: String) {

    private val logger: Logger = LoggerFactory.getLogger(GakusciHttpClient::class.java)

    init {
        logger.info(url)
    }

    fun get(): String {
        // Since Javalin does not have a high level rest client consumer, like RestTemplate in Spring
        // I use Jetty's HttpClient. This class is not difficult to use but you have to convert the JSON
        // to an object by yourself.
        val client = HttpClient(SslContextFactory.Client(true))

        try {
            client.start()
            val response: ContentResponse = client.newRequest(url).timeout(16, TimeUnit.SECONDS).send()
            val content = String(response.content)
            logger.info("status: ${response.status}")
            logger.info("content: $content")    // Ok, I can get the result of the call by using Jetty's HttpClient
            return content
        } finally {
            client.stop()
        }
    }
}