package com.manning.junitbook.ch12.htmlunit;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;

/**
 * Tests navigating the Sun Javadoc 6 site.
 * 
 * @author <a href="mailto:ggregory@apache.org">Gary Gregory</a>
 * @version $Id: JavadocPageTest.java 410 2009-05-20 21:37:55Z garydgregory $
 */
public class JavadocPageTest extends ManagedWebClient {

    @Test
    public void testClassNav() throws IOException {
        HtmlPage mainPage = (HtmlPage) this.webClient.getPage("http://docs.oracle.com/javase/8/docs/api/");
        HtmlPage packagePage = (HtmlPage) mainPage.getFrameByName("packageFrame").getEnclosedPage();
        HtmlPage bVerPage = packagePage.getAnchorByHref("javax/lang/model/util/AbstractAnnotationValueVisitor8.html").click();
        HtmlParagraph p = (HtmlParagraph) bVerPage.getElementsByTagName("p").item(0);
        Assert.assertTrue("Unexpected text", p.asText().startsWith(
                "WARNING: The AnnotationValueVisitor interface implemented by this class may have methods added to it in the future to accommodate new, currently unknown, language structures added to future versions of the Java™ programming language. Therefore, methods whose names begin with \"visit\" may be added to this class in the future; to avoid incompatibilities, classes which extend this class should not declare any instance methods with names"));
    }
}
