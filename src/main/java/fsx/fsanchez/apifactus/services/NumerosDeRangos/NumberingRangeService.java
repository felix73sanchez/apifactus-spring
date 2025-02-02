package fsx.fsanchez.apifactus.services.NumerosDeRangos;

import fsx.fsanchez.apifactus.dto.RangosDto.NumberingRangeDto;

import java.util.List;

public interface NumberingRangeService {

    List<NumberingRangeDto> getNumberingRanges();
}
