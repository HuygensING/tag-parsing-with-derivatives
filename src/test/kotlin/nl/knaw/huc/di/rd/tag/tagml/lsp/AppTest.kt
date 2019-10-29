/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package nl.knaw.huc.di.rd.tag.tagml.lsp

import org.eclipse.lsp4j.launch.LSPLauncher
import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest {
    @Test
    fun testAppHasAGreeting() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }

    @Test
    fun testLSP4J() {
        val server = TAGMLLanguageServer()
        val inputStream = System.`in`
        val outputStream = System.out
        val launcher = LSPLauncher.createServerLauncher(server, inputStream, outputStream)
        launcher.startListening()

    }
}
