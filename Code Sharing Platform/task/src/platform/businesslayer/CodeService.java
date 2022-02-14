package platform.businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import platform.persistence.CodeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CodeService {

    private final List<CodeSnippetView> codeList = Collections.synchronizedList(new ArrayList<>());
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public String insertCode(String code, String time, String views) {
        CodeSnippet codeSnippet = new CodeSnippet();
        codeSnippet.setCode(code);
        codeSnippet.setDate(LocalDateTime.now());
        codeSnippet.setViews(Integer.parseInt(views));
        codeSnippet.setTime(Integer.parseInt(time));
        return codeRepository.save(codeSnippet).getId();
    }

    public CodeSnippet getCodeById(String id) {
        CodeSnippet snippet = codeRepository.findById(id).orElse(null);

        if (snippet != null) {
            int viewed = snippet.getViewed();
            snippet.setViewed(viewed + 1);
            codeRepository.save(snippet);
        }

        return snippet;
    }

    static public CodeSnippetView snippetToObject(CodeSnippet snippet) {
        if (snippet == null) {
            return null;
        }

        return new CodeSnippetView(
                snippet.getCode(),
                snippet.getDate(),
                snippet.getLeftTime(),
                snippet.getLeftViews());
    }

    private void fillListOfData(CodeSnippet snippet) {
        if(snippet.getTime() == 0 && snippet.getViews() == 0) {
            codeList.add(snippetToObject(snippet));
        }
    }

    public List<CodeSnippetView> getLast10 () {
        codeList.clear();
        codeRepository.findAll(Sort.by(Sort.Direction.DESC, "date"))
                .forEach(this::fillListOfData);
        int toIndex = codeList.size() < 10 ? codeList.size() : 10;
        return codeList.subList(0, toIndex);
    }
}
