package code.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ccy
 */
@Data
public class DateParamDTO implements Serializable {
    private Date dateParam;

    private Date dateReturn;
}