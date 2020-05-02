import KotlinDataScience.data
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication

class TestMain : StringSpec({
    "should retrieve root path properly" {
        withTestApplication(Application::data) {
            handleRequest(HttpMethod.Get, "/").apply {
                response.status() shouldBe HttpStatusCode.OK
                response.content shouldBe "OK"
            }
        }
    }
})
