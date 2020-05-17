package cn.nurasoft.bkhatfield.TemperatureAlpha;

public class TemperatureStructure {

    private String name;
    private String group;
    private String max;
    private String min;

  public TemperatureStructure(String name, String group, String max, String min) {
        this.name = name;
        this.group = group;
        this.max = max;
        this.min = min;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }
}
