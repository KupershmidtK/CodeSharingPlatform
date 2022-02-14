package platform.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.businesslayer.*;

import java.util.List;
import java.util.Map;

@Controller
public class CodeController {

    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/code/new")
    public String returnNewHTML(Model model) {
        return "newcode";
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<Map<String, String>>  insertCode(@RequestBody Map<String, String> newCode) {
        String id = codeService.insertCode(
                newCode.get("code"),
                newCode.get("time"),
                newCode.get("views"));

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Content-Type", "application/json");
        return ResponseEntity.ok()
                .headers(responseHeader)
                .body(Map.of("id", id));
    }

    @GetMapping("/code/{id}")
    public String getHtmlById(Model model, @PathVariable String id) {
        CodeSnippet snippet = codeService.getCodeById(id);
        if (snippet == null
                || (snippet.getTime() !=0 && snippet.getLeftTime() <= 0)
                || (snippet.getViews() != 0 && snippet.getLeftViews() < 0)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "404 Not Found");
        }

        model.addAttribute("code", snippet);
        return "code";
    }

    @GetMapping(path = "/api/code/{id}")
    public ResponseEntity<CodeSnippetView> getJsonById(@PathVariable String id) {
        CodeSnippet snippet = codeService.getCodeById(id);
        CodeSnippetView snippetView = CodeService.snippetToObject(snippet);

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Content-Type", "application/json");
        if ((snippet.getViews() != 0 && snippet.getLeftViews() <= 0)
                || (snippet.getTime() !=0 && snippet.getLeftTime() < 0)) {
            return ResponseEntity.status(404)
                    .headers(responseHeader)
                    .body(snippetView);
        }

        return ResponseEntity.ok()
                .headers(responseHeader)
                .body(snippetView);
    }

    @GetMapping("/code/latest")
    public String getHtmlLast10(Model model) {
        List<CodeSnippetView> snippet = codeService.getLast10();
        model.addAttribute("snippets", snippet);
        return "codelist";
    }

    @GetMapping(path = "/api/code/latest")
    public ResponseEntity<List<CodeSnippetView>> getJsonLast10() {
        List<CodeSnippetView> snippet = codeService.getLast10();

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Content-Type", "application/json");

        return ResponseEntity.ok()
                .headers(responseHeader)
                .body(snippet);
    }
}
