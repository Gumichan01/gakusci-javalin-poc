package io.gakusci.gumichan01.javalinpoc

import io.gakusci.gumichan01.javalinpoc.controller.GakusciController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get

fun main() {
    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start(8081)

    app.routes {
        get("/") { ctx -> GakusciController().home(ctx) }
        get("/search/") { ctx -> GakusciController().search(ctx) }
    }
}


