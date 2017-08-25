
/**
 * @author Vadym Stavskyi
 * @version 25/08/2017
 */
package Logs;
import java.util.*;
import edu.duke.*;


public class LogAnalyzer
{

     private ArrayList<LogEntry> records;
     private HashMap<String, Integer> ips;
     
     public LogAnalyzer() {
         records = new ArrayList<>();
         ips = new HashMap<>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line: fr.lines()) {
            LogEntry x = WebLogParser.parseEntry(line);
            records.add(x);
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public int countUniqueIPs() {
         ArrayList<String> IPs = new ArrayList<>();
         for (LogEntry entry: records) {
             String ip = entry.getIpAddress();
             if (!IPs.contains(ip)) {
                 IPs.add(ip);
             }
         }
         return IPs.size();
     }

     public void printAllHigherThanNum(int num) {
         for (LogEntry entry: records) {
             int statusCode = entry.getStatusCode();
             if (statusCode > num) {
                 System.out.println(entry);
             }
         }
     }

     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIp = new ArrayList<>();
         for (LogEntry entry: records) {
             String date = entry.getAccessTime().toString();
             if (date.substring(4,10).equals(someday)) {
                 String ip = entry.getIpAddress();
                 if (!uniqueIp.contains(ip)) {
                     uniqueIp.add(ip);
                 }
             }
         }
         return uniqueIp;
     }

     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIp = new ArrayList<>();
         for (LogEntry entry: records) {
             int statusCode = entry.getStatusCode();
             if (statusCode >= low && statusCode <= high) {
                 String ip = entry.getIpAddress();
                 if (!uniqueIp.contains(ip)) {
                     uniqueIp.add(ip);
                 }
             }
         }
         return uniqueIp.size();
     }

     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> uniqueIps = new HashMap<>();
         for (LogEntry entry: records) {
             String currentIP = entry.getIpAddress();
             if (!uniqueIps.containsKey(currentIP)) {
                 uniqueIps.put(currentIP, 1);
             }
             else {
                 uniqueIps.put(currentIP, uniqueIps.get(currentIP)+1);
             }
         }
         ips = uniqueIps;
         return uniqueIps;
     }

     public int mostNumberVisitsByIP(HashMap<String, Integer> ips) {
         int maxSoFar = 0;
         for (String key: ips.keySet()) {
             if (ips.get(key) > maxSoFar) {
                 maxSoFar = ips.get(key);
             }
         }
         return maxSoFar;
     }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ips) {
        int maxVisits = mostNumberVisitsByIP(ips);
        ArrayList<String> result = new ArrayList<>();

        for (String key: ips.keySet()) {
            int value = ips.get(key);
            if (value == maxVisits) {
                result.add(key);
            }
        }
        return result;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> result = new HashMap<>();
         for (LogEntry le: records) {
             String date = le.getAccessTime().toString().substring(4,10);
             String ip = le.getIpAddress();

             if (!result.containsKey(date)) {
                 ArrayList<String> ips = new ArrayList<>();
                 ips.add(ip);
                 result.put(date, ips);
             }
             else {
                 ArrayList<String> ips = result.get(date);
                 ips.add(ip);
                 result.put(date, ips);
             }
         }
        return result;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsOnDate, String day) {
         ArrayList<String> ips = iPsOnDate.get(day);
         HashMap<String, Integer> counts = new HashMap<>();

         for (int i=0; i<ips.size(); i++) {
             String currentIP = ips.get(i);
             if (!counts.containsKey(currentIP)) {
                 counts.put(currentIP, 1);
             }
             else {
                 counts.put(currentIP, counts.get(currentIP)+1);
             }
         }
         ArrayList<String> maxVisits = iPsMostVisits(counts);
        return maxVisits;
    }


}
