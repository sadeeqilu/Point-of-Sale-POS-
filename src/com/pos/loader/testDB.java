/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.loader;

import com.pos.business.User;
import com.pos.db.UserDB;

/**
 *
 * @author ASIYA
 */
public class testDB {
    public static void main(String[] v){
        UserDB udb = new UserDB();
        User u = udb.getUser("bash.salisu", "pass");
        System.out.println(u.getRole());
    }
}
