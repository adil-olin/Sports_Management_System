package COACH;

public class Coach {
    private String Name;
    private String EmailId;
    private int Id;

    public void setName(String name) {
        Name = name;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public void setId(int id) {
        Id = id;
    }
    public String getName()
    {
        return this.Name;
    }
    public String getEmailId()
    {
        return this.EmailId;
    }
    public int getId()
    {
        return Id;
    }

}
