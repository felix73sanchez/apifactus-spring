package fsx.fsanchez.apifactus.dto.RangosDto;

import lombok.Data;
import java.util.List;

@Data
public class NumberingRangeResponse {
    private List<NumberingRangeDto> data;
}