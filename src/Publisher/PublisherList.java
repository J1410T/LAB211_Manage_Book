/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Publisher;

import Service.InputService;
import Publisher.Publisher;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author Asus
 */
public class PublisherList extends ArrayList<Publisher> {

//    private ArrayList<Publisher> pubList = new ArrayList<Publisher>();
//
//    public PublisherList(ArrayList<Publisher> pubList) {
//        this.pubList = pubList;
//    }
    public PublisherList() {
        this.readFile();
    }

    String fName = "src\\Data\\Publisher.dat";

    public void addPublisher() {
        //Nhap thong tin
        String publisherId;
        do {
            publisherId = InputService.inputPattern("Enter Publisher ID: ", "[Pp][0-9]{5}", "Pxxxxx").toUpperCase();
            if (searchPublisher(publisherId) != null) {
                System.out.println("Publisher " + publisherId + " existed!!!");
            }
        } while (searchPublisher(publisherId) != null);
        //Nhap va check su ton tai
        String publisherName = InputService.inputPattern("Enter Publisher's name: ", "[a-z A-Z]{5,30}", "Name have from 5 to 30 characters!").toUpperCase(Locale.ITALY);
        String phone = InputService.inputPattern("Enter Publisher's phone: ", "[0-9]{10,12}", "Number have from 10 to 12 digits");

        //Add vao List
        Publisher newPub = new Publisher(publisherId, publisherName, phone);
        this.add(newPub);
        System.out.println("Add Publisher Success!!");
        System.out.println("");
    }

    public void addPublisher(Publisher publisher) {
        this.add(publisher);
    }

    public Publisher searchPublisher(String ID) {
        for (Publisher pub : this) {
            if (pub.publisherId.equalsIgnoreCase(ID)) {
                return pub;
            }
        }
        return null;
    }

    public void deletePublisher() {
        //B1: Tim duoc cai cho xoa
        //B2: Xoa
        System.out.print("Enter Publisher's ID: ");
        String publisherId = InputService.SC.nextLine().trim().toUpperCase();
        if (searchPublisher(publisherId) == null) {
            System.out.println("Publisher " + publisherId + " does not existed!!");
            System.out.println("REMOVE FAILED!!!");
            System.out.println("");
        } else {
            this.remove(searchPublisher(publisherId));
            System.out.println("REMOVE SUCCESS!!!");
            System.out.println("");
        }

    }

    public void readFile() {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File is not existed!!");
            System.exit(0);
        }
        try {
            FileReader fr = new FileReader(fName);
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String element[] = line.split("[|]");
                String publisherId = element[1].trim().toUpperCase();
                String publisherName = element[2].trim().toUpperCase();
                String phone = element[3].trim();
                Publisher newPub = new Publisher(publisherId, publisherName, phone);
                this.add(newPub);
            }
            System.out.println("Read Publisher file success!!");
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println("Not found File Publisher");
            System.out.println("");
        }
    }

    public void printOut() {
        if (this.size() == 0) {
            System.out.println("Empty!!!");
            return;
        }
        Collections.sort(this, new Comparator<Publisher>() {
            @Override
            public int compare(Publisher o1, Publisher o2) {
                return o1.publisherName.compareTo(o2.publisherName);
            }

        });
        for (Publisher p : this) {
            System.out.println(p.toString());
        }
    }

    public void writeFile() {
        try {
            FileWriter fw = new FileWriter(fName);
            BufferedWriter bw = new BufferedWriter(fw);
            Collections.sort(this, new Comparator<Publisher>() {
                @Override
                public int compare(Publisher o1, Publisher o2) {
                    return o1.publisherId.compareTo(o2.publisherId);
                }
            });
            for (Publisher p : this) {
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("Save to File Success!!");
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Save to File Failed!!!");
            System.out.println("");
        } 

    }

}
