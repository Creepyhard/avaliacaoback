package com.ekan.controller;

import com.ekan.DTO.BeneficiarioDTO;
import com.ekan.model.Documento;
import com.ekan.service.BeneficiarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

    private final BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    @GetMapping
    public List<BeneficiarioDTO> getBeneficiarios() {
        return beneficiarioService.listarTodosService();
    }

    @GetMapping(path = {"/{id}"})
    public BeneficiarioDTO getBeneficiarioId(@PathVariable long id) {
        return beneficiarioService.buscarPorIdService(id);
    }

    @PostMapping
    public BeneficiarioDTO salvarBeneficiario(@RequestBody BeneficiarioDTO ben) {
        return beneficiarioService.salvarService(ben);
    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<Object> deletarBeneficiario(@PathVariable long id) {
        return beneficiarioService.deletarService(id);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<BeneficiarioDTO> attBeneficiario(@PathVariable Long id, @RequestBody BeneficiarioDTO benDetails){
        return beneficiarioService.attBeneficiarioService(id, benDetails);
    }

    @PostMapping({"/withdoc"})
    public BeneficiarioDTO salvarBeneficiarioEDocumentoService(@RequestBody BeneficiarioDTO ben, @RequestBody Documento doc) {
        return beneficiarioService.salvarBenegiciarioEDocumentoService(ben, doc);
    }
}
