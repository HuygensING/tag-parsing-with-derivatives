package nl.knaw.huc.di.rd.tag.tagml.tokenizer

import arrow.core.Either
import lambdada.parsec.io.Reader
import nl.knaw.huc.di.rd.tag.tagml.tokenizer.TAGMLTokenizer.tagmlParser
import nl.knaw.huc.di.rd.tag.tagml.tokenizer.TAGMLTokenizer.tokenize
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Test
import java.net.URL

class TAGMLTokenizerTest() {

    @Test
    fun test() {
        val tagml = "[!schema http://example.org/schema.yaml]\n[hello>World!<hello]"
        val tagmlReader = Reader.string(tagml)
        val response = tagmlParser(tagmlReader)
        print(response)
    }

    @Test
    fun tokenizeTest1() {
        val tagml = "[hello>World!<hello]"

        val startTag = StartTagToken("hello")
        val text = TextToken("World!")
        val endTag = EndTagToken("hello")
        val expectedTokens = listOf(startTag, text, endTag)

        assertTokenizingSucceeds(tagml, expectedTokens)
    }

    @Test
    fun tokenizeTest2() {
        val tagml = "[!schema http://example.org/schema.yaml][hello>World!<hello]"

        val schemaLocationToken = SchemaLocationToken(URL("http://example.org/schema.yaml"))
        val startTag = StartTagToken("hello")
        val text = TextToken("World!")
        val endTag = EndTagToken("hello")
        val expectedTokens = listOf(schemaLocationToken, startTag, text, endTag)

        assertTokenizingSucceeds(tagml, expectedTokens)
    }

    private fun assertTokenizingSucceeds(tagml: String, expectedTokens: List<TAGMLToken>) {
        when (val result = tokenize(tagml).also { print(it) }) {
            is Either.Left -> fail("Parsing failed: ${result.a}")
            is Either.Right -> assertThat(result.b.toString()).isEqualTo(expectedTokens.toString())
        }
    }
}