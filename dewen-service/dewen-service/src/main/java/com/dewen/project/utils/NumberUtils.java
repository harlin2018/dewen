package com.dewen.project.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 数字操作工具类
 * 
 * @author harlin
 *
 */
public class NumberUtils {
	
	/**
	 * 获取一个UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 获取一个六位随机数（短信验证码）
	 * 
	 * @return
	 */
	public static String getRandomNo() {
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
		    int index = rand.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 6; i++)
		    result = result * 10 + array[i];
		return String.valueOf(result);
	}

	/**
	 * 获取一个三位随机数（短信验证码）
	 *
	 * @return
	 */
	public static String getRandomNoThree() {
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 3; i++)
			result = result * 10 + array[i];
		return String.valueOf(result);
	}
	
}
