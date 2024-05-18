import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String filePath = "data.txt";
        Map<String, List<Broadcast>> channelPrograms = new LinkedHashMap<>();
        List<String> orderOfChannels = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String channel = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    channel = line.substring(1).trim();
                    orderOfChannels.add(channel);
                    channelPrograms.put(channel, new ArrayList<>());
                } else {
                    String programName = line.trim();
                    String programTimeStr = reader.readLine().trim();
                    channelPrograms.get(channel).add(new Broadcast(programName, programTimeStr));
                }
            }

            for (String channelName : orderOfChannels) {
                System.out.println("Канал: " + channelName);
                for (Broadcast program : channelPrograms.get(channelName)) {
                    System.out.println("Время: " + program.getName() + ", Программа: " + program.getTime());
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelWriter excelWriter = new ExcelWriter();
        excelWriter.writeExcel(channelPrograms);
    }

}