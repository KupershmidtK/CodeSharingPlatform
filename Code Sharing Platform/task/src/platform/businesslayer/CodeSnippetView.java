package platform.businesslayer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodeSnippetView {
    private String code;
    private String date;
    private long time;
    private int views;
}
