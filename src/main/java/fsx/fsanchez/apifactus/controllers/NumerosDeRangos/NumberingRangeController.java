package fsx.fsanchez.apifactus.controllers.NumerosDeRangos;

import fsx.fsanchez.apifactus.dto.RangosDto.NumberingRangeDto;
import fsx.fsanchez.apifactus.services.NumerosDeRangos.NumberingRangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/numbering-ranges")
public class NumberingRangeController {

    private final NumberingRangeService numberingRangeService;

    public NumberingRangeController(NumberingRangeService numberingRangeService) {
        this.numberingRangeService = numberingRangeService;
    }

    @GetMapping
    public List<NumberingRangeDto> getAllNumberingRanges() {
        return numberingRangeService.getNumberingRanges();
    }
}
