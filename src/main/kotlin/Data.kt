import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import jetbrains.datalore.plot.PlotHtmlExport
import jetbrains.letsPlot.GGBunch
import jetbrains.letsPlot.geom.geom_bar
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.ggtitle
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.lets_plot

/* Guidance in setting up the Data() class taken from: https://www.youtube.com/watch?v=8yJrQk9ShPg
 * The tutorial is for java, but it functions the same per Kotlin, nonetheless.
 * Effectively, this gives the user access to the sheets requested, and saves their authorization locally.*/
class Data {
    var appName = "CS 199 IKP"
    var id = "1JWxwV81A2hFBWK0eO30wFkGmuEtHxDImCKGx9KLdlZA"

    private fun authorize(): Credential {
        val input: InputStream = this.javaClass.getResourceAsStream("/credentials.json")
        val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(
            JacksonFactory.getDefaultInstance(), InputStreamReader(input)
        )

        val scopes = listOf(SheetsScopes.SPREADSHEETS)

        val flow = GoogleAuthorizationCodeFlow.Builder(
            GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
            clientSecrets, scopes
        )
            .setDataStoreFactory(FileDataStoreFactory(File("tokens")))
            .setAccessType("offline")
            .build()
        return AuthorizationCodeInstalledApp(
            flow, LocalServerReceiver()
        ).authorize("user")
    }

    private fun getSheetsService(): Sheets {
        val credential = authorize()
        return Sheets.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            JacksonFactory.getDefaultInstance(), credential
        )
            .setApplicationName(appName)
            .build()
    }

    /* Static plot() */
    companion object {
        /* This fetches the data from the Google Sheets and plots graphs based on the data received.
     * The commented out blocks of code are for fetching other data in the survey. Currently not using that data, so
     * they were commented out to prevent unnecessary requests. */
        fun plot() {
            val service = Data().getSheetsService()
            val generalRange = "B:I"
            /*
        val studentRange = "J:R"
        val facultyRange = "S:V"
        val financialRange = "W:Z"
         */

            val generalResponse = service.spreadsheets().values()
                .get(Data().id, generalRange)
                .execute()
            /*
        val studentResponse = service.spreadsheets().values()
            .get(id, studentRange)
            .execute()
        val facultyResponse = service.spreadsheets().values()
            .get(id, facultyRange)
            .execute()
        val financialResponse = service.spreadsheets().values()
            .get(id, financialRange)
            .execute()

         */

            val generalEntries = generalResponse.getValues()

            val generalMap = mutableMapOf<String, List<Any>?>()
            for (list in generalEntries) {
                for (i in 0 until list.size) {
                    val question = generalEntries[0][i].toString()
                    if (generalMap.size < list.size) {
                        generalMap[question] = listOf()
                    } else if (list[i] != " ") {
                        generalMap[question] = generalMap[question]?.plus(list[i])
                    }
                }
            }

            /*
        val studentEntries = studentResponse.getValues().filter { e ->
            e.isNotEmpty() && e[0] != "No (You don't need to answer and may skip to the next page)"
        }
        val facultyEntries = facultyResponse.getValues().filter { e ->
            e.isNotEmpty() && e[0] != "No (You don't need to answer and may skip to the next page)"
        }
        val financialEntries = financialResponse.getValues()

         */

            val test = HashMap<Any, Int>()
            for (submission in generalEntries) {
                var count = test.getOrDefault(submission[0], 0)
                test[submission[0]] = ++count
            }

            val plots = mutableListOf<Plot>()
            for (key in generalMap.keys) {
                val plot = lets_plot(generalMap[key]) + geom_bar() + ggtitle(key)
                plots.add(plot)
                if (key.length > 50) {
                    plots[plots.lastIndex] = plots.last() + ggsize(9 * key.length, 300)
                }
            }

            val h = 500
            val w = 750
            val bunch = GGBunch()
            for ((count, plot) in plots.withIndex()) {
                bunch.addPlot(plot, (count % 3) * w, (count / 3) * h)
            }

            /* When run locally, it saves the following file properly. However, when using shadowJar to create the fat
             * jar, and then running the .jar file, it says that directory does not exist. Changing the directory to
             * ../resources/main/static/iframetest.html works, but does not work locally.
             */
            println("saved to file")
            // Saves the bunch to an iframe; html and svg are options as well
            val iframe = PlotHtmlExport.buildHtmlFromRawSpecs(bunch.toSpec(), iFrame = true)
            File("../resources/main/static/iframetest.html").writeText(iframe)
        }
    }
}
