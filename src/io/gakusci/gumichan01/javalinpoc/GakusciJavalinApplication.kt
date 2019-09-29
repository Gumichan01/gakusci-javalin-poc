package io.gakusci.gumichan01.javalinpoc

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get

fun main() {
    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start(8080)
    app.routes {
        get("/") { ctx ->
            ctx.result("""Hello world! 
                |/!\ THSI IS A POC. DO NOT USE IT IN PRODUCTION /!\ """.trimMargin())
        }
    }
}

