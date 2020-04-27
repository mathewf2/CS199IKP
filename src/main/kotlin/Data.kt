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
import java.io.InputStream
import java.io.InputStreamReader

class Data {
    var app_name = "CS 199 IKP"
    var id = "1JWxwV81A2hFBWK0eO30wFkGmuEtHxDImCKGx9KLdlZA"

    fun authorize(): Credential {
        val input: InputStream = this.javaClass.getResourceAsStream("/credentials.json")
        val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(
            JacksonFactory.getDefaultInstance(), InputStreamReader(input)
        )

        val scopes = listOf(SheetsScopes.SPREADSHEETS)

        val flow = GoogleAuthorizationCodeFlow.Builder(
            GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
            clientSecrets, scopes
        )
            .setDataStoreFactory(FileDataStoreFactory(java.io.File("tokens")))
            .setAccessType("offline")
            .build()
        return AuthorizationCodeInstalledApp(
            flow, LocalServerReceiver()
        ).authorize("user")
    }

    fun getSheetsService(): Sheets {
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
        val generalRange = "B2:I"
        val studentRange = "J2:R"
        val facultyRange = "S2:V"
        val financialRange = "W2:Z"

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
        val studentEntries = studentResponse.getValues().filter { e ->
            e.isNotEmpty() && e[0] != "No (You don't need to answer and may skip to the next page)"
        }
        val facultyEntries = facultyResponse.getValues().filter { e ->
            e.isNotEmpty() && e[0] != "No (You don't need to answer and may skip to the next page)"
        }
        val financialEntries = financialResponse.getValues()

        println(generalEntries)
        println(studentEntries)
        println(facultyEntries)
        println(financialEntries)

    }
}