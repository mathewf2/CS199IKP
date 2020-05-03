
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.html.*

fun Application.data() {
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
                                    href = "https://github.com/mathewf2/CS199IKP",
                                    target = "_blank",
                                    classes = "githubLink") {
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
                    div(classes = "footer") {
                        h4(classes = "credit") {
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

                    div(classes = "footer") {
                        h4(classes = "credit") {
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
                    div(classes = "footer") {
                        h4(classes = "credit") {
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
                            p(classes = "description ") {
                                +"""
                                This project was created by Noah Rogers and Mat Farley for the University of Illinois's CS 199: IKP (Imperative Kotlin Programming) Class.
                                The purpose of this project was to visualize a collection of data regarding the impacts of COVID-19. Our shift in focus to data science was mainly due to personal interests, data science's importance to society, and also, due to Kotlin's rise in libraries and compatibility within the field.
                                """
                                br { }
                                br { }
                                +"""
                                Ultimately, our goal was to collect data ourselves (via Google Forms, accessed via Google Sheets API), represent that data through Lets-Plot-Kotlin, host it on a KTOR server, utilize coroutines to implement a live updating feature to constantly fetch new survey results, and host it on AWS.
                                Noah primarily dealt with the front-end work and the coroutines, while Mat worked on the backend and data collection. Ultimately, our biggest struggles arose from working with compatibility issues and lack of documentation in some of our Kotlin libraries. Understandable considering it's a new language.
                                """
                                br { }
                                br { }
                                +"""Lets Plot, though written in Kotlin natively, is primarily a Python library where not all of the features have been appropriately ported over to support the Kotlin language. Lets Plot does, however, allow for a huge variety of exciting visual representations to play with, which can lead to some powerful graphs (more powerful than ours, surely!)
                                The survey and purpose, though many steps were taken to avoid this, are inherently biased towards individuals in a college setting. We spent a lot of time trying to make it as generic and relevant to all populations as much as possible, but we still faulted there in some regard. Both of us are a bit new to the field of data science, so it certainly is not our expertise.
                                """
                            }
                        }
                    }
                    div(classes = "footer") {
                        h4(classes = "credit") {
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
            while (true) {
                Data.plot()
                delay(3500)
            }
        }
        launch {
            println("Running")
            println("Main " + System.getProperty("user.dir"))
            embeddedServer(
                Netty,
                watchPaths = listOf("KotlinDataScience", "/IdeaProjects/KotlinDataScience", "/IdeaProjects/KotlinDataScience/src/main/resources/static/",
                                    "/KotlinDataScience/src/main/kotlin/", "KotlinDataScience/src/main/resources", "KotlinDataScience/build/resources/main/static/iframetest.html"),
                port = 8080,
                module = Application::data
            ).start()
        }
    }
}
