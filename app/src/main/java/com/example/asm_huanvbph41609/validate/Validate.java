package com.example.asm_huanvbph41609.validate;

public class Validate {
    public static boolean validate(String id, String name, String room){
        if(name.trim().equals("")) return false;
        if(id.trim().equals("")) return false;
        if(room.trim().equals("")) return false;
        return true;
    }
}
