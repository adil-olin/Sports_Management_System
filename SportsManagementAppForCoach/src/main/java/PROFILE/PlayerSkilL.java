package PROFILE;

public class PlayerSkilL {
    private String skillName;
    private int value;
    private int skillValueType;
    public PlayerSkilL(String skillName, int value) {
        this.skillName = skillName;
        this.value = value;
    }
    public PlayerSkilL()
    {
        this.skillName = null;
        this.value = 0;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSkillValueType() {
        return skillValueType;
    }

    public void setSkillValueType(int skillValueType) {
        this.skillValueType = skillValueType;
    }
}
