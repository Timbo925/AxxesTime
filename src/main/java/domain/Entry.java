package domain;

import org.apache.catalina.User;

import java.time.LocalDateTime;

public class Entry {

    private Long id;
    private LocalDateTime from;
    private LocalDateTime to;
    private String dag;
    private Project project;
    private Percentage percantage;
    private User user;
    private Status status;
}
