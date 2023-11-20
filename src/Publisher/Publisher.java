/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Publisher;

import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Publisher {
    String publisherId; //format Pxxxxx
    String publisherName; 
    String phone; //format 9 - 12 digits

    public Publisher(String publisherId, String publisherName, String phone) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.phone = phone;
    }

    //finding
    public Publisher(String publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public String toString() {
        return String.format("|%-10s|%-30s|%-15s|", publisherId, publisherName, phone);
    }

    @Override
    public boolean equals(Object obj) {
        Publisher p = (Publisher) obj;
        return p.publisherId.equalsIgnoreCase(publisherId);
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
