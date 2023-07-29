package ru.railandcompany.app.task1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import java.util.List;

public class JakartaDAO {
    private ObjectMapper objectMapper = new ObjectMapper();

    private String path;
    private Jakarta jakarta;


    public JakartaDAO(Jakarta jakarta, String path) {
        this.jakarta = jakarta;
        this.path = path;

    }

    public void writeToJson() throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), jakarta);
    }

    public Jakarta readFromJson() throws IOException {
        return objectMapper.readValue(new File(path), Jakarta.class);
    }

    public void updateTechnology(Technology technology) throws IOException {
        Jakarta jakarta = readFromJson();
        List<Technology> list = jakarta.getListOfTechnologies();

        list.removeIf(l -> l.getName().equals(technology.getName()));
        list.add(technology);

        this.jakarta = jakarta;
        writeToJson();
    }

}
