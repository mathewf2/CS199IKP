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
import jetbrains.datalore.plot.PlotHtmlExport
import jetbrains.datalore.plot.PlotSvgExport
import jetbrains.datalore.plot.builder.theme.Theme
import jetbrains.datalore.plot.config.Option
import jetbrains.datalore.plot.config.theme.ThemeConfig
import jetbrains.letsPlot.*
import jetbrains.letsPlot.geom.geom_area
import jetbrains.letsPlot.geom.geom_bar
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.StatKind
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.BarMapping
import jetbrains.letsPlot.intern.toSpec
import jetbrains.letsPlot.stat.stat_count
import org.jetbrains.numkt.identity
import org.jetbrains.numkt.tile
import org.jetbrains.numkt.type
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.reflect.typeOf

class Data {
    var app_name = "CS 199 IKP"
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
            .setApplicationName(app_name)
            .build()
    }

    fun main() {
        val service = getSheetsService()
        val generalRange = "B:I"
        val studentRange = "J:R"
        val facultyRange = "S:V"
        val financialRange = "W:Z"

        val generalResponse = service.spreadsheets().values()
            .get(id, generalRange)
            .execute()
        val studentResponse = service.spreadsheets().values()
            .get(id, studentRange)
            .execute()
        val facultyResponse = service.spreadsheets().values()
            .get(id, facultyRange)
            .execute()
        val financialResponse = service.spreadsheets().values()
            .get(id, financialRange)
            .execute()

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
        val studentEntries = studentResponse.getValues().filter { e ->
            e.isNotEmpty() && e[0] != "No (You don't need to answer and may skip to the next page)"
        }
        val facultyEntries = facultyResponse.getValues().filter { e ->
            e.isNotEmpty() && e[0] != "No (You don't need to answer and may skip to the next page)"
        }
        val financialEntries = financialResponse.getValues()

        val test = HashMap<Any, Int>()
        for(submission in generalEntries) {
            var count = test.getOrDefault(submission[0], 0)
            test[submission[0]] = ++count
        }

        val plotsz = mutableListOf<Plot>()
        for (key in generalMap.keys) {
            val title = key
            val plot = lets_plot(generalMap[key]) + geom_bar() + ggtitle(title)
            plotsz.add(plot)
            //println(plot.toSpec())
            //plotsz = plotsz.plus(lets_plot(generalMap[key]) + geom_bar {

//            }  + ggtitle(title))
            if (title.length > 100) {
                plotsz[plotsz.lastIndex] = plotsz.last() + ggsize(9 * title.length, 300)
            }
        }

        val h = 500
        val w = 500
        val bunch = GGBunch()
        for ((count, plot) in plotsz.withIndex()) {
            bunch.addPlot(plot,(count % 3) * w, (count / 3) * h)
        }

        val html = PlotHtmlExport.buildHtmlFromRawSpecs(bunch.toSpec(), iFrame = false)
        File("htmltest.html").writeText(html)

        val iframe = PlotHtmlExport.buildHtmlFromRawSpecs(bunch.toSpec(), iFrame = true)
        File("iframetest.html").writeText(iframe)

        val svg = PlotSvgExport.buildSvgImageFromRawSpecs(bunch.toSpec(), null)
        File("svgtest.svg").writeText(svg)


    }
}