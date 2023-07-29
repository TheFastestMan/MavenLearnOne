package ru.railandcompany.app.task1;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JakartaDAOTest {
    private Jakarta jakarta;
    private Technology technology;
    private JakartaDAO jakartaDAO;
    private String path = "/Users/rail/IdeaProjects/SkillSpace_Enterprise/MavenLearnOne/src/main/resources" +
            "/test_jakarta.json";

    @BeforeEach
    void setUp() {
        technology = new Technology("Test technology", "Test technology description");
        List<Technology> technologies = new ArrayList<>();
        technologies.add(technology);
        jakarta = new Jakarta("Test jakarta version", "Test jakarta description", technologies);
        jakartaDAO = new JakartaDAO(jakarta, path);
    }

    @Test
    void writeToJsonTest() throws IOException {
        jakartaDAO.writeToJson();
        File file = new File(path);
        assertTrue(file.exists(), "The 'test_jakarta.json' should have been created");
        assertTrue(file.length() > 0, "File should not be empty");
    }

    @Test
    void readFromJsonTest() throws IOException {
        jakartaDAO.writeToJson();
        Jakarta readJakarta = jakartaDAO.readFromJson();
        assertEquals(jakarta.getVersion(), readJakarta.getVersion());
        assertEquals(jakarta.getDescription(), readJakarta.getDescription());
        assertEquals(jakarta.getListOfTechnologies().size(), readJakarta.getListOfTechnologies().size());
        assertEquals(jakarta.getListOfTechnologies().get(0).getName(),
                readJakarta.getListOfTechnologies().get(0).getName());
        assertEquals(jakarta.getListOfTechnologies().get(0).getDescription(),
                readJakarta.getListOfTechnologies().get(0).getDescription());
    }

    @Test
    void updateTechnologyTest() throws IOException {
        Technology updatedTec = new Technology("Updated Technology", "Updated description");
        jakartaDAO.updateTechnology(updatedTec);
        Jakarta updatedJakarta = jakartaDAO.readFromJson();
        assertEquals(updatedJakarta.getListOfTechnologies().get(0).getName(),
                jakarta.getListOfTechnologies().get(0).getName());
        assertEquals(updatedJakarta.getListOfTechnologies().get(0).getDescription(),
                jakarta.getListOfTechnologies().get(0).getDescription());

    }
}