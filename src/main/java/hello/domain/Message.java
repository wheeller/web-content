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

//    @Column
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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

    public Message(String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.author = user;
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

    public String getAuthorName (){
        return author != null ? author.getUsername() : "<none>";
    }
}
