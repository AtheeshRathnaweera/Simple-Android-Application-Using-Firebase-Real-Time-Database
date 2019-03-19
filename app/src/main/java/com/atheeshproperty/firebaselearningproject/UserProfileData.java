package com.atheeshproperty.firebaselearningproject;

public class UserProfileData {
    String FName;
    String LName;
    String UAge;
    String UPhone;

    public UserProfileData(){


    }

    public UserProfileData(String first, String last, String age, String phone){
        this.FName=first;
        this.LName = last;
        this.UAge = age;
        this.UPhone = phone;
    }



}
