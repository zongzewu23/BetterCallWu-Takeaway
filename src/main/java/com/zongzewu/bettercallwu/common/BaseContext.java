package com.zongzewu.bettercallwu.common;

/**
 * A threadLocal encapsulation tool class used to save and obtain the ID of the currently logged in user.
 */
public class BaseContext {
        private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * set value
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * obtain value
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
