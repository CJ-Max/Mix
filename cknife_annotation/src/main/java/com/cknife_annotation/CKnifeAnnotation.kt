package com.cknife_annotation

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class BindView {
    var value: Int
}