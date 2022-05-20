package com.ckinfe_processor

import com.cknife_annotation.BindView
import com.google.auto.service.AutoService
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

@AutoService
class CKnifeProcessor : AbstractProcessor() {
    private var messager: Messager? = null
    private var filer: Filer ?= null

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        messager = processingEnv.messager
        filer = processingEnv.filer
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun getSupportedAnnotationTypes(): kotlin.collections.MutableSet<String> {
        return HashSet<String>().apply {
            add(BindView::class.java.canonicalName)
        }
    }

    override fun process(
        p0: kotlin.collections.MutableSet<out TypeElement>?,
        p1: RoundEnvironment?
    ): Boolean {
        TODO("Not yet implemented")
    }
}