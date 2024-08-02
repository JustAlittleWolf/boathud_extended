package jewtvet.boathud_extended;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import net.fabricmc.loader.api.FabricLoader;

public class Config {
    public static String speedUnit = " m/s";
    public static String angleUnit = " °";
    public static String accelerationUnit = " m/s²";
    public static String timeUnit = "s";
    public static int yOffset = 36;
    public static boolean extended = true;
    public static boolean enabled = true;
    public static boolean telemetryEnabled = false;
    public static String telemetryDirectory = "C:/boat_telemetry/";
    public static boolean checkpointEnabled = false;
    public static String checkpointFile = "C:/checkpoints.cf";
    public static String checkpointFileLoaded = "";
    public static boolean circularTrack = false;
    public static double speedRate = 1.0D;
    public static int speedType = 0;
    public static double accelerationRate = 1.0D;
    public static int accelerationType = 0;
    public static ArrayList<Double[]> checkpointdata = new ArrayList();
    public static int checkpoints = 0;
    public static int barType = 0;
    private static File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "boathud.properties");

    private Config() {
    }

    public static void load() {
        try {
            if (configFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(configFile));

                for(String line = br.readLine(); line != null; line = br.readLine()) {
                    if (line.startsWith("enabled ")) {
                        enabled = Boolean.parseBoolean(line.substring(8));
                    }

                    if (line.startsWith("extended ")) {
                        extended = Boolean.parseBoolean(line.substring(9));
                    }

                    if (line.startsWith("telemetryEnabled ")) {
                        telemetryEnabled = Boolean.parseBoolean(line.substring(17));
                    }

                    if (line.startsWith("telemetryDirectory ")) {
                        telemetryDirectory = line.substring(19);
                    }

                    if (line.startsWith("checkpointEnabled ")) {
                        checkpointEnabled = Boolean.parseBoolean(line.substring(18));
                    }

                    if (line.startsWith("checkpointFile ")) {
                        checkpointFile = line.substring(15);
                    }

                    if (line.startsWith("circularTrack ")) {
                        circularTrack = Boolean.parseBoolean(line.substring(14));
                    }

                    if (line.startsWith("yoffset ")) {
                        yOffset = Integer.parseInt(line.substring(8));
                    }

                    if (line.startsWith("barType ")) {
                        barType = Integer.parseInt(line.substring(8));
                    }

                    if (line.startsWith("speedUnit ")) {
                        setSpeedUnit(Integer.parseInt(line.substring(10)));
                    }

                    if (line.startsWith("accelerationUnit ")) {
                        setAccelerationUnit(Integer.parseInt(line.substring(17)));
                    }
                }

                br.close();
            }
        } catch (Exception var2) {
        }

        if (barType > 2 || barType < 0) {
            barType = 0;
        }

        if (checkpointEnabled) {
            loadCheckpoints();
        }

    }

    public static void save() {
        try {
            FileWriter writer = new FileWriter(configFile);
            writer.write("enabled " + enabled + "\n");
            writer.write("extended " + extended + "\n");
            writer.write("telemetryEnabled " + telemetryEnabled + "\n");
            writer.write("telemetryDirectory " + telemetryDirectory + "\n");
            writer.write("checkpointEnabled " + checkpointEnabled + "\n");
            writer.write("checkpointFile " + checkpointFile + "\n");
            writer.write("circularTrack " + circularTrack + "\n");
            writer.write("yoffset " + yOffset + "\n");
            writer.write("barType " + barType + "\n");
            writer.write("speedUnit " + speedType + "\n");
            writer.write("accelerationUnit " + accelerationType + "\n");
            writer.close();
        } catch (Exception var1) {
        }

    }

    public static void setSpeedUnit(int type) {
        switch(type) {
        case 0:
        default:
            speedRate = 1.0D;
            speedUnit = " m/s";
            speedType = 0;
            break;
        case 1:
            speedRate = 3.6D;
            speedUnit = " km/h";
            speedType = 1;
            break;
        case 2:
            speedRate = 2.236936D;
            speedUnit = " mph";
            speedType = 2;
            break;
        case 3:
            speedRate = 1.943844D;
            speedUnit = " kt";
            speedType = 3;
        }

    }

    public static void setAccelerationUnit(int type) {
        switch(type) {
        case 0:
        default:
            accelerationRate = 1.0D;
            accelerationUnit = " m/s²";
            accelerationType = 0;
            break;
        case 1:
            accelerationRate = 0.101972D;
            accelerationUnit = " g  ";
            accelerationType = 1;
        }

    }

    public static void loadCheckpoints() {
        checkpointdata.clear();

        try {
            BufferedReader br = new BufferedReader(new FileReader(checkpointFile));

            for(String line = br.readLine(); line != null; line = br.readLine()) {
                if (!line.contains("time")) {
                    String[] s = line.split(",");
                    Double[] data = new Double[5];

                    for(int i = 0; i < 5; ++i) {
                        data[i] = Double.parseDouble(s[i]);
                    }

                    checkpointdata.add(data);
                }
            }

            br.close();
        } catch (Exception var5) {
        }

        checkpointFileLoaded = checkpointFile;
        checkpoints = checkpointdata.size();
    }
}
