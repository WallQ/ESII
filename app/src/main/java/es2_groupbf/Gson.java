package es2_groupbf;

import es2_groupbf.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Gson {
    public static <T> void exportData(T data, String fileName) throws FileNotFoundException {
        com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String json = gson.toJson(data);

        String buildDir = Utils.getPathFromResources("");
        File file = new File(buildDir, fileName);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json);
            System.out.println("The following file -> " + fileName + " was saved in -> " + file.getAbsolutePath());
        } catch (IOException exception) {
            throw new FileNotFoundException("Error writing file: " + exception.getMessage());
        }
    }
}
