package cn.nurasoft.bkhatfield.CriticalsAlpha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableCriticals {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<>();

        List<String> critical1 = new ArrayList<>();
        critical1.add("1. Stocked with paper towels, antibacterial soap, sanitizer and glove racks.");
        critical1.add("2. Hands correctly washed and sanitized at least once per hour.");
        critical1.add("3. Free from obstruction.");

        List<String> critical2 = new ArrayList<>();
        critical2.add("1. Most current health inspection report available.");
        critical2.add("2. Follow up action(s) documented");
        critical2.add("3. All critical & non-critical violations corrected.");

        List<String> critical3 = new ArrayList<>();
        critical3.add("1. All sinks meet hot water requirements and in good repair.");

        List<String> critical4 = new ArrayList<>();
        critical4.add("1. Temperature Control && Quality Checks.");

        List<String> critical5 = new ArrayList<>();
        critical5.add("1. Properly labeled containers/spray bottles.");
        critical5.add("2. Sanitizer test strips readily available.");
        critical5.add("3. All open stations have accessible sanitizer solutions at proper PPM (100-200/150-400) with correct color wipe cloths immersed in solution.");

        List<String> critical6=new ArrayList<>();
        critical6.add("1. All brushes present, clean, stored properly and in good repair.");
        critical6.add("2. Blender/spindle area are free of dried shake mix build up.");
        critical6.add("3. No buildup evident in carburetor tube.");

        List<String> critical7=new ArrayList<>();
        critical7.add("1. Only approved food, packaging, and chemicals in use.");

        List<String> critical8=new ArrayList<>();
        critical8.add("1. Potentially hazardous foods marked with proper hold time and discarded when expired (ham, cheese, sliced tomatoes and lettuce).");

        List<String> critical9=new ArrayList<>();
        critical9.add("1. Required tongs and ice scoops/holders available and in use.");
        critical9.add("2. Cooked and raw product tongs kept separate and stored so handles not touching product.");
        critical9.add("3. All smallwares, ice scoops/holders, and specified broiler parts W/R/S every 4 hours.");
        critical9.add("4. New or replacement product not mixed with old product.");

        List<String> critical10=new ArrayList<>();
        critical10.add("1. Restaurant free of insects, live or dead rodents, visible rodent droppings and nesting birds.");
        critical10.add("2. Most recent pest control operator report on file in restaurant.");

        List<String> critical11=new ArrayList<>();
        critical11.add("1. Beef Cookout performed & recorded at least 3 times per day (4 times/day if open after 12 a.m.).");
        critical11.add("2. Previous shift cookouts completed, missed cookouts highlighted and initialed");

        List<String> critical12=new ArrayList<>();
        critical12.add("1. No evidence of Team Member illness (e.g., cold, flu,stomach disorders)");
        critical12.add("2. No cleaning products / chemicals improperly labeled or stored by food");
        critical12.add("3. Only yellow tools, wipe cloths, and scrub pads stored in rest room caddy / only used in rest room");
        critical12.add("4. No drainage back-up.");

        expandableListDetail.put("Hand Washing", critical1);
        expandableListDetail.put("Health Violations", critical2);
        expandableListDetail.put("Hot Water", critical3);
        expandableListDetail.put("Temperature Control & Quality Control",critical4);
        expandableListDetail.put("Sanitizing",critical5);
        expandableListDetail.put("Shake Machine/Soft Serve",critical6);
        expandableListDetail.put("Approved Product",critical7);
        expandableListDetail.put("Time Control",critical8);
        expandableListDetail.put("Cross Contamination",critical9);
        expandableListDetail.put("Pest Activity",critical10);
        expandableListDetail.put("Cookouts",critical11);
        expandableListDetail.put("Other Critical Violantions",critical12);
        return expandableListDetail;
    }
}

