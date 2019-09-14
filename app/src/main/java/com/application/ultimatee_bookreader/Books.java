package com.application.ultimatee_bookreader;

class Books {
    String bookname;
    String author;
    String details;
    String downloadURL;
    String bookType;


    public Books(String bookname, String author, String details, String downloadURL, String bookType) {
        this.bookname = bookname;
        this.author = author;
        this.details = details;
        this.downloadURL = downloadURL;
        this.bookType = bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookType() {
        return bookType;
    }
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
}


