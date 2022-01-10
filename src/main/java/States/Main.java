package States;

import AvtoPark.AvtoPark;
import DriverInfo.DriverInfo;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String choice = "";
        int id = 0;
        //1st-stage
        List<AvtoPark> list1 = new ArrayList<>();
        try {
            String stage1 = new String(Files.readAllBytes(Path.of("C:\\Users\\mapae\\IdeaProjects\\ttt.xml\\StageS.json")));
            JSONObject json = new JSONObject(stage1);

            for(int i = 0; i<json.length();i++){
                JSONObject driver = json.getJSONObject(String.valueOf(i));
                AvtoPark car1 = new AvtoPark();
                car1.setId(driver.getInt("id"));
                car1.setName(driver.getString("name"));
                car1.setDriver(driver.getString("driver"));
                car1.setState(driver.getString("state"));
                list1.add(car1);
            }
            displayFStage(list1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2nd-stage
        try{
            String stage2 = new String(Files.readAllBytes(Path.of("C:\\Users\\mapae\\IdeaProjects\\ttt.xml\\StageF.json")));
            JSONObject json2 = new JSONObject(stage2);
            for(int i = 0; i<json2.length();i++){
                JSONObject driver = json2.getJSONObject(String.valueOf(i));
                list1.get(i).dInfo = new DriverInfo();
                list1.get(i).dInfo.setDriverId(driver.getString("id"));
                list1.get(i).dInfo.setName(driver.getString("name"));
                list1.get(i).setDriver(list1.get(i).dInfo.getName());
            }
            displaySStage(list1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            System.out.print("N: ");
            id = scanner.nextInt();

            id--;
            System.out.println("Bus: "+list1.get(id).getName());
            System.out.println("Driver: "+list1.get(id).getDriver());
            System.out.println("Bus state: "+list1.get(id).getState());
            System.out.println("If you wish to stop: click q");
            scanner.nextLine();
            choice = scanner.nextLine();
        }while (!choice.equals("q"));
        //3rd-stage


    }
    public static void displayFStage(List<AvtoPark> list1){
        System.out.println(ANSI_PURPLE+"# Макс-ое кол-во символов в id: 2"+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"# Макс-ое кол-во символов в назв-ии трака: 16"+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"# Макс-ое кол-во символов в имени водителя: 10"+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"# Макс-ое кол-во символов в сост-нии трака: 6\n"+ANSI_RESET);
        System.out.println(ANSI_YELLOW + "          #1-st Stage" + ANSI_RESET);
        System.out.println("|#id|      Bus      |  Driver  |State |");
        System.out.println("|———|———————————————|——————————|——————|");
        for(AvtoPark autoPark : list1){
            System.out.printf("|%-2s ",autoPark.getId());
            System.out.printf("|%-15s|", autoPark.getName());
            System.out.printf("%-10s|", autoPark.getDriver() + " ");
            System.out.printf("%-6s|",autoPark.getState());
            System.out.println();
        }
    }
    public static void displaySStage(List<AvtoPark> list2){
        System.out.println("\n"+ANSI_YELLOW + "          #2-nd Stage" + ANSI_RESET);
        System.out.println("|#id|      Bus      |  Driver  |State |");
        System.out.println("|———|———————————————|——————————|——————|");
        for(AvtoPark autoPark : list2){
            System.out.printf("|%-2s ",autoPark.getId());
            System.out.printf("|%-15s|", autoPark.getName());
            System.out.printf("%-10s|", autoPark.getDriver() + " ");
            System.out.printf("%-6s|",autoPark.getState());
            System.out.println();
        }
        System.out.println();
    }
}
