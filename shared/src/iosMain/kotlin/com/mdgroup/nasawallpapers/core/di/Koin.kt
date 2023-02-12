package com.mdgroup.nasawallpapers.core.di

import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.ObjCProtocol
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier

// https://github.com/touchlab/KaMPKit/issues/166
fun Koin.get(clazz: ObjCClass): Any {
    val kClazz = getOriginalKotlinClass(clazz)!!
    return get(kClazz, null, null)
}

// Для получения бина через интерфейс
fun Koin.get(protocol: ObjCProtocol): Any {
    val kClazz = getOriginalKotlinClass(protocol)!!
    return get(kClazz, null, null)
}

fun Koin.get(clazz: ObjCClass, qualifier: Qualifier): Any {
    val kClazz = getOriginalKotlinClass(clazz)!!
    return get(kClazz, qualifier, null)
}

fun Koin.get(clazz: ObjCClass, parameter: Any): Any {
    val kClazz = getOriginalKotlinClass(clazz)!!
    return get(kClazz, null) { parametersOf(parameter) }
}

fun Koin.get(clazz: ObjCClass, qualifier: Qualifier, parameter: Any): Any {
    val kClazz = getOriginalKotlinClass(clazz)!!
    return get(kClazz, qualifier) { parametersOf(parameter) }
}
