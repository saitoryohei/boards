package bean;

import java.io.Serializable;

public class Board implements Serializable {
    private int id;
    private String date;
    private String login_id;
    private String contents;

    // ゲッターとセッター
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getLogin_id() { return login_id; }
    public void setLogin_id(String login_id) { this.login_id = login_id; }

    public String getContents() { return contents; }
    public void setContents(String contents) { this.contents = contents; }
}