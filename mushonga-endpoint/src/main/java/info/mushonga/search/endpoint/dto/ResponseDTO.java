package info.mushonga.search.endpoint.dto;

import lombok.Data;

import java.util.Map;

/**
 * The Response Dto
 *
 * @author percym
 */
@Data
public class ResponseDTO {

    String message;

    Boolean success;

    Map<String ,String> data;
}
