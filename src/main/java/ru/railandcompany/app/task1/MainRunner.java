package ru.railandcompany.app.task1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainRunner {
    public static void main(String[] args) {
        String path = "/Users/rail/IdeaProjects/SkillSpace_Enterprise/MavenLearnOne/src/main/resources/jakarta.json";

        Technology technology1 = new Technology("Technology 1.0", "Description 1.0");
        Technology technology2 = new Technology("Technology 2.0", "Description 2.0");

        List<Technology> technologies = new ArrayList<>();
        technologies.add(technology1);
        technologies.add(technology2);

        try {
            Jakarta jakarta = new Jakarta("1.1", "testing", technologies);

            JakartaDAO jakartaDAO = new JakartaDAO(jakarta, path);

            jakartaDAO.writeToJson();

            Jakarta jakarta1 = jakartaDAO.readFromJson();
            System.out.println(jakarta1);

            Technology updatedTec = new Technology("Technology 2.0", "UpdatedTec Description 2.0");
            jakartaDAO.updateTechnology(updatedTec);
            Jakarta updated = jakartaDAO.readFromJson();
            System.out.println("UpdatedTec " + updated);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
