package com.example.testing_book_library_project.Domain;

public class Books {

    private int id, pages;
    private String name, author, imageUrl,short_Des, long_Des;
    private Boolean isExpanded;

    public Books(int id, int pages, String name, String author, String imageurl, String short_Des, String long_Des) {
        this.id = id;
        this.pages = pages;
        this.name = name;
        this.author = author;
        this.imageUrl = imageurl;
        this.short_Des = short_Des;
        this.long_Des = long_Des;
        isExpanded = false;

    }

    public Boolean getExpanded() {
        return isExpanded;
    }

    public void setExpanded(Boolean expanded) {
        isExpanded = expanded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShort_Des() {
        return short_Des;
    }

    public void setShort_Des(String short_Des) {
        this.short_Des = short_Des;
    }

    public String getLong_Des() {
        return long_Des;
    }

    public void setLong_Des(String long_Des) {
        this.long_Des = long_Des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Books{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", imageurl='" + imageUrl + '\'' +
                ", short_Des='" + short_Des + '\'' +
                ", long_Des='" + long_Des + '\'' +
                ", id=" + id +
                ", pages=" + pages +
                '}';
    }
}
