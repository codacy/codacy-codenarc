package codacy.codenarc.docs

import scala.xml.{Elem, SAXParser}
import scala.xml.factory.XMLLoader

object XML extends XMLLoader[Elem] {
  override def parser: SAXParser = {
    val f = new com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl()
    f.setNamespaceAware(false)
    f.setValidating(false)
    f.setFeature("http://xml.org/sax/features/namespaces", false)
    f.setFeature("http://xml.org/sax/features/validation", false)
    f.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false)
    f.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
    f.newSAXParser()
  }
}
