package com.atmecs.testing.extentreport;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

public class ThreadPool {
	 private static ThreadPool thisPool = new ThreadPool();

	 private static HashMap<String, ThreadLocal<WebDriver>> localMap = new HashMap<String, ThreadLocal<WebDriver>>();

	 public final ThreadLocal<WebDriver> userThreadLocal = new ThreadLocal<WebDriver>();

	 public static void clear() {
	 thisPool.userThreadLocal.remove();
	 }

	 public static WebDriver get() {
	 return thisPool.userThreadLocal.get();
	 }

	 public static WebDriver getDriverInfo() {
	 final String key = Thread.currentThread().getName();
	 final ThreadLocal<WebDriver> userThreadLocal = ThreadPool.localMap.get(key);
	 if (userThreadLocal == null) {
	 return null;
	 }

	 return userThreadLocal.get();
	 }

	 public static void set(WebDriver dInfo) {

	 thisPool.userThreadLocal.set(dInfo);
	 }

	 public static void setDriverInfo(WebDriver dInfo) {
	 final String key = Thread.currentThread().getName();
	 // System.out.println("currentThread name is:"+key);
	 ThreadLocal<WebDriver> userThreadLocal = ThreadPool.localMap.get(key);
	 if (userThreadLocal == null) {
	 userThreadLocal = new ThreadLocal<WebDriver>();
	 }
	 userThreadLocal.set(dInfo);
	 ThreadPool.localMap.put(key, userThreadLocal);
	 }
}
