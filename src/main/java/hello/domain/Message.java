package hello.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Message{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    private String text;

    @Column
    private String tag;

    @Column
    private String author;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Message(Integer id) {
        this.id = id;
    }

    public Message() {}

    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }

    public Message(String text, String tag, String author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return text;
    }

    public void setMessage(String message) {
        this.text = message;
    }
}
