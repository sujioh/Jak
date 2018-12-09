package com.example.osujeong.board3;
//데이터들 셋터 겟터  한줄의 데이터를 만드므니다.

//Beans - TO(Transfer object) 트랜스퍼 오브젝트/VO (velue object)벨류 오브젝ㅌ츠
public class BoardTO {
    private String _id;
    private String subject;
    private String writer;

    private String password;
    private String content;
    private String hit;
    private String wdate;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getWdate() {
        return wdate;
    }

    public void setWdate(String wdate) {
        this.wdate = wdate;
    }
}