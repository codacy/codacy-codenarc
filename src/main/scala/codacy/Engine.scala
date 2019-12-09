package codacy

import codacy.codenarc.CodeNarcTemp
import com.codacy.tools.scala.seed.DockerEngine

object Engine extends DockerEngine(CodeNarcTemp)()
