package com.example.juniordeveloper.model;

public class ModelDaftar {
    String Nama, Email, Username, Password;

    public ModelDaftar(String nama, String email, String username, String password){
     this.Nama = nama;
     this.Email = email;
     this.Password = password;
     this.Username = username;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
