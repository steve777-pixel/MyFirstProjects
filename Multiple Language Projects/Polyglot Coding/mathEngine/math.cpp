#include <jni.h>
#include <iostream>
#include "core_algorithms.h"

// The name must exactly match: Java _ package _ class _ method
extern "C"{

    JNIEXPORT jdouble JNICALL Java_app_Index_getPopulationVariance(JNIEnv *env,jclass clazz, jdoubleArray javaArray){

        jsize length = env->GetArrayLength(javaArray);

        jdouble* rawElements = env->GetDoubleArrayElements(javaArray, nullptr);

        std::vector<double> cppVector(rawElements, rawElements + length);

        env->ReleaseDoubleArrayElements(javaArray, rawElements, JNI_ABORT);

        return calculatePopulationVariance(cppVector);
    }

    JNIEXPORT jdouble JNICALL Java_app_Index_getPopulationSD(JNIEnv *env,jclass clazz, jdoubleArray javaArray){

        jsize length = env->GetArrayLength(javaArray);
        jdouble* rawElements = env->GetDoubleArrayElements(javaArray, nullptr);

        std::vector<double> cppVector(rawElements, rawElements + length);
        env->ReleaseDoubleArrayElements(javaArray, rawElements, JNI_ABORT);

        return calculatePopulationSD(cppVector);
    }
}
