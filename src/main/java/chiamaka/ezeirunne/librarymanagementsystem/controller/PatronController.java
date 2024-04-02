package chiamaka.ezeirunne.librarymanagementsystem.controller;

import chiamaka.ezeirunne.librarymanagementsystem.dto.BookResponse;
import chiamaka.ezeirunne.librarymanagementsystem.dto.PatronRequest;
import chiamaka.ezeirunne.librarymanagementsystem.dto.PatronResponse;
import chiamaka.ezeirunne.librarymanagementsystem.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    @PostMapping
    public ResponseEntity<?> registerPatron(@RequestBody PatronRequest patronRequest) {

        patronService.registerPatron(patronRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatron(@PathVariable Long id, @RequestBody PatronRequest patronRequest) {

        patronService.updatePatron(id, patronRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<PatronResponse>> getAllPatrons() {
        List<PatronResponse> patronResponses = patronService.getAllPatrons();
        return new ResponseEntity<>(patronResponses, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronResponse> getPatronById(@PathVariable Long id) {
        PatronResponse patronResponse = patronService.getPatronById(id);
        return ResponseEntity.ok().body(patronResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }


}
