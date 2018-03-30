package com.example.hp2.driver;

/**
 * Created by hp 2 on 09-10-2017.
 */



import java.io.Serializable;

/**
 * Created by ishantkumar on 19/07/17.
 */

// Bean or Model or POJO

public class User implements Serializable {


    String name;
    String email;
    String  password;
    String reenter;
    String address;
    String gender;
    String phone_no;
    String city;
    String zip_code;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;


    public User(){

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReenter() {
        return reenter;
    }

    public void setReenter(String reenter) {
        this.reenter = reenter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    @Override
    public String toString() {
        return "User{" +

                " name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", reenter='" + reenter + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", phone_no=" + phone_no +
                ", city='" + city + '\'' +
                ", zip_code=" + zip_code +
                '}';
    }
}