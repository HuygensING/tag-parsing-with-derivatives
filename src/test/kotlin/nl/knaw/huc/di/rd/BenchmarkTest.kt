package nl.knaw.huc.di.rd

import nl.knaw.huc.di.rd.tag.tagml.derivation.WellFormedness
import nl.knaw.huc.di.rd.tag.tagml.tokenizer.LSPToken
import nl.knaw.huc.di.rd.tag.tagml.tokenizer.TAGMLTokenizer
import nl.knaw.huc.di.rd.tag.util.showErrorLocation
import org.assertj.core.api.Assertions
import org.junit.Test
import kotlin.test.fail

class BenchmarkTest {

    @Test
    fun parseSmallTAGML() {
        parseTAGMLFile("small.tagml")
    }

    @Test(timeout = 10_000)
    fun parse() {
        val tagml = "[a>" +
                "[b>text<b]".repeat(3) +
                "<a]"
        assertTAGMLisWellFormed(tagml)
    }

    @Test(timeout = 10_000)
    fun parseMediumTAGML() {
        parseTAGMLFile("medium.tagml")
    }

    //    @Ignore
    @Test(timeout = 10_000)
    fun parseLargeTAGML() {
        parseTAGMLFile("large.tagml")
    }

    private fun parseTAGMLFile(s: String) {
        val tagml = this::class.java.getResource(s).readText(Charsets.UTF_8)
        assertTAGMLisWellFormed(tagml)
    }

    private fun assertTAGMLisWellFormed(tagml: String) {
        mapTokenizedTAGML(tagml) { Assertions.assertThat(WellFormedness.checkWellFormedness(it).isWellFormed).isTrue() }
    }

    private fun mapTokenizedTAGML(tagml: String, funk: (tokens: List<LSPToken>) -> Unit) =
            TAGMLTokenizer.tokenize(tagml).fold(
                    { reject ->
                        showErrorLocation(tagml, reject)
                        fail("Parsing failed: $reject")
                    },
                    { tokens -> funk(tokens) }
            )

}