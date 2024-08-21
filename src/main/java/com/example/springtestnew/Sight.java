package com.example.springtestnew;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonPropertyOrder({ "sightName", "zone", "category","photoURL","description","address" })
//@Document(collation = "sights")
public class Sight implements java.io.Serializable{
    @Id
    private String id;
    private String sightName=null;
    private String zone=null;
    private String category=null;
    private String photoURL=null;
    private String description=null;
    private String address=null;
    public Sight(){
    }

    public String getSightName() {
        return sightName;
    }

    public void setSightName(String sightName) {
        this.sightName = sightName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString(){
        String output = String.format("SightName: "+getSightName()+"\n");
        output=String.format(output+"Zone: "+getZone()+"\n");
        output=String.format(output+"Category: "+getCategory()+"\n");
        output=String.format(output+"PhotoURL: "+getPhotoURL()+"\n");
        output=String.format(output+"Description: "+getDescription()+"\n");
        output=String.format(output+"Address: "+getAddress()+"\n\n\n");
        return output;
    }
}
