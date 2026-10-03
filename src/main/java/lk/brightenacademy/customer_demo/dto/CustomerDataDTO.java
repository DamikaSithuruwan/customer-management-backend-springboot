package lk.brightenacademy.customer_demo.dto;

public class CustomerDataDTO {
    private Integer id;
    private String name;
    private String mobile;
    private String nic;
    private String city;

    public CustomerDataDTO(){

    }

    public CustomerDataDTO(Integer id,String name,String mobile, String nic, String city){
        this.id=id;
        this.name=name;
        this.mobile=mobile;
        this.nic=nic;
        this.city=city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
