package com.example.xiaoxiong.test;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectTest {

    private static final String TAG = "wangyuchao";

    //@Before("execution(* android.support.v7.app.AppCompatActivity.on**(..))")

//    @Before("execution(* com.example.xiaoxiong.test.MainActivity.on*(android.os.Bundle))")
//    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
//        String key = joinPoint.getSignature().toString();
//        Log.d(TAG, "onActivityMethodBefore: " + key);
//    }
//
//    @After("execution(* com.example.xiaoxiong.test.MainActivity.on*(android.os.Bundle))")
//    public void onActivityMethodAfter(JoinPoint joinPoint) throws Throwable {
//        String key = joinPoint.getSignature().toString();
//        Log.d(TAG, "onActivityMethodAfter: " + key);
//    }

//    @Around("execution(* com.example.xiaoxiong.test.MainActivity.testAOP())")
//    public void onActivityMethodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        String key = proceedingJoinPoint.getSignature().toString();
//        String key1 = proceedingJoinPoint.getKind().toString();
//        String key2 = proceedingJoinPoint.toLongString();
//        String key3 = proceedingJoinPoint.getArgs().toString();
//        String key4 = proceedingJoinPoint.getSourceLocation().toString();
//        String key5 = proceedingJoinPoint.getStaticPart().toString();
//        String key6 = proceedingJoinPoint.getTarget().toString();
//        String key7 = proceedingJoinPoint.getThis().toString();
//        String key8 = proceedingJoinPoint.getClass().toString();
//        Log.d(TAG, "onActivityMethodAroundFirst: " + key);//void com.example.xiaoxiong.test.MainActivity.testAOP()
//        Log.d(TAG, "onActivityMethodAroundFirst: " + key1);
//        Log.d(TAG, "onActivityMethodAroundFirst: " + key2);
//        Log.d(TAG, "onActivityMethodAroundFirst: " + key3);
//        Log.d(TAG, "onActivityMethodAroundFirst: " + key4);
//        Log.d(TAG, "onActivityMethodAroundFirst: " + key5);
//        Log.d(TAG, "onActivityMethodAroundFirst: " + key6);
//        Log.d(TAG, "onActivityMethodAroundFirst: " + key7);
//        Log.d(TAG, "onActivityMethodAroundFirst: " + key8);
//        proceedingJoinPoint.proceed();
//        Log.d(TAG, "onActivityMethodAroundSecond: " + key);
//    }


    @Pointcut("execution(@com.example.xiaoxiong.test.DebugTool * *(..))")
    public void DebugToolMethod() {
    }

    @Before("DebugToolMethod()")
    public void onDebugToolMethodBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.d(TAG, "onDebugToolMethodBefore: " + key);
    }

}