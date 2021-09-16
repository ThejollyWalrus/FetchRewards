package com.example.fetchrewards;

public class UserInfo {

    String id;
    String listId;
    String name;

    public UserInfo(){}

    public UserInfo (String Id, String ListId, String Name){
        id = Id;
        listId = ListId;
        name = Name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
