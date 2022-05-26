package com.ckinfe_processor

import com.cknife_annotation.BindView
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import java.util.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

@AutoService(Processor::class)
class CKnifeProcessor : AbstractProcessor() {

    private var messager: Messager? = null
    private var filer: Filer? = null
    private var elementUtils: Elements? = null

    companion object {
        //        val kotlinGenDirName = "kapt.kotlin.generated"
        val VIEW_BINDING = "_ViewBinding"
    }

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        messager = processingEnv?.messager
        filer = processingEnv?.filer
        elementUtils = processingEnv?.elementUtils
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return Collections.singleton(BindView::class.java.canonicalName)
    }

    override fun process(
        annotations: MutableSet<out TypeElement>,
        roundEnv: RoundEnvironment
    ): Boolean {
        messager?.printMessage(Diagnostic.Kind.NOTE, "cjj process start\n")

        var elements = roundEnv.getElementsAnnotatedWith(BindView::class.java)
        for (element in elements) {
            messager?.printMessage(Diagnostic.Kind.NOTE, "cjj process ${element.simpleName}\n")

            var pkgName = elementUtils?.getPackageOf(element)?.qualifiedName.toString()
            var activitySimpleName = "${(element.enclosingElement as TypeElement).simpleName}"
            var classSimpleName = "$activitySimpleName$VIEW_BINDING"

            var resId = element.getAnnotation(BindView::class.java).value
            messager?.printMessage(Diagnostic.Kind.NOTE, "cjj process functionSpec\n")

            var functionSpec =
                FunSpec.builder("bind").addModifiers(KModifier.OVERRIDE)
                    .addParameter("activity", Class.forName("android.app.Activity").kotlin)
                    .addStatement("(activity as $activitySimpleName).${element.simpleName} = activity.findViewById($resId)")
                    .build()

            messager?.printMessage(Diagnostic.Kind.NOTE, "cjj functionSpec end\n")
            // 查找 IBindView 接口
            var bindViewClass = ClassName.bestGuess("com.cj.mix.util.IBindView")

            var typeSpec = TypeSpec.classBuilder(classSimpleName)
                .addModifiers(KModifier.FINAL, KModifier.PUBLIC)
                .addSuperinterface(bindViewClass)
                .addFunction(functionSpec)
                .build()

            messager?.printMessage(Diagnostic.Kind.NOTE, "cjj typeSpec start")
            FileSpec.builder(pkgName, classSimpleName).addType(typeSpec).build().writeTo(filer!!)
        }
        return true
    }
}