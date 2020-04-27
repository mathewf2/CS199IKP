import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.html.respondHtml
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.*

import java.awt.SystemColor.window


fun Application.data() {
    install(FreeMarker)
    routing {

        static("/static") {
            resources("static")
        }
        get("/") {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/static/main.css")
                }

                body {
                    div {
                        div("row") {
                            div("buttonGrid homeC") {
                                button(classes = "topButtons") {
                                    +"Home"
                                }

                                h1(" title topButtonsspec") {
                                    +"Kotlin Data Science Project: CS199IKP"
                                }

                                button(classes = "topButtons") {
                                    +"Data"
                                }
                            }
                        }

                        div {
//                            IFRAME("https://docs.google.com/forms/d/e/1FAIpQLScSOG770icQDBoCc0geBc9ynS8uFAlcai3gW_ih-3Yj28A8Hw/viewform?embedded=true") {
//
//                            }
                        }
                    }
                    script(type = ScriptType.textJavaScript) {
                        unsafe {
                            raw("""
                                    function my() { return 1; }
                                    """)
                        }
                    }
                }
            }
        }
    }
}

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::data).start(wait = true)
    Data().main()
}
