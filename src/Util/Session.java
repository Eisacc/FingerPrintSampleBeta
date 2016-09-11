/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public final class Session {

    /**
     * session
     */
    private static Session session;
    /**
     * mapObjectSession
     */
    private static Map<String, Object> mapObjectSession;

    /**
     * constructor privado
     */
    private Session() {
    }

    /**
     * createInstance
     *
     * @return
     */
    public static Session createInstanceSession() {
        if (!UtilFunctions.isNotNull(session)) {
            session = new Session();
            mapObjectSession = new HashMap<String, Object>();
        }
        return session;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void addObjectSession(String key, Object value) {
        if (UtilFunctions.isNotNullOrEmptyString(key)
                && UtilFunctions.isNotNull(value)) {
            mapObjectSession.put(key, value);
        }
    }

    /**
     *
     * @param key
     */
    public void removeObjectSession(String key) {
        if (UtilFunctions.isNotNullOrEmptyString(key)) {
            mapObjectSession.remove(key);
        }

    }

    public Object getObjectSession(String key) {
        return mapObjectSession.get(key);
    }

    /**
     * destroySession
     */
    public static void destroySession() {
        session = null;
        mapObjectSession = null;
    }

}
