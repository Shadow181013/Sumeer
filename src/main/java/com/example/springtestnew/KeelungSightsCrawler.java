package com.example.springtestnew;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class KeelungSightsCrawler {
    ArrayList<Sight> sights = new ArrayList<Sight>();

    public Sight[] getItems() throws IOException {

        try {
            Document doc = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/")
                    .timeout(30000)
                    .get(); //爬第一層網址
            Elements SightNames = doc.select("div.box a"); //獲得每個地標

            //逐個執行去確認是否包含要搜索的名字
            for (Element sightname : SightNames) {
                if (sightname != null) {
                    Document secondDoc = Jsoup.connect(sightname.absUrl("href"))
                            .timeout(15000)
                            .get(); //爬第二層的網址
                    Sight sight = new Sight();
                    Element name = secondDoc.selectFirst("[property=dc:title]");
                    if (name != null) {
                        sight.setSightName(name.text());
                    }
                    Element Zone = secondDoc.selectFirst("li.bc_last a");
                    if (Zone != null) {
                        sight.setZone(Zone.text());
                    }
                    Element Category = secondDoc.selectFirst("[property=rdfs:label]");
                    if (Category != null) {
                        sight.setCategory(Category.text());
                    }
                    Element PhotoURL = secondDoc.selectFirst("div.gpic img.lazyload");
                    if (PhotoURL != null) {
                        sight.setPhotoURL(PhotoURL.absUrl("data-src"));
                    }
                    Element Description = secondDoc.selectFirst("div.text");
                    if (Description != null) {
                        Description.select(".author").remove();
                        sight.setDescription(Description.text());
                    }
                    Element Address = secondDoc.selectFirst("[property=vcard:street-address]");
                    if (Address != null) {
                        sight.setAddress(Address.text());
                    }
                    sights.add(sight);
                }
            }
            Sight[] ArraySights = new Sight[sights.size()];
            ArraySights = sights.toArray(ArraySights);
            return ArraySights;
        } catch (IOException e) {
            System.err.println("爬取資料時發生錯誤：" + e.getMessage());
            throw e;
        }
    }
}

