package com.example.covid_19_emergency_app.model;

public class helper_user
{
    private String full_name,mobile_no,Age,token;

    public helper_user(){

    }

    public helper_user(String full_name, String mobile_no, String age) {
        this.full_name = full_name;
        this.mobile_no = mobile_no;
        Age = age;

    }

    public helper_user(String full_name, String mobile_no, String age, String token) {
        this.full_name = full_name;
        this.mobile_no = mobile_no;
        Age = age;
        this.token = token;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public String getAge() {
        return Age;
    }

    public String getToken() {
        return token;
    }

    /*
            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }

            public String getMobile_no() {
                return mobile_no;
            }

            public void setMobile_no(String mobile_no) {
                this.mobile_no = mobile_no;
            }

            public String getEmail() {
                return Date_of_Birth;
            }

          //  public void setEmail(String email) {
            //    Date_of_Birth = dob;
            //}

            public String getCollege() {
                return Categories;
            }

           // public void setCollege(String college) {
            //    Categories = categories;
            //}
        */
    @Override
    public String toString() {
        return "helper_user{" +
                "full_name='" + full_name + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", Date_of_Birth='" + Age + '\'' +
                '}';
    }
}
