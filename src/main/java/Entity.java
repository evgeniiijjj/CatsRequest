public class Entity {

    public String id;
    public String text;
    public String type;
    public String user;
    public Integer upvotes;

    public String toString() {
        return "{id: " + id + ", "
                + "text: " + text + ", "
                + "type: " + type + ", "
                + "user: " + user + ", "
                + "upvotes: " + upvotes + "}";
    }
}
