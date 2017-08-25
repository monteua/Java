
/**
 * @author Vadym Stavskyi
 * @version 25/08/2017
 */
package Logs;

import java.util.*;

public class Tester {

    public static void testLogEntry() {
        Logs.LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    public static void testLogAnalyzer(String filename) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/Logs/data/"+filename);
        la.printAll();
    }

    public static void testUniqueIP(String filename) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/Logs/data/"+filename);
        System.out.println("Unique IPs: " + la.countUniqueIPs());
    }

    public static void testPrintAllHigherThanNum(String filename) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/Logs/data/"+filename);
        la.printAllHigherThanNum(400);
    }

    public static void testUniqueIPVisitsOnDay(String filename, String date) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/Logs/data/"+filename);
        ArrayList<String> lt = la.uniqueIPVisitsOnDay(date);
        System.out.println("Unique IPs in day: " + lt);
        System.out.println("Size: " + lt.size());
    }

    public static void testCountUniqueIPsInRange(String filename, int low, int high) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/Logs/data/"+filename);
        System.out.println("Unique IPs with status code: " + la.countUniqueIPsInRange(low, high));
        //System.out.println("Unique IPs with status code: " + la.countUniqueIPsInRange(300, 399));
    }

    public static void testCountVisitsPerIp(String filename) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/Logs/data/"+filename);
        HashMap<String,Integer> result = la.countVisitsPerIP();
        System.out.println("Ips counts:\n" + result);
    }

    public static void testMostNumberVisitsByIP(String filename) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/Logs/data/"+filename);
        HashMap<String,Integer> result = la.countVisitsPerIP();
        System.out.println("Maximum number of visits to this website by a single IP address:\t"
                + la.mostNumberVisitsByIP(result));
    }

    public static void testIPsMostVisits(String filename) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/Logs/data/"+filename);
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println("Maximum number of visits to this website by a single IP address:\t"
                + la.mostNumberVisitsByIP(counts));
        System.out.println("Ips, that have maximum visits:\t" + la.iPsMostVisits(counts));
    }

    public static void testIPsForDays(String filename) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/Logs/data/"+filename);
        String date = null;
        int visits = 0;
        HashMap<String, ArrayList<String>> result = la.iPsForDays();
        for (String key: result.keySet()) {
            int size = result.get(key).size();
            if (size > visits) {
                date = key;
                visits = size;
            }
        }
        System.out.println("Day:\t" + date);
        //System.out.println("IPS on days:\n" + result);
    }

    public static void testIPsWithMostVisitsOnDay(String filename, String date) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/Logs/data/"+filename);
        HashMap<String, ArrayList<String>> ips = la.iPsForDays();
        System.out.println("IP addresses that had the most accesses on the given day:\n" +
                la.iPsWithMostVisitsOnDay(ips, date));
    }


    public static void main(String[] args) {
        //testLogEntry();
        //testLogAnalyzer();
        //testUniqueIP("weblog2_log");
        //testPrintAllHigherThanNum();
        //testUniqueIPVisitsOnDay("weblog2_log", "Sep 27");
        //testCountUniqueIPsInRange("weblog2_log", 200, 299);
        //testCountVisitsPerIp("weblog2_log");

        //testMostNumberVisitsByIP("weblog2_log");
        //testIPsMostVisits("weblog2_log");
        //testIPsForDays("weblog2_log");
        testIPsWithMostVisitsOnDay("weblog2_log", "Sep 30");
    }
}
