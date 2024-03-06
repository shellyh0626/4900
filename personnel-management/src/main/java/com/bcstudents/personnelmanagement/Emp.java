package main.java;

public class Emp {
    private Integer SSN;
    private String email;
    private String education;
    private Integer age;
    private String gender;
    private String address;
    private String name;
    private Integer employeeID;

    public Emp() {
        
    }
    public Emp(Integer SSN, String email, String education, Integer age, String gender, String address, String name, Integer employeeID){
        this.SSN = SSN;
        this.email = email;
        this.education = education;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.name = name;
        this.employeeID = employeeID;
    }

    public Integer getSSN(){
        return SSN;
    }

    public void  setSSN(Integer SSN) {
        this.SSN = SSN;
    }

    public String getEmail(){
        return email;
    }

    public void  setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public String toString() {
        return "Emp{" + "SSN = " + SSN + 
        ", email = " + email + '\'' + 
        ", education = " + education + 
        ", age = " + age + '\'' +
        ", gender = " + gender +
        ", address = " + address + '\'' +
        ", name = " + name + 
        ", employeeID = " + employeeID + '}';
    }
}