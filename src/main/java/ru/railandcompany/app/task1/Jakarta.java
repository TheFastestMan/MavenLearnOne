package ru.railandcompany.app.task1;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Jakarta {
    private String version;
    private String description;
    private List<Technology> listOfTechnologies;

}