package com.atheeshproperty.firebaselearningproject;

public class UserProfileData {
   private String FirstName;
    private String LName;
    private String UAge;
    private String UPhone;

    public UserProfileData(){


    }

    public UserProfileData(String first, String last, String age, String phone){
        this.FirstName=first;
        this.LName = last;
        this.UAge = age;
        this.UPhone = phone;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getUAge() {
        return UAge;
    }

    public void setUAge(String UAge) {
        this.UAge = UAge;
    }

    public String getUPhone() {
        return UPhone;
    }

    public void setUPhone(String UPhone) {
        this.UPhone = UPhone;
    }
}
