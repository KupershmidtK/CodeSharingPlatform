package platform.businesslayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "snippet")
public class CodeSnippet {
    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @Column
    private String code;

    @Column
    private LocalDateTime date;

    @Column
    private int time;

    @Column
    private int views;

    @Column
    private int viewed;

    @Transient
    private LocalDateTime timeOfCreation = LocalDateTime.now();

    @Transient
    private long leftTime;

    public long getLeftTime() {
        return time == 0 ? time : time - ChronoUnit.SECONDS.between(date, timeOfCreation);
    }

    public int getLeftViews() {
        return views == 0 ? views : views - viewed;
    }

    public String getDate() {
        final String FORMAT_OF_DATE =  "yyyy-MM-dd HH:mm:ss";
        return date.format(DateTimeFormatter.ofPattern(FORMAT_OF_DATE));
    }
}