package ru.vk.competition.minchecker.utils;

public class SystemVariables {

    private String host_name = System.getenv("rs.endpoint");

    public SystemVariables() {

    }

    public String apiRoot() {        
        return host_name + "/api/";
    }
}
