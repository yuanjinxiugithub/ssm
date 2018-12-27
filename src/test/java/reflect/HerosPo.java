package reflect;

public class HerosPo {
    private String name;//姓名
    private String type;//类型
    private int status;// 状态 0--已用  1--禁用
    
    public HerosPo() {
        super();
    }
    public HerosPo(String name, String type, int status) {
        super();
        this.name = name;
        this.type = type;
        this.status = status;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "HerosPo [name=" + name + ", type=" + type + ", status=" + status + "]";
    }
}