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
import java.net.URL


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
                                a("/", classes = "topButtons") {
                                    +"Home"
                                }

                                a("Data", classes = "topButtons") {
                                    +"Data"
                                }
                            }
                        }

                        div(classes = "iframeHolder") {
                            h1("title") {
                                +"Kotlin Data Science Project: CS199IKP"
                            }
                            iframe() {
                                src =
                                    "https://docs.google.com/forms/d/e/1FAIpQLScSOG770icQDBoCc0geBc9ynS8uFAlcai3gW_ih-3Yj28A8Hw/viewform?embedded=true"
                            }
                        }
                    }
                    script(type = ScriptType.textJavaScript) {
                        unsafe {
                            raw(
                                """
                                    function my() { return 1; }
                                    
                                    """
                            )
                        }
                    }
                    div (classes = "footer") {
                        h4 (classes = "credit") {
                            +"A UIUC CS199IKP Project by Mathew Farley and Noah Rogers"
                        }
                    }
                }
            }
        }
        get("/Data") {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/static/main.css")
                }

                body {
                    div {
                        div("row") {
                            div("buttonGrid homeC") {
                                a("/", classes = "topButtons") {
                                    +"Home"
                                }

                                a("Data", classes = "topButtons") {
                                    +"Data"
                                }
                            }
                        }

                        div(classes = "iframeHolder") {

                        }
                    }
                    div (classes = "footer") {
                        h4 (classes = "credit") {
                            +"A UIUC CS199IKP Project by Mathew Farley and Noah Rogers"
                        }
                    }
                }
            }
        }
    }
}

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::data).start(wait = true)
}
