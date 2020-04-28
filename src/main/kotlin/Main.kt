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

                body(classes = "mainBody") {
                    div {
                        div("row") {
                            div("buttonGrid homeC") {
                                a("/", classes = "topButtons") {
                                    +"Home"
                                }

                                a("Survey", classes = "topButtons") {
                                    +"Survey"
                                }

                                a("Data", classes = "topButtons") {
                                    +"Data"
                                }
                            }
                        }

                        div(classes = "iframeHolder home") {
                            img {
                                src = "/static/cs199projectbanner.png"
                            }

                            p(classes = "description") {
                                +"The purpose of this project is to visualize a dataset of responses regarding the effects of COVID-19. The data is collected from students, friends, relatives, and anyone who is willing to respond, and it is represented using a variety of Kotlin data science libraries such as Let's Plot, Kmath and Knumpy. This visual representation is hosted via KTOR, and the page was built using HTML DSL for KTOR."
                            }

                            p(classes = "description") {
                                +"If you are interested in seeing and/or editing the code for the project, here is a link to our "

                                a(href =  "https://github.com/mathewf2/CS199IKP", target = "_blank", classes = "githubLink") {
                                    +"Public Github"
                                }
                            }

                            p(classes = "description") {
                                +"Thank you for taking the time to explore information and provide your feedback on the impact of Covid-19. We hope you stay safe and maintain compassion during these frustrating times. We will get through this together!"
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
        get("/Survey") {
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

                                a("Survey", classes = "topButtons") {
                                    +"Survey"
                                }

                                a("Data", classes = "topButtons") {
                                    +"Data"
                                }
                            }
                        }

                        div(classes = "iframeHolder") {
                            h1("title") {
                                +"Please Take Our Survey! Share Your Opinions!"
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

                                a("Survey", classes = "topButtons") {
                                    +"Survey"
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
