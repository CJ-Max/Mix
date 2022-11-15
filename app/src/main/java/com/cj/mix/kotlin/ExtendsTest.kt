package com.cj.mix.kotlin

import android.content.Context
import android.util.AttributeSet
import android.view.View

class ExtendsTest {
}

class MyViewWithoutConstructor : View {
    constructor(cxt: Context) : super(cxt)
    constructor(cxt: Context, attrs: AttributeSet) : super(cxt, attrs)
    constructor(cxt: Context, attrs: AttributeSet?, defStyle: Int) : super(cxt, attrs, defStyle)
}

class MyViewWithConstructor(cxt: Context, attrs: AttributeSet?, defStyle: Int) :
    View(cxt, attrs, defStyle) {

}
