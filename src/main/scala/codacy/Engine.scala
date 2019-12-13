package codacy

import codacy.codenarc.CodeNarc
import com.codacy.tools.scala.seed.DockerEngine

object Engine extends DockerEngine(CodeNarc)()
