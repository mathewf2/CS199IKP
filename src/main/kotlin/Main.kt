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
import kotlinx.coroutines.*
import kotlinx.html.*
import java.awt.SystemColor.window
import java.io.File
import java.net.URL

/* ktlint does not like wildcard imports, here are the necessary imports if we want to run ktlint again.
 *
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.h4
import kotlinx.html.head
import kotlinx.html.iframe
import kotlinx.html.img
import kotlinx.html.link
import kotlinx.html.p
import kotlinx.html.script
import kotlinx.html.unsafe
 */


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

                                a("About", classes = "topButtons") {
                                    +"About"
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

                                a(
                                    href =  "https://github.com/mathewf2/CS199IKP",
                                    target = "_blank",
                                    classes = "githubLink")
                                {
                                    +"Public Github"
                                }
                            }

                            p(classes = "description") {
                                +"Thank you for taking the time to explore information and provide your feedback on the impact of Covid-19. We hope you stay safe and maintain compassion during these frustrating times."
                                br {
                                    br {
                                        +"We will get through this together!"
                                    }
                                }
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

                                a("About", classes = "topButtons") {
                                    +"About"
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

                                a("About", classes = "topButtons") {
                                    +"About"
                                }
                            }
                        }
                        div(classes = "iframeHolder") {
//                            button() {
//                                onClick = "update()"
//                                +"Update"
//                            }
                            iframe {
                                src = "/static/iframetest.html"
                            }
                        }
                    }
                    script(type = ScriptType.textJavaScript) {
                        unsafe {
                            raw(
                                """
                                    function update() {
                                        alert(data.main())
                                        console.log("it worked");
                                    }
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
        get("/About") {
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

                                a("About", classes = "topButtons") {
                                    +"About"
                                }
                            }
                        }
                        div(classes = "iframeHolder") {
                            p (classes = "description") {
                                + "This project  was created by Noah Rogers and Mat Farley for University of Illinois' CS 199: IKP (Imperative Kotlin Programming).\n\n"
                                + "\n"
                                + "\n"
                                + "\n The purpose of this project is visualize a collection of data regarding the impacts of COVID-19. We decided to head in in a data science direction as it was an interest for both of us, and because of Kotlin's rise in libraries and compatibilty within the field.\n"
                                + "\n"
                                + "\n Ultimately, our goal was to collect data ourselves (via Google Forms, accessed via Google Sheets API), represent that data through Lets-Plot-Kotlin, host it on a KTOR server, utilize coroutines to implement a live updating feature to constantly fetch new survey results, and host it on AWS.\n"
                                + "\n"
                                + "\n Noah primarily dealt with the front-end work and the coroutines, while Mat worked on the backend and data collection. Ultimately, our biggest struggles arose from working with some of the incompatibility, or lack of documentation, for Kotlin considering it's a new language."
                                +" Lets Plot, though written in Kotlin natively, is primarily a Python library where not all of the features have been appropriately ported over to support the Kotlin language. Lets Plot does, however, allow for a huge variety of exciting visual representations to play with, which can lead to some powerful graphs (more powerful than ours, surely!)\n"
                                + "\n The survey and purpose, though many steps were taken to avoid this, are inherently biased towards individuals in a college setting. We spent a lot of time trying to make it as generic and relevant to all populations as much as possible, but we still faulted there in some regard. Both of us are a bit new to the field of data science, so it certainly is not our expertise."
                            }
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
    runBlocking {
        launch {
            delay(1000)
            while(true) {
                Data.plot()
                println("ran")
                delay(3500)
            }
        }
        launch {
            val src = File("src/main/resources/static/iframetest.html")
            if(src.exists()) {
                embeddedServer(
                    Netty,
                    watchPaths = listOf(src.absolutePath),
                    port = 8080,
                    module = Application::data
                ).start()
            }
        }
    }
}
