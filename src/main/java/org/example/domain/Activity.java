package org.example.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;
    private String tag;
    private boolean activeAct;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<User> users =new ArrayList<>();


    public Activity() {
    }


    public boolean isActiveAct() {
        return activeAct;
    }

    public void setActiveAct(boolean activeAct) {
        this.activeAct = activeAct;
    }


    public Activity(String text, String tag, List <User> users) {
        this.text = text;
        this.tag = tag;
        this.users=users;
        this.activeAct=false;
    }


    public String getUsernames(){

        if (users !=null){
            StringBuilder sb=new StringBuilder();
            for (User user:users){
                sb.append(user.getUsername()).append(" ");
            }
          return   sb.toString();
        }
        return "none";

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
